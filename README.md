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
| 💼 [Section 4](section4/) | Job Portal Application                 | Spring Boot, JPA, H2 & Database-backed Applications        |

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

This section begins a larger **Job Portal application** and introduces the foundations required for building a real database-backed Spring Boot application.

### 📚 Technologies & Concepts

* 🚀 `@SpringBootApplication`
* 🌐 Spring MVC
* 🗄️ Spring Data JPA
* 🧩 JPA Entities
* 🗃️ H2 Database
* 🔧 Maven
* ♻️ Spring Boot DevTools
* 🧪 Application testing and development workflow

The project will gradually evolve into a complete backend application with concepts such as:

* 👤 Users
* 💼 Jobs
* 🏢 Companies
* 📄 Applications
* 🔐 Authentication & Authorization
* 🗄️ Database persistence
* 🌐 REST APIs

> 🚧 **This section is currently under development as I continue through the course.**

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

# ▶️ Running the Projects

## 🌱 Sections 1 & 2 — Spring Core

Run the selected exercise directly from your IDE.

Or compile the projects using Maven:

```bash
cd section1
mvn compile
```

```bash
cd section2
mvn compile
```

---

## 🌐 Section 3 — Backend

Start the Spring Boot application:

```bash
cd section3/backend
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

You can test the endpoints using:

* Postman
* Browser
* `curl`

---

## 💼 Section 4 — Job Portal

Start the application with:

```bash
cd section4/jobportal
./mvnw spring-boot:run
```

---

# 📖 Learning Approach

My learning approach throughout this course is based on:

**Learn → Implement → Experiment → Break Things → Fix Them → Build Projects**

Rather than only watching the lectures, I am implementing the concepts myself through small exercises and gradually larger applications.

The repository will continue to grow as I progress through the course.

---

# 📈 Progress

This repository is a record of my journey toward becoming a **Java Backend Engineer**.

### Current Focus

* 🌱 Spring Core
* 🚀 Spring Boot
* 🌐 REST APIs
* 🗄️ JPA & Databases
* 🔐 Spring Security

### Coming Next

* 🔐 Authentication & Authorization
* 🗄️ Advanced JPA & Hibernate
* 🧪 Testing
* 🐳 Docker
* ☁️ AWS
* ⚙️ Production-ready backend development

---

## ⭐ Goal

> **Build a strong foundation in Java backend development and become capable of designing, developing, securing, containerizing, and deploying production-ready Spring Boot applications.**

---

### 🚀 Keep Learning. Keep Building. Keep Improving.
