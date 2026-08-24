# Job Portal

A Spring Boot learning project for building a Job Portal API. The current lesson sets up a `companies` table in H2, loads sample company records, adds a global API path prefix, and exposes a versioned controller endpoint.

## What we learned

### Spring Boot application structure

- `@SpringBootApplication` is the main application entry point. It enables auto-configuration and component scanning.
- `SpringApplication.run(JobportalApplication.class, args)` starts the embedded web server and application context.
- `@RestController` marks a class whose methods return HTTP response bodies.
- `@RequestMapping` and `@GetMapping` map HTTP requests to controller methods.
- `ResponseEntity` lets a controller specify the HTTP status and response body explicitly.

### Database initialization with H2 and SQL

- H2 is a lightweight database that is useful for local development and learning.
- `schema.sql` creates the `companies` table when it does not already exist.
- `data.sql` inserts the sample company records after the schema is available.
- The project uses a file-based H2 database, so data is retained between application restarts. `AUTO_SERVER=true` allows multiple local processes to access the database file.

### Central web configuration

- `WebConfig` implements `WebMvcConfigurer` to customize Spring MVC in one place.
- `configurePathMatch` adds `/api` to every controller path. Therefore, `@RequestMapping("/companies")` becomes `/api/companies`.
- `configureApiVersioning` resolves a version from the `v` parameter of the vendor media type `application/vnd.ahmedapp+json`.
- Versions `1.0`, `2.0`, and `3.0` are supported. When a version is absent, Spring uses `1.0` by default.
- `@GetMapping(version = "1.0")` means the current controller method handles version `1.0` requests.

## Requirements

- Java 25 (configured in `pom.xml`)
- No separate database server is required; H2 is included as a runtime dependency.

## Run the application

Run these commands from the `jobportal` directory.

```bash
./mvnw spring-boot:run
```

On Windows:

```bat
mvnw.cmd spring-boot:run
```

Useful Maven commands:

```bash
./mvnw test                # run tests
./mvnw clean package       # create the executable JAR in target/
java -jar target/jobportal-0.0.1-SNAPSHOT.jar
```

## API endpoint

The application uses Spring Boot's default port, `8080`.

| Method | URL | Required API version | Current response |
| --- | --- | --- | --- |
| `GET` | `http://localhost:8080/api/companies` | `1.0` (default) | `List companies` |

Call the default version:

```bash
curl http://localhost:8080/api/companies
```

Call version `1.0` explicitly:

```bash
curl -H 'Accept: application/vnd.ahmedapp+json;v=1.0' \
  http://localhost:8080/api/companies
```

The media type is used because API versioning is configured with `useMediaTypeParameter(...)`. A request for `2.0` or `3.0` is a supported version but has no matching controller method yet.

## Application properties

The active properties are in `src/main/resources/application.properties`.

| Property | Current value | Purpose |
| --- | --- | --- |
| `spring.application.name` | `jobportal` | Sets the application name used by Spring Boot. |
| `logging.pattern.console` | custom colored pattern | Formats console logs with time, level, thread, logger, and message colors. |
| `spring.h2.console.enabled` | `true` | Enables the browser-based H2 Console. |
| `spring.datasource.url` | `jdbc:h2:file:/home/ahmed/Desktop/h2db/jobportal;AUTO_SERVER=true` | Connects to the local file-based H2 database. The absolute path is specific to this machine. |
| `spring.datasource.username` | `sa` | Sets the H2 database username. |
| `spring.datasource.password` | configured in the local properties file | Sets the H2 database password. Do not commit real credentials to a shared repository. |
| `spring.sql.init.mode` | `embedded` | Runs `schema.sql` and `data.sql` for the embedded H2 database. |

The file also contains commented examples of these defaults or optional settings:

```properties
spring.h2.console.path=/h2-console
spring.h2.console.enabled=false
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.sql.init.schema-locations=optional:classpath:newPackage/newName.sql
spring.sql.init.data-locations=optional:classpath:newPackage/newName.sql
```

## H2 Console

With the application running, open:

```text
http://localhost:8080/h2-console
```

Use the same JDBC URL, username, and password configured in `application.properties`. Example query:

```sql
SELECT * FROM companies;
```

## Project layout

```text
src/main/java/com/ahmed/jobportal/
├── JobportalApplication.java             # application entry point
├── config/web/WebConfig.java              # API prefix and version configuration
└── company/controller/CompanyController.java
src/main/resources/
├── application.properties                 # Spring Boot configuration
├── schema.sql                             # companies table definition
└── data.sql                               # sample companies
```
