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
docker build -t backend-api .
docker run -p 8080:8080 backend-api
```

## Architecture

Spring Boot 3.5.5 Kotlin REST API using Gradle with Kotlin DSL.

**Package structure**: `com.kurage.lab.backendapi`
- `controller/` - REST controllers
- Entry point: `BackendApiApplication.kt`

**Current endpoints**:
- `GET /hello` - Hello message
- `GET /health` - Health check (returns JSON status)

## Tech Stack

- Kotlin 1.9.25 / Java 17
- Spring Boot 3.5.5 with DevTools
- JUnit 5 for testing
- Jackson for JSON serialization

## Conventions

- Kotlin strict null-safety enabled (`-Xjsr305=strict`)
- Use `@RestController` for API endpoints
- Tests use `@SpringBootTest` for integration testing
