# 🚗 RentACarLab

Car rental management system built with Java, PostgreSQL, and Swing.
Final project for Java seminar at KDBM Datalabs.

## 🎯 What I Learned

- **OOP design**: Inheritance hierarchy with `BaseEntity` 
  parent class and composition for entity relationships
- **DAO pattern**: Separating database logic from business 
  and UI layers (Single Responsibility Principle)
- **JDBC fundamentals**: PreparedStatements, ResultSet 
  mapping, transaction management
- **Database design**: 1-to-N and N-to-N relationships, 
  cascade vs restrict deletion policies
- **Docker basics**: Containerized PostgreSQL setup

## 🛠️ Tech Stack

- Java 17
- PostgreSQL 15 (Docker)
- Swing (GUI)
- JDBC (no ORM — manual mapping for learning purposes)


## ✨ Features

- Full CRUD for Categories, Cars, Clients, Rentals
- Search rentals by client or by car
- View all active rentals
- Foreign key constraints (RESTRICT / CASCADE)
- Edit existing records

## 🚀 Setup

1. Start PostgreSQL: `docker-compose up -d`
2. Run schema: `psql -f database/schema.sql`
3. Configure `config.properties`
4. Run `gui/MainFrame.java`

## 📚 What's Next

Moving to web technologies (React + REST APIs) to apply 
the same architectural patterns in a modern stack.

## 📜 Credits & Context

This project was developed as part of the **Java** module at **KDBM Datalabs**.

**Objective:** To demonstrate proficiency in core Java and OOP principles (inheritance, polymorphism, composition), implementing the DAO pattern for database access, designing relational schemas with PostgreSQL, and building functional desktop GUIs with Swing in Linux-based environments.
