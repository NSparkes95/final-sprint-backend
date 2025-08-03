# Final Sprint Backend

## Overview

A Spring Boot REST API for [Project Name/Theme]—built for SDAT Summer 2025 Final Sprint.  
This backend powers flight/airport management (Flights, Airports, Airlines, Gates) and is designed for cloud deployment (AWS).

---

## Team

- Nicole Sparkes (Backend Lead)
- Christian Rose (Frontend Lead)
- Dylan Finlay (Agile Lead / QA / Docs / DevOps Support)

---

## Tech Stack

- Java 17+ / Spring Boot
- MySQL (local & AWS)
- Docker (for deployment)
- AWS ECS/EBS (deployment target)
- JUnit / Mockito (testing)

---

## Project Setup

### Prerequisites

- Java 17 or newer
- Maven or Gradle
- MySQL running locally
- (Optional) Docker

### Clone the Repo

```bash
git clone https://github.com/NSparkes95/final-sprint-backend.git
cd final-sprint-backend

Configure Database
Create a local MySQL database (e.g., sprint_db).

Update src/main/resources/application.properties (or .yml) with your local DB credentials.

Run the App
./mvnw spring-boot:run
# or
./gradlew bootRun
App will run at http://localhost:8080

API Documentation
Swagger UI: http://localhost:8080/swagger-ui.html

Key Endpoints:

/api/flights

/api/airports

/api/airlines

/api/gates

(Expand as you implement more features)

Docker
docker build -t final-sprint-backend .
docker run -p 8080:8080 --env-file .env final-sprint-backend
(Document MySQL connection info for Docker setup as you go)

AWS Deployment
Will be deployed to AWS ECS/EBS (single team AWS account).

Update this section with public API URL once live.

Contributors & Roles
Name	Role	Responsibilities
Nicole Sparkes	Backend Lead	API, DB, Docker, AWS
Christian Rose	Frontend Lead	React, UI, API integration
Dylan Finlay	Agile/QA/Docs	Trello, docs, QA, CI/CD

Additional Notes
@CrossOrigin is enabled on all controllers for CORS.

PR workflow and branch protection is enabled.

Please submit PRs for all changes.

License
MIT
