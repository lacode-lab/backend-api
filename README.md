# Backend API

Spring Boot + Kotlin REST API

## Quick Start

```bash
# Start Docker services
docker compose up -d

# Run application
./gradlew bootRun
```

## URLs

| Service | URL |
|---------|-----|
| App | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Actuator | http://localhost:8081/actuator |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3030 (admin/admin) |

## API Endpoints

### Task Management

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| POST | `/api/tasks` | Create task |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |

#### Examples

```bash
# Create task
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Buy milk", "description": "From the store"}'

# Get all tasks
curl http://localhost:8080/api/tasks

# Update task (mark as completed)
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"completed": true}'

# Delete task
curl -X DELETE http://localhost:8080/api/tasks/1
```

### Load Testing

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/load/cpu?iterations=N` | CPU load (default: 1000000) |
| GET | `/api/load/memory?sizeMb=N` | Memory load (default: 10MB) |
| GET | `/api/load/delay?delayMs=N` | Delay response (default: 1000ms) |

#### Examples

```bash
# CPU load
curl "http://localhost:8080/api/load/cpu?iterations=5000000"

# Memory load
curl "http://localhost:8080/api/load/memory?sizeMb=50"

# Delay
curl "http://localhost:8080/api/load/delay?delayMs=3000"
```

### Other

| Method | Path | Description |
|--------|------|-------------|
| GET | `/hello` | Hello message |
| GET | `/health` | Health check |

## Tech Stack

- Kotlin 1.9.25 / Java 17
- Spring Boot 3.5.5
- PostgreSQL 15.3
- Prometheus + Grafana
- Swagger/OpenAPI

## Build Commands

```bash
./gradlew build          # Build
./gradlew bootRun        # Run
./gradlew test           # Test
./gradlew bootJar        # Create JAR
```

## Docker

### Application (PostgreSQL)

```bash
docker compose up -d       # Start PostgreSQL
docker compose down -v     # Stop and remove data
```

### Monitoring (Prometheus + Grafana)

```bash
docker compose -f docker-compose.monitoring.yml up -d    # Start monitoring
docker compose -f docker-compose.monitoring.yml down -v  # Stop and remove data
```