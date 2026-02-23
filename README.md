# SNHU-CS-499

CS 499 Capstone – Enhanced Service Application

This repository contains my capstone project for the Bachelor of Science in Computer Science program. The project enhances a Java-based Task, Appointment, and Contact Service application through architectural refactoring, algorithm optimization, and secure database integration.

The objective was to evolve a course-based service application into a scalable, maintainable, and security-conscious system aligned with professional software development standards.

Project Enhancements
Software Engineering & Design

Refactored architecture to improve modularity and maintainability

Applied layered design principles and separation of concerns

Centralized validation logic into reusable components

Improved exception handling and documentation

Enforced clean coding standards and structured organization

Algorithms & Data Structures

Replaced linear list-based searches O(n) with HashMap indexing O(1)

Conducted Big-O time complexity analysis

Improved lookup efficiency and scalability

Evaluated performance trade-offs (memory vs. speed)

Database Integration

Implemented persistent storage using the DAO (Data Access Object) pattern

Designed relational schema with appropriate constraints

Implemented full CRUD operations

Used prepared statements to mitigate SQL injection

Applied transaction handling and secure data validation

Technical Stack

Java

Object-Oriented Design Principles

Java Collections Framework (HashMap)

JDBC

Relational Database (SQLite)

DAO Pattern

JUnit Testing

Key Skills Demonstrated

Modular system architecture

Algorithm analysis and optimization

Secure coding practices

Defensive programming

Database schema design

CRUD implementation

Professional documentation and repository structure

Security Considerations

Centralized input validation

Prepared statements for database queries

Structured exception handling

Defensive validation logic

Reduced risk of injection and information leakage

Performance Improvements
Feature	Original Implementation	Enhanced Implementation
Object Lookup	Linear search (O(n))	Hash-based lookup (O(1))
Data Storage	In-memory only	Persistent database storage
Validation	Inline logic	Reusable validation framework
Future Improvements

Implement REST API layer

Add authentication and role-based access control

Expand unit test coverage

Introduce structured logging

Code Review

YouTube walkthrough:
https://www.youtube.com/watch?v=1soNn6DVQa0

Author

Chris Trimble
B.S. Computer Science Candidate
Focus: Cybersecurity & Secure Software Development
