# Spring Boot, Postgres SQL, JPA, Hibernate Rest API

Build Restful CRUD API for a simple Patient application using Spring Boot, Postgres SQL, JPA and Hibernate.


## Steps to Setup

**1. Clone the application**

Clone the project into local

**2. Build and run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/patients
    
    POST /api/patient
    
    GET /api/patient/{patientId}
    
    PATCH /api/patient/{patientId}
    
    DELETE /api/patient/{patientId}

