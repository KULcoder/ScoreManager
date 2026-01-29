# Score Manager 🎯

> 🚧 **Under Construction** — This project is actively being designed and developed.
> APIs, schema, and UI are subject to change.

Score Manager is a lightweight system for collecting, storing, and displaying user scores.
It provides a backend API for score submission and retrieval, along with a simple web UI to visualize leaderboards and player statistics.

The project is intentionally kept simple while being designed with **production-grade practices** (clean architecture, migrations, reproducible builds).

---

## ✨ Features (Planned)

### Functional

* Submit a score for a user ID
* Retrieve a user’s highest score
* Display a leaderboard of highest scores
* Display number of attempts per user

### Non-Functional

* Low-latency API (< 100ms per request)
* Handles ~1,000 requests/sec
* Supports up to 100,000 total players
* Easy local and remote deployment

---

## 🏗️ System Architecture

```
Web UI (Thymeleaf)
        ↓
Spring Boot Backend (MVC + JPA)
        ↓
PostgreSQL
```

* Server-side rendered UI using Thymeleaf
* REST-style backend APIs
* Relational database with schema migrations

---

## 🛠️ Tech Stack

### Backend

* **Java 21**
* **Spring Boot 4.0.2**
* Spring Web MVC
* Spring Data JPA + Hibernate

### Frontend

* Thymeleaf (server-side rendering)

### Database

* PostgreSQL
* Flyway (database migrations)

### Build & Tooling

* Maven (with Maven Wrapper)
* Spring Boot Actuator
* Jakarta Bean Validation

---

## 🚀 Getting Started

### Prerequisites

* Java 21+
* PostgreSQL (local or containerized)

### Run Locally

```bash
./mvnw spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

> Database configuration will be added in later stages.

---

## 📁 Project Structure (Current)

```
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com/kulcoder/scoremanager
    │   │       └── ScoreManagerApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── db
    │       │   └── migration
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com/kulcoder/scoremanager
                └── ScoreManagerApplicationTests.java
```

---

## 🧭 Development Status

* [x] Project initialization
* [ ] Database schema design
* [ ] Score submission API
* [ ] Leaderboard queries
* [ ] Web UI
* [ ] Performance testing
* [ ] Deployment configuration

---

## 📌 Notes

* This project is currently **single-service** by design.
* Authentication and authorization are intentionally out of scope for the initial version.
* The focus is on correctness, performance, and clean system design.

---

## 📄 License

MIT License (to be finalized)

---
