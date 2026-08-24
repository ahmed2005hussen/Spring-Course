# Spring Data JPA and CORS

This project demonstrates how to persist company data with Spring Data JPA and how to allow a frontend application to access the API through CORS configuration.

## Covered topics

- Spring Data repositories
- Spring Data JPA entity mapping
- Repository, service, and DTO layers
- H2 database initialization with `schema.sql` and `data.sql`
- Global CORS configuration for REST endpoints

## Spring Data and Spring Data JPA

### Entity mapping

`Company` is a JPA entity mapped to the `COMPANIES` database table.

- `@Entity` marks the class as a persistent JPA entity.
- `@Table(name = "COMPANIES")` connects the entity to the database table.
- `@Id` and `@GeneratedValue` define the primary key and its auto-generated value.
- `@Column` customizes column names, nullability, length, uniqueness, and numeric precision.
- `@Lob` maps the company description as a large text value.

### Repository layer

`CompanyRepository` extends Spring Data JPA's repository abstraction. Spring creates the implementation automatically, so common database operations such as `findAll()`, `findById()`, `save()`, and `deleteById()` do not require handwritten SQL or implementation code.

### Service and DTO layers

- `CompanyService` defines the company-related application operation.
- `CompanyServiceImpl` gets entities from `CompanyRepository` and maps them to `CompanyDto` objects.
- `CompanyDto` is a Java record returned by the API. Using a DTO keeps persistence details and fields that should not be exposed out of the HTTP response.

### Database initialization

- `schema.sql` creates the `COMPANIES` table.
- `data.sql` inserts sample companies.
- H2 is used as a file-based development database, configured through `spring.datasource.*` properties.
- `spring.sql.init.mode=embedded` instructs Spring Boot to run the SQL initialization scripts for the embedded database.

## CORS

Browsers enforce the same-origin policy, which blocks a frontend from calling an API hosted on a different origin unless the API allows it through CORS (Cross-Origin Resource Sharing).

`WebConfig` implements `WebMvcConfigurer` and configures global CORS rules in `addCorsMappings`:

- The rule applies to all API routes matching `/api/**`.
- Requests are allowed from the Vite frontend origin, `http://localhost:5173/`.
- All HTTP methods and request headers are allowed.
- Response headers are exposed to the frontend.
- Credentials are allowed with `allowCredentials(true)`.
- Browser preflight responses can be cached for 3,600 seconds.

```java
registry.addMapping("/api/**")
        .allowedOrigins("http://localhost:5173/")
        .allowedMethods("*")
        .allowedHeaders("*")
        .exposedHeaders("*")
        .allowCredentials(true)
        .maxAge(3600);
```
