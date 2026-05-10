# RentACarLab

Java Swing application for managing a car rental business.
Final project for Java seminar at KDBM Datalabs

## Tech Stack
- Java
- PostgreSQL (Docker)
- Swing (GUI)
- JDBC

## Architecture
- **Model layer**: Entity classes with inheritance (BaseEntity → Category, Car, Client, Rent)
- **DAO layer**: Full CRUD operations with PreparedStatements
- **GUI layer**: Swing forms with JTabbedPane for views

## Database
4 tables with 1-N (Category-Car) and N-N (Client-Car via Rent) relationships.

## Setup
1. Start PostgreSQL via Docker
2. Run schema from `database/schema.sql`
3. Configure `config.properties` with your credentials
4. Run `MainFrame.main()` from `src/gui/MainFrame.java`

## Features
- ✅ Full CRUD for Categories, Cars, Clients, Rents
- ✅ Search by client / car
- ✅ View all active rentals
- ✅ Foreign key constraints (RESTRICT for categories, CASCADE for clients)
