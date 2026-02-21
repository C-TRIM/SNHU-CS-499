package com.snhu.cs320.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Simple SQLite database bootstrapper.
 * Creates schema on first use.
 */
public final class Database {

    private Database() { }

    public static Connection open(String jdbcUrl) throws SQLException {
        Connection conn = DriverManager.getConnection(jdbcUrl);
        ensureSchema(conn);
        return conn;
    }

    private static void ensureSchema(Connection conn) throws SQLException {
        // Keep schema minimal; business rules are enforced in Contact validation and service checks.
        String sql = """
            CREATE TABLE IF NOT EXISTS contacts (
                contact_id TEXT PRIMARY KEY,
                first_name TEXT NOT NULL,
                last_name  TEXT NOT NULL,
                phone      TEXT NOT NULL,
                address    TEXT NOT NULL
            );
            """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}
