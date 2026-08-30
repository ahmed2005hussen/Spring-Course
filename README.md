# 🚀 Java Backend Engineering with Spring Boot

> **A hands-on learning journey through Spring Boot, Spring Security, JPA, REST APIs, Docker, AWS, and production-ready backend development.**

This repository contains my code, exercises, experiments, and projects while learning **Java Backend Development with Spring Boot**.

The goal is to build a strong understanding of the Spring ecosystem — starting from the fundamentals of **Spring Core and Dependency Injection**, then moving into **REST APIs, databases, security, Docker, AWS, and production-ready backend applications**.

---

## 🎓 Course

**Become a Java Backend Engineer with Spring Boot, Spring Security, JPA, REST APIs, Docker, AWS & Production-Ready Skills**

🔗 [Udemy Course](https://www.udemy.com/share/106ofA3@Ao4IR5EawpUW-iRSs5f0Empa7OEO2zsROWRqEWbTdBYYcz3ASZylY4hUBuxOKchjYA==/)

---

## 🗺️ Learning Roadmap

| Section                   | Topic                                  | What I Learned                                             |
| ------------------------- | -------------------------------------- | ---------------------------------------------------------- |
| 🌱 [Section 1](section1/) | Spring Core Basics                     | IoC, Beans, Configuration & Application Context            |
| 🔧 [Section 2](section2/) | Dependency Injection & Bean Management | DI, Component Scanning, Lifecycle, Scopes & Bean Selection |
| 🌐 [Section 3](section3/) | Spring MVC & REST APIs                 | Controllers, HTTP Requests/Responses & API Versioning      |
| 💼 [Section 4](section4/) | Job Portal Application | Spring Boot, H2 & Database-backed Applications |
| 🗄️ [Section 5](section5/) | Spring Data JPA & CORS | JPA, Repositories, DTOs & CORS |
| 🐳 [Section 6](section6/) | Docker & MySQL | Docker, Docker Compose, MySQL & containerized database development |
| 🗄️ [Section 7](section7/) | Schema Management & JPA Buddy | `ddl-auto` modes & JPA Buddy IntelliJ plugin |
| ⚠️ [Section 8](section8/) | Exception Handling, Validation, Auditing, API Docs & Bean Scopes | Global Exception Handling, Validation, JPA Auditing, Springdoc OpenAPI & Bean Scopes |
> 🚧 **More sections will be added as I progress through the course.**

---

# 🌱 Section 1 — Spring Core: Java Configuration & Beans

The first section introduces the **Spring IoC Container** and the fundamental concept of **Beans**.

Instead of manually creating and managing every object, Spring takes responsibility for creating, configuring, and managing application objects.

### 📚 Topics Covered

* `@Configuration`
* `@Bean`
* Spring IoC Container
* `ApplicationContext`
* `AnnotationConfigApplicationContext`
* Bean names and default naming conventions
* Retrieving beans by type
* Retrieving beans by name and type
* Understanding bean lookup and casting

The example application defines `Vehicle`, `String`, and `Integer` beans and retrieves them from the Spring Application Context.

📖 See the [Section 1 README](section1/README.md) for more details.

---

# 🔧 Section 2 — Spring Core: Dependency Injection & Bean Management

Section 2 goes deeper into the Spring Container and focuses on how Spring **discovers, creates, selects, connects, and manages beans**.

### 📚 Topics Covered

* 🏷️ **Bean Names & Aliases**

  * Custom bean names using `@Bean(name = ...)`
  * Multiple aliases for a bean

* 🔀 **Multiple Beans of the Same Type**

  * Resolving ambiguity
  * Using bean names
  * Using `@Primary`

* 🧩 **Configuration Composition**

  * Splitting configuration across multiple classes
  * Using `@Import`

* 🔍 **Component Scanning**

  * `@Component`
  * `@ComponentScan`
  * Automatic bean discovery

* ♻️ **Bean Lifecycle**

  * `InitializingBean`
  * `DisposableBean`
  * `@PostConstruct`
  * `@PreDestroy`

* 🔗 **Dependency Injection**

  * Bean-to-bean dependencies
  * Method parameter injection
  * Constructor injection

* 🧱 **Programmatic Bean Registration**

  * Spring's `BeanRegistrar`
  * Conditional bean registration

* 📦 **Bean Scopes**

  * Singleton
  * Prototype

* 💤 **Lazy Initialization**

  * `@Lazy`
  * Delaying bean creation until needed

Each concept is implemented as a separate exercise under:

`section2/src/main/java/com/ahmed/ex1` → `ex11`

---

# 🌐 Section 3 — Spring MVC & REST APIs

This section marks the transition from **Spring Core** to building real web applications and REST APIs using **Spring Boot and Spring MVC**.

The `backend` project contains multiple focused examples demonstrating how a server receives an HTTP request, processes it, and produces an HTTP response.

### 📚 Topics Covered

* 🚀 Spring Boot Web Applications
* 🎮 `@RestController`
* 🗺️ `@RequestMapping`
* 📥 `@GetMapping`
* 📤 `@PostMapping`
* 🔢 `@RequestParam`
* 🛣️ `@PathVariable`
* 📨 `@RequestHeader`
* 📋 `HttpHeaders`
* 📦 `@RequestBody`
* 🔄 JSON → Java DTO conversion
* 🌍 `RequestEntity`
* 📡 `ResponseEntity`
* 🎯 HTTP Status Codes
* 📄 Request & Response Headers
* 🧾 Consumed Media Types
* 📤 Produced Media Types
* 🔢 API Versioning

A major topic in this section is **Spring Framework 7's built-in API Versioning support**, including:

* URL versioning
* Query parameter versioning
* Header versioning
* Media type versioning

📖 See the [Section 3 README](section3/README.md) for endpoint documentation and `curl` examples.

---

# 💼 Section 4 — Job Portal Application

Section 4 introduces building a **database-backed Spring Boot application** through a Job Portal project.

### 📚 Topics Covered

* 🚀 `@SpringBootApplication` & Spring Boot application structure
* 🌐 Spring MVC & REST Controllers
* 💾 H2 Database
* 📝 SQL database initialization with `schema.sql` & `data.sql`
* 💽 File-based H2 databases & data persistence
* 🖥️ H2 Console
* ♻️ Spring Boot DevTools
* ⚙️ `WebMvcConfigurer` for centralized MVC configuration
* 🛣️ Global API path prefixes using `configurePathMatch`
* 🔢 Spring Framework 7 API Versioning
* ⚙️ `spring.sql.init.mode` and SQL initialization behavior

The project currently provides a versioned `GET /api/companies` endpoint backed by an H2 database and demonstrates how Spring Boot can initialize and persist application data.

📖 See the [Section 4 README](section4/jobportal/README.md) for detailed configuration, database setup, API documentation, and examples.

---

# 🗄️ Section 5 — Spring Data JPA & CORS

Section 5 introduces **Spring Data JPA** for database persistence and expands the Job Portal application with a layered architecture and global CORS configuration.

### 📚 Topics Covered

* 🗄️ Spring Data & Spring Data JPA
* 🧩 JPA Entity Mapping
* 🏷️ `@Entity`, `@Table`, `@Id`, `@GeneratedValue` & `@Column`
* 📦 Spring Data Repository Abstraction
* 🔄 Repository → Service → DTO architecture
* 📋 Java Records as DTOs
* 📝 H2 database initialization with `schema.sql` & `data.sql`
* 🌍 Global CORS configuration with `WebMvcConfigurer`
* 🔐 Cross-Origin Resource Sharing (CORS)

`CompanyRepository` uses Spring Data JPA to provide common database operations such as `findAll()`, `findById()`, `save()`, and `deleteById()` without manually implementing the repository.

`CompanyService` handles the application logic and maps JPA entities to `CompanyDto` objects, keeping persistence models separate from API responses.

The section also configures global CORS rules to allow a frontend application running on `http://localhost:5173` to access the backend API under `/api/**`.

📖 See the [Section 5 README](section5/jobportal/README.md) for detailed entity mapping, repository structure, database configuration, and CORS examples.

---

# 🐳 Section 6 — Docker & MySQL

Section 6 introduces **Docker** and **Docker Compose** for running MySQL as a containerized database instead of installing MySQL directly on the development machine.

### 📚 Topics Covered

* 🐳 Running MySQL with Docker
* 📦 Docker Compose
* 🗄️ MySQL database configuration
* 🔌 MySQL JDBC Driver
* 🌱 Spring Boot Docker Compose integration
* 🔐 Environment variables for datasource configuration
* 💾 Persistent database storage with Docker volumes
* ⚙️ Externalizing database configuration with Spring properties

The application connects to MySQL using configurable environment variables such as `DATABASE_HOST`, `DATABASE_PORT`, `DATABASE_NAME`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD`, with local development defaults.

Docker Compose is used to define and run the MySQL service with its database, credentials, port mapping, and persistent volume configuration.

The section also demonstrates the role of the main dependencies:

* `spring-boot-starter-data-jpa` — JPA/Hibernate support for database persistence.
* `mysql-connector-j` — JDBC driver used to connect the application to MySQL.
* `spring-boot-docker-compose` — integrates Spring Boot with Docker Compose during development.

📖 See the [Section 6 README](section6/jobportal/README.md) for Docker Compose configuration, MySQL setup, datasource properties, and running instructions.

---

# 🗄️ Section 7 — Schema Management (`ddl-auto`) & JPA Buddy Plugin

Section 7 covers how to control Hibernate's database schema management using `spring.jpa.hibernate.ddl-auto`, and how to speed up JPA development in IntelliJ IDEA using the JPA Buddy plugin.

### 📚 Topics Covered

* 🧬 `spring.jpa.hibernate.ddl-auto` values: `create`, `create-drop`, `create-only`, `drop`, `update`, `validate`, `truncate`, `none`
* 🌍 Recommended `ddl-auto` value per environment (dev / testing / production)
* 🐶 JPA Buddy plugin for IntelliJ IDEA (entity generation, relationship builder, repository generator)

📖 See the [Section 7 README](section7/jobportal/README.md) for detailed explanations and comparison tables.

---

# ⚠️ Section 8 — Global Exception Handling, Validation, Auditing, API Docs & Bean Scopes

Section 8 covers how to handle errors consistently, validate incoming request data, automatically audit entity changes with JPA, document REST APIs with Springdoc OpenAPI, and understand Spring's different bean scopes.

### 📚 Topics Covered

* ⚠️ Global Exception Handling (`@ExceptionHandler`, `@RestControllerAdvice`)
* ✅ Validations in Spring Boot REST APIs (`@Valid`, `@Validated`, Jakarta Validation annotations)
* 🕒 Auditing in Spring Boot with JPA (`@CreatedDate`, `@CreatedBy`, `@LastModifiedDate`, `@LastModifiedBy`, `AuditorAware`, `@EnableJpaAuditing`)
* 📄 Springdoc OpenAPI (Swagger UI, API documentation)
* 📦 Bean Scopes in Spring (Singleton, Prototype, Request, Session, Application, WebSocket)

📖 See the [Section 8 README](section8/jobportal/README.md) for detailed explanations and code examples.

---

# 🛠️ Technologies

The technologies covered throughout this learning journey include:

```text
☕ Java
🌱 Spring Framework
🚀 Spring Boot
🌐 Spring MVC
🔐 Spring Security
🗄️ Spring Data JPA
💾 Hibernate
🛢️ H2 / SQL Databases
📡 REST APIs
📦 Maven
🐳 Docker
☁️ AWS
🧪 Postman
🔧 Git & GitHub
```


---

## ⭐ Goal

> **Build a strong foundation in Java backend development and become capable of designing, developing, securing, containerizing, and deploying production-ready Spring Boot applications.**

---

### 🚀 Keep Learning. Keep Building. Keep Improving.
