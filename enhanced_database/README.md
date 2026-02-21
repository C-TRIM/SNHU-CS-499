# CS499 Capstone – Database Enhancement (Contact Service)

This module demonstrates the **Databases** enhancement by adding persistent storage to the Contact Service using:

- **SQLite** (embedded database)
- **JDBC** (prepared statements)
- **DAO pattern** (separates persistence from business logic)

## Key Changes vs. Baseline
- Contacts are persisted to a `contacts` table instead of an in-memory collection.
- `ContactService` depends on a `ContactDAO` interface to decouple storage from logic.
- Input validation still occurs in the domain model (`Contact`) and service layer.
- CRUD operations are implemented with parameterized SQL to reduce SQL injection risk.

## Run Tests
```bash
mvn test
```

## Notes
- Tests create a temporary SQLite database file so the suite is repeatable.
- The schema is created automatically if it does not exist.
