# SNHU-CS-499
Capstone project for the B.S. in Computer Science program. Enhanced a Java Task, Appointment, and Contact Service application through architectural refactoring, HashMap-based algorithm optimization, and secure DAO database integration, demonstrating modular design, Big-O analysis, and secure data persistence aligned with industry practices.

CS 499 Capstone – Enhanced Service Application
Overview

This project represents my capstone for the Bachelor of Science in Computer Science program. It enhances a Java-based Task, Appointment, and Contact Service application through architectural refactoring, algorithm optimization, and secure database integration.

The goal of this project was to transform a course-based service application into a more scalable, maintainable, and security-conscious system aligned with professional software development standards.

Project Objectives

The enhancement focused on three core areas:

1. Software Engineering & Design

Refactored architecture to improve modularity and maintainability

Introduced service interfaces and layered design principles

Centralized validation logic into reusable components

Improved exception handling and documentation

Applied professional coding standards and clean structure

2. Algorithms & Data Structures

Replaced linear list-based searches (O(n)) with HashMap-based indexing (O(1))

Analyzed time complexity trade-offs using Big-O notation

Improved lookup efficiency and scalability

Evaluated performance implications of memory vs speed trade-offs

3. Database Integration

Implemented persistent data storage using a DAO (Data Access Object) pattern

Designed relational schema with proper constraints

Implemented CRUD operations

Used prepared statements to mitigate SQL injection risks

Introduced transaction handling and secure data validation

Technical Stack

Java

Object-Oriented Design Principles

HashMap and Collections Framework

JDBC / Relational Database Integration

DAO Pattern

Unit Testing (JUnit)

Key Skills Demonstrated

Modular architecture and separation of concerns

Algorithm efficiency analysis and optimization

Secure coding practices

Defensive programming

Database schema design

CRUD implementation

Professional documentation and code organization

Security Considerations

This project incorporates a security mindset through:

Input validation

Centralized validation framework

Prepared statements for database queries

Exception control to prevent information leakage

Structured error handling

Performance Improvements
Feature	Original Implementation	Enhanced Implementation
Object Lookup	Linear search (O(n))	Hash-based lookup (O(1))
Data Storage	In-memory only	Persistent database storage
Validation	Inline logic	Reusable validation classes
Future Improvements

Implement REST API layer

Add authentication and role-based access control

Expand unit test coverage

Introduce logging framework

Author

Chris Trimble
B.S. Computer Science Candidate
Focus: Cybersecurity & Secure Software Development
