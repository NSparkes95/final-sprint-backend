# S4 Sprint 1 API – City/Airport/Aircraft/Passenger

## Overview
A Spring Boot REST API for managing cities, airports, aircraft, and passengers.  
This project is part of S4 Sprint 1 at Keyin College. It demonstrates a relational database with JPA/Hibernate, CRUD operations, and custom query endpoints.

---

## Table of Contents

- [Features](#features)
- [ER Diagram](#er-diagram)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Seeding Sample Data](#seeding-sample-data)
- [Testing](#testing)
- [Team](#team)
- [Notes](#notes)

---

## Features

- Manage **Cities, Airports, Aircraft, Passengers**
- Full CRUD (Create, Read, Update, Delete) for all entities
- Special query endpoints:
    - Airports by City
    - Aircraft by Passenger
    - Airports by Aircraft
    - Airports by Passenger
- Relational data with JPA (One-to-Many, Many-to-Many)
- Database seeding on startup
- MySQL or H2 support

---

## ER Diagram

> _Insert your ER diagram here (or link to a PNG in the repo)!_

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL Community Server (or use H2 for testing)
- (Optional) Postman or curl for API testing

### Setup Instructions

1. **Clone the repo:**
   ```bash
   git clone https://github.com/YOUR-USERNAME/s4-sprint1-api.git
   cd s4-sprint1-api
Create a local database:

Start MySQL Server

In MySQL Workbench or command line, run:

sql
Copy
Edit
CREATE DATABASE s4sprint1db;
Set up your local properties:

Copy the sample config file to your real config:

css
Copy
Edit
cp src/main/resources/application-sample.properties src/main/resources/application.properties
Edit src/main/resources/application.properties and set your local MySQL username and password.

Build and run the project:

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Or, from your IDE, run S4Sprint1ApiApplication.java

API Endpoints:

Method	Endpoint	Description

GET	/cities	Get all cities

GET	/city/{id}	Get city by ID

POST	/city	Create city

PUT	/city/{id}	Update city

DELETE	/city/{id}	Delete city

GET	/city/{id}/airports	Get airports in a city

GET	/airports	Get all airports

GET	/airport/{id}	Get airport by ID

POST	/airport	Create airport

PUT	/airport/{id}	Update airport

DELETE	/airport/{id}	Delete airport

GET	/aircrafts	Get all aircraft

GET	/aircraft/{id}	Get aircraft by ID

POST	/aircraft	Create aircraft

PUT	/aircraft/{id}	Update aircraft

DELETE	/aircraft/{id}	Delete aircraft

GET	/aircraft/{id}/airports	Get airports for an aircraft

GET	/passengers	Get all passengers

GET	/passenger/{id}	Get passenger by ID

POST	/passenger	Create passenger

PUT	/passenger/{id}	Update passenger

DELETE	/passenger/{id}	Delete passenger

GET	/passenger/{id}/aircraft	Get aircraft for a passenger

GET	/passenger/{id}/airports	Get airports used by a passenger

Seeding Sample Data
On startup, the app seeds the database with at least 10 cities, airports, aircraft, and passengers.

This is handled in DataSeeder.java.

Testing
Test endpoints using Postman or curl.

Example:

bash

Copy

Edit

GET http://localhost:8080/cities

Verify database seeding with GET requests to each endpoint.

Team
Backend Lead: Nicole Sparkes

CLI Lead: Christian Rose

Testing/Docs: Dylan Finlay

Notes
Spring Boot 3.3.0, Java 17, Maven

Database: MySQL (s4sprint1db)

DO NOT COMMIT your real application.properties file (contains your local credentials).

Use application-sample.properties as a template for your own config.

Configuration Security

This project uses the sample properties file pattern for security:

Only application-sample.properties is committed.

application.properties (with your password) is not tracked and is in .gitignore.

Each team member must copy the sample file and enter their own credentials.

How to set up your config:

Copy:

cp src/main/resources/application-sample.properties src/main/resources/application.properties

Edit application.properties with your local username/password.
