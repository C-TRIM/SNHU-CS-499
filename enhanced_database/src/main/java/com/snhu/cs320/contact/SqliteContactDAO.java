package com.snhu.cs320.contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite implementation of ContactDAO using JDBC prepared statements.
 */
public class SqliteContactDAO implements ContactDAO {

    private final String jdbcUrl;

    public SqliteContactDAO(String jdbcUrl) {
        if (jdbcUrl == null || jdbcUrl.isBlank()) {
            throw new IllegalArgumentException("jdbcUrl must not be blank");
        }
        this.jdbcUrl = jdbcUrl;
        // Verify schema exists early
        try (Connection conn = Database.open(jdbcUrl)) {
            // no-op; schema creation happens in Database.open()
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    @Override
    public void create(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact must not be null");
        String sql = "INSERT INTO contacts(contact_id, first_name, last_name, phone, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.open(jdbcUrl);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contact.getContactId());
            ps.setString(2, contact.getFirstName());
            ps.setString(3, contact.getLastName());
            ps.setString(4, contact.getPhone());
            ps.setString(5, contact.getAddress());
            ps.executeUpdate();

        } catch (SQLException e) {
            // Unique constraint / primary key violation should map to a clear message for the caller.
            if (isConstraintViolation(e)) {
                throw new IllegalArgumentException("contactId must be unique");
            }
            throw new RuntimeException("DB insert failed", e);
        }
    }

    @Override
    public Contact findById(String contactId) {
        if (contactId == null || contactId.isBlank()) return null;
        String sql = "SELECT contact_id, first_name, last_name, phone, address FROM contacts WHERE contact_id = ?";
        try (Connection conn = Database.open(jdbcUrl);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contactId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB query failed", e);
        }
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT contact_id, first_name, last_name, phone, address FROM contacts ORDER BY contact_id";
        List<Contact> results = new ArrayList<>();
        try (Connection conn = Database.open(jdbcUrl);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(mapRow(rs));
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException("DB query failed", e);
        }
    }

    @Override
    public void update(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact must not be null");
        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, phone = ?, address = ? WHERE contact_id = ?";
        try (Connection conn = Database.open(jdbcUrl);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getAddress());
            ps.setString(5, contact.getContactId());

            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new IllegalArgumentException("contactId not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB update failed", e);
        }
    }

    @Override
    public boolean deleteById(String contactId) {
        if (contactId == null || contactId.isBlank()) return false;
        String sql = "DELETE FROM contacts WHERE contact_id = ?";
        try (Connection conn = Database.open(jdbcUrl);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contactId);
            int deleted = ps.executeUpdate();
            return deleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("DB delete failed", e);
        }
    }

    private static Contact mapRow(ResultSet rs) throws SQLException {
        return new Contact(
                rs.getString("contact_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("phone"),
                rs.getString("address")
        );
    }

    private static boolean isConstraintViolation(SQLException e) {
        // SQLite constraint violations commonly map to SQLState "23000" or error code 19
        return "23000".equals(e.getSQLState()) || e.getErrorCode() == 19;
    }
}
