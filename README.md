# Final Sprint Backend

## 📌 Overview
A Spring Boot REST API for SDAT Final Sprint – Flight API — built for **SDAT Summer 2025 Final Sprint**.  
This backend powers **flight and airport management** (Flights, Airports, Airlines, Gates) and is designed for cloud deployment on **AWS**.

---

## 👥 Team
| Name | Role | Responsibilities |
|------|------|------------------|
| Nicole Sparkes | Backend Lead | API, DB, Docker, AWS |
| Christian Rose | Frontend Lead | React, UI, API integration |
| Dylan Finlay | Agile Lead / QA / Docs / DevOps Support | Trello, docs, QA, CI/CD |

---

## 🛠 Tech Stack
- **Java 17+** / Spring Boot
- **MySQL** (local & AWS RDS)
- **Docker** (for deployment)
- **AWS ECS Fargate** (deployment target)
- **JUnit / Mockito** (testing)

---

## ⚙️ Local Development Setup

### 1️⃣ Prerequisites
- Java 17 or newer
- Maven or Gradle
- MySQL running locally
- (Optional) Docker

### 2️⃣ Clone the repository
```bash
git clone https://github.com/NSparkes95/final-sprint-backend.git
cd final-sprint-backend
3️⃣ Configure database
Create a local MySQL database (e.g., sprint_db).
Update src/main/resources/application.properties (or .yml) with your local DB credentials:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/sprint_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Management endpoints
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
4️⃣ Run the app locally
./mvnw spring-boot:run
 or
./gradlew bootRun
App will be available at:


http://localhost:8080
📜 API Documentation
Swagger UI (if enabled):


http://localhost:8080/swagger-ui.html

Key endpoints:

GET /api/flights
GET /api/airports
GET /api/airlines
GET /api/gates

🐳 Running with Docker
Build the image
docker build -t final-sprint-backend .
Run the container
docker run -p 8080:8080 --env-file .env final-sprint-backend
Note: Ensure .env contains DB connection info for your environment.

☁️ AWS Deployment
ECS
Cluster: sprint-final-cluster

Task Definition: final-sprint-task

Public IP: 3.145.47.123

Health Check:


http://3.145.47.123:8080/actuator/health
RDS
Endpoint: final-sprint-db.cx02s4sy0lyc.us-east-2.rds.amazonaws.com

Port: 3306

Publicly Accessible: Yes

🔒 Additional Notes
@CrossOrigin enabled on all controllers for CORS support.

PR workflow and branch protection enabled.

All changes must be submitted via Pull Request.

📄 License
MIT License — see LICENSE file for details.

---
