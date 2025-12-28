# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
./gradlew build          # Build the project
./gradlew bootRun        # Run the application (default port 8080)
./gradlew test           # Run all tests
./gradlew bootJar        # Build executable JAR
./gradlew clean build    # Clean and rebuild
```

### Docker
```bash
# Application (PostgreSQL)
docker-compose up -d
docker-compose down -v

# Monitoring (Prometheus + Grafana)
docker-compose -f docker-compose.monitoring.yml up -d
docker-compose -f docker-compose.monitoring.yml down -v
```

## Architecture

Spring Boot 3.5.5 Kotlin REST API using Gradle with Kotlin DSL.

**Package structure**: `com.kurage.lab.backendapi`
- `controller/` - REST controllers
- `model/` - Data classes
- `repository/` - Database access
- `config/` - Configuration classes
- Entry point: `BackendApiApplication.kt`

## Tech Stack

- Kotlin 1.9.25 / Java 17
- Spring Boot 3.5.5 with DevTools
- PostgreSQL 15.3 (Docker)
- Prometheus + Grafana (Docker) - Metrics monitoring
- Swagger/OpenAPI - API documentation
- JUnit 5 for testing
- Jackson for JSON serialization

## URLs

- App: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Actuator: http://localhost:8081/actuator
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000 (admin/admin)

## Conventions

- Kotlin strict null-safety enabled (`-Xjsr305=strict`)
- Use `@RestController` for API endpoints
- Tests use `@SpringBootTest` for integration testing
