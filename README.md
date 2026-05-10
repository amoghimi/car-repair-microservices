# car-repair-microservices

Kurze Anleitung zum Starten der Microservices mit Docker.

## Voraussetzungen
- Java 17+
- Maven 3.9+
- Docker Desktop (mit laufender Engine)

## Start
1. Projekt bauen (erzeugt die JARs für Docker):
   ```bash
   mvn clean package -DskipTests
   ```
2. Services starten:
   ```bash
   docker compose up -d --build
   ```
3. Status prüfen:
   ```bash
   docker compose ps
   ```

## Wichtige URLs
- API Gateway: `http://localhost:8080`
- Eureka: `http://localhost:8761`
- Config Server: `http://localhost:8888/config-server/default`
- Keycloak: `http://localhost:8180` (Realm: `car-repair`)
- Zipkin: `http://localhost:9411`

## Tests
- Unit/Integration Tests:
  ```bash
  mvn test
  ```
- Postman Collection:
  `postman/car-repair-microservices.postman_collection.json`

## Stoppen
```bash
docker compose down
```
