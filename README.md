# Spring Course Notes

This repository contains the code and examples from a Spring course. The sections build from Spring Core fundamentals to creating REST APIs and starting a database-backed Spring Boot application.

## Course roadmap

| Section | Topic | Main outcome |
| --- | --- | --- |
| [Section 1](section1/) | Spring Core basics | Create, configure, and retrieve beans from the Spring container. |
| [Section 2](section2/) | Spring Core dependency injection | Learn bean selection, component scanning, lifecycle, scopes, and dependency injection. |
| [Section 3](section3/) | Spring MVC and REST APIs | Build REST endpoints that read HTTP requests, return responses, and support API versioning. |
| [Section 4](section4/) | Job Portal application foundation | Set up a Spring Boot project for a web application with JPA and an H2 database. |

## Section 1 — Spring Core: Java configuration and beans

This section introduces the Spring Inversion of Control (IoC) container. Instead of creating and managing every object manually, we declare objects as **beans** and let Spring manage them.

What we learned:

- Create a Java configuration class with `@Configuration`.
- Register objects in the application context with `@Bean`.
- Understand default bean names: a bean normally uses its `@Bean` method name.
- Create an `AnnotationConfigApplicationContext`.
- Retrieve beans by type or by name and type.
- Recognize that looking up a bean by name returns `Object`, so it may need casting.

The example defines `Vehicle`, `String`, and `Integer` beans, then reads them from the application context. See the [Section 1 README](section1/README.md) for more detail.

## Section 2 — Spring Core: bean management and dependency injection

This section expands Spring Core through eleven small exercises. It focuses on how Spring finds, selects, creates, connects, and destroys beans.

What we learned:

- **Bean names and aliases:** customize bean names with `@Bean(name = ...)` and give a bean multiple aliases.
- **Multiple beans of one type:** understand why type-only lookup can be ambiguous and resolve it with names or `@Primary`.
- **Configuration composition:** split configuration across classes and combine them using `@Import`.
- **Component scanning:** use `@Component` and `@ComponentScan` so Spring automatically discovers beans.
- **Bean lifecycle:** run initialization and cleanup code using `InitializingBean` / `DisposableBean`, then the less coupled `@PostConstruct` / `@PreDestroy` annotations.
- **Wiring dependencies:** connect beans declared with `@Bean` methods, including method-parameter injection.
- **Constructor injection:** provide required dependencies through a class constructor; this is the preferred approach for required collaborators.
- **Programmatic registration:** use Spring's `BeanRegistrar` API to conditionally register beans at startup.
- **Bean scopes:** compare the default singleton scope with prototype scope.
- **Lazy initialization:** use `@Lazy` to postpone creation of a singleton bean until it is first requested.

Each exercise has its own short README under `section2/src/main/java/com/ahmed/ex1` through `ex11`.

## Section 3 — Spring MVC: request handling and API versioning

This section moves to Spring Boot and web development. The `backend` project contains focused REST endpoints that demonstrate how a server receives an HTTP request and creates an HTTP response.

What we learned:

- Create a Spring Boot web application and REST controllers with `@RestController`.
- Map URLs and HTTP methods with `@RequestMapping`, `@GetMapping`, and `@PostMapping`.
- Limit mappings by supported methods, consumed media types, and produced media types.
- Read query-string values with `@RequestParam`.
- Read values from a URL path with `@PathVariable`.
- Read individual headers or all headers with `@RequestHeader` and `HttpHeaders`.
- Convert JSON request bodies to Java DTOs with `@RequestBody`.
- Inspect the whole HTTP request with `RequestEntity`.
- Build explicit status codes, headers, and bodies with `ResponseEntity`.
- Compare URL, query parameter, header, and media-type API versioning with Spring Framework 7's built-in API versioning support.

See the [Section 3 README](section3/README.md) for endpoint tables and `curl` examples.

## Section 4 — Job Portal: application and persistence setup

This section begins a larger Job Portal application. The current project establishes the Spring Boot foundation needed for a database-backed web application.

What we learned and prepared:

- Create a Spring Boot application entry point with `@SpringBootApplication`.
- Add Spring MVC for web controllers and HTTP endpoints.
- Add Spring Data JPA for working with entities and databases through Java objects and repositories.
- Add the in-memory H2 database for local development and experimentation.
- Add Spring Boot DevTools to speed up development with restart-related tooling.
- Use Maven and the Maven Wrapper to run and test a Spring Boot application consistently.

As this section develops, it is the place to add the Job Portal domain model, repositories, services, controllers, and user-facing features.

## Requirements

- Java 25
- Maven, or the Maven Wrapper included in the Spring Boot projects
- An IDE such as IntelliJ IDEA (recommended)

## Running the projects

### Sections 1 and 2

These are Spring Core examples. Run the selected example class from your IDE, or compile the project with Maven:

```bash
cd section1
mvn compile
```

```bash
cd section2
mvn compile
```

### Section 3 backend

```bash
cd section3/backend
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`.

### Section 4 Job Portal

```bash
cd section4/jobportal
./mvnw spring-boot:run
```

## Suggested learning order

1. Start with Section 1 to understand beans and the application context.
2. Work through Section 2 exercises in numerical order.
3. Use Section 3 to practise endpoints with a browser, Postman, or `curl`.
4. Continue Section 4 to apply the concepts in a larger, database-backed application.
