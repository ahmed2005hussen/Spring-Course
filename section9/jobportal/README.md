# Job Portal: Docker and MySQL

This application stores its data in a MySQL database. Docker is used to run MySQL locally, so MySQL does not need to be installed directly on the machine.

## MySQL configuration

The datasource is configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://${DATABASE_HOST:localhost}:${DATABASE_PORT:3306}/${DATABASE_NAME:jobportal}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
```

Spring uses the environment variables when they are provided. Otherwise, it connects with these defaults:

| Setting | Default value |
| --- | --- |
| Host | `localhost` |
| Port | `3306` |
| Database | `jobportal` |
| Username | `root` |
| Password | `root` |

## Start MySQL with Docker Compose

The project includes [`../compose.yml`](../compose.yml), which starts a MySQL container named `jobportaldb`. It creates the `jobportal` database, sets the root password to `root`, exposes port `3306`, and persists MySQL data in `/home/ahmed/Desktop/jobportal-data`.

From this `jobportal` directory, make sure Docker is running and start the database:

```bash
docker compose -f ../compose.yml up -d
```

This matches the application's default datasource values: `localhost:3306`, database `jobportal`, username `root`, and password `root`.

To stop or remove the database container:

```bash
docker compose -f ../compose.yml stop
docker compose -f ../compose.yml down
```

To run the application with different database values, set the matching environment variables before starting Spring Boot:

```bash
DATABASE_HOST=localhost DATABASE_PORT=3306 DATABASE_NAME=jobportal DATABASE_USERNAME=root DATABASE_PASSWORD=root ./mvnw spring-boot:run
```

## Relevant Maven dependencies

- `spring-boot-starter-data-jpa` provides JPA/Hibernate support for accessing relational data.
- `com.mysql:mysql-connector-j` is the MySQL JDBC driver used at runtime to connect to the database.
- `spring-boot-docker-compose` lets Spring Boot manage a Docker Compose file automatically. The default Compose-file selection can be overridden with `spring.docker.compose.file`; an example is commented out in `application.properties`.

## Run the application

After MySQL is ready:

```bash
./mvnw spring-boot:run
```
