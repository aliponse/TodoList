# TodoList
TodoList Application for assignment built with Spring Boot 3.x, MongoDB, and Docker.
## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/aliponse/TodoList.git
2. Build the project:
   ```bash
   mvn clean install
3. Unit Test:
   ```bash
   mvn test
4. Run the application using Docker:
   ```bash
   docker-compose up --build
## API Documentation
1. Access the Swagger UI at: http://localhost:8080/swagger-ui/index.html.
2. Swagger file is ./src/doc/api-docs.json and UI at:http://localhost:8080/v3/api-docs.
## DevOps
1. CI/CD
```
   Git ---> Jenkins ---> Docker ---> SoapUI
```
2. SoapUI AutoTest example in ./src/doc/AutoTest-soapui-script.xml
## Develop Environment
1. JDK: 17
2. Maven: 3.6.3
3. MongoDB: 8.0.0
4. SpringBoot: 3.0.0
## Project Structure
    src
    ├── main/java/com/sg/sleekflow.assignment.todolist
    |   ├── config               # Configuration classes (Swagger, Security)
    |   ├── controller           # REST API controllers
    |   ├── dto                  # Data Transfer Objects (DTOs)
    |   ├── exception            # Custom exception handling
    |   ├── mapper               # DTOs and Models conversion
    |   ├── model                # Datebase entities
    |   ├── repository           # Datebase repositories
    |   ├── security             # JWT Implemention
    |   ├── service              # Business logic layer
    |   ├── util                 # Utility classes
    |   └── TodoListApplication  # Main application class
    └── test    # Unit test cases
## Architecture Diagram
```
   +-------------------+     +-------------------+     +-------------------+
   |   REST Controller |     |   Service Layer   |     |   MongoDB         |
   +-------------------+     +-------------------+     +-------------------+
   |                         |                         |
   | HTTP Requests           | Business Logic          | Database Queries
   |------------------------>|------------------------>|
   |                         |                         |
   |<------------------------|<------------------------|
   | HTTP Responses          | Processed Data          | Database Results
   +-------------------+     +-------------------+     +-------------------+
   |   DTOs            |     |   Repository      |     |  Docker Container |
   +-------------------+     +-------------------+     +-------------------+
   |                         |                         |
   | Data Mapping            | MongoDB Queries         | MongoDB Instance
   |------------------------>|------------------------>|
   |                         |                         |
   |<------------------------|<------------------------|
   | Mapped Data             | Query Results           | MongoDB Data
   +-------------------+     +-------------------+     +-------------------+
   |   Exception       |     |   Configuration   |     |  MongoDB Config   |
   +-------------------+     +-------------------+     +-------------------+
   |                         |                         |
   | Error Handling          | Spring Boot Config      | MongoDB Settings
   |------------------------>|------------------------>|
   |                         |                         |
   |<------------------------|<------------------------|
   | Custom Exceptions       | App Properties          | MongoDB Properties
   +-------------------+     +-------------------+     +-------------------+
```