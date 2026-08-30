# ⚠️ Section 8 — Global Exception Handling, Validation, Auditing, API Docs & Bean Scopes

Section 8 covers how to handle errors consistently across the application, validate incoming request data, automatically audit entity changes with JPA, document REST APIs with Springdoc OpenAPI, and understand Spring's different bean scopes.

---

## 📚 Topics Covered

* ⚠️ Global Exception Handling (`@ExceptionHandler`, `@RestControllerAdvice`)
* ✅ Validations in Spring Boot REST APIs (`@Valid`, `@Validated`, Jakarta Validation annotations)
* 🕒 Auditing in Spring Boot with JPA (`@CreatedDate`, `@CreatedBy`, `@LastModifiedDate`, `@LastModifiedBy`, `AuditorAware`, `@EnableJpaAuditing`)
* 📄 Springdoc OpenAPI (Swagger UI, API documentation)
* 📦 Bean Scopes in Spring (Singleton, Prototype, Request, Session, Application, WebSocket)

---

## ⚠️ Global Exception Handling in Spring Boot

In a Spring Boot application, when an exception occurs, it may cause an application crash or return an unclear error response to the client. Global Exception Handling ensures that errors are handled consistently and meaningful error messages are returned.

**Why Use Global Exception Handling?**
- Provides consistent error responses across the application.
- Improves readability and maintainability of exception handling logic.
- Helps in logging and debugging by capturing errors in a central place.
- Ensures better user experience with meaningful error messages.
- No need to write try and catch blocks in all the methods.

**Ways to Handle Exceptions in Spring Boot:**
- Using `@ExceptionHandler` (Controller-Level Handling)
- Using `@RestControllerAdvice` & `@ExceptionHandler` (Global Exception Handling)

This code defines a global exception handler using `@RestControllerAdvice` that catches all unhandled exceptions in the application. When any exception occurs, it creates an `ErrorResponseDto` containing the request details, error message, status, and timestamp, and returns it with HTTP 500 – Internal Server Error.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                    WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

---

## ✅ Validations in Spring Boot REST APIs

Validation ensures that the data sent in API requests is correct before processing.

**Why Use Validation?**
- Ensures correct and expected data input.
- Prevents invalid data from reaching the database.
- Reduces errors and improves API reliability.
- Helps maintain clean and secure APIs.

To get started with validation, add the below dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Spring Boot provides two key annotations to trigger validation:
- `@Valid` – Used on method custom datatype parameters to enable validation.
- `@Validated` – Used on method primitive datatype parameters to enable validation.

Spring Boot supports field-level validation using Jakarta Validation annotations inside DTOs and Entity classes:

```java
public record ContactRequestDto(

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
        String name,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "UserType cannot be empty")
        @Pattern(regexp = "Job Seeker|Employer|Other",
                message = "UserType must be one of: Job Seeker, Employer, Other")
        String userType,

        @NotBlank(message = "Subject cannot be empty")
        @Size(min = 5, max = 255, message = "Subject must be between 5 and 255 characters")
        String subject,

        @NotBlank(message = "Message cannot be empty")
        @Size(min = 10, max = 1000, message = "Message must be between 10 and 1000 characters")
        String message

);
```

### Applying validation on `@RequestBody`

To apply validation on `RequestBody` data, use `@Valid` inside a controller method.

```java
@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService iContactService;

    @PostMapping
    public ResponseEntity<String> saveContact(@Valid @RequestBody ContactRequestDto contactRequestDto) {

        boolean isSaved = iContactService.saveContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Request processed successfully");
    }
}
```

### Applying validation on `@RequestParam`

To apply validation on `RequestParam`, use the below kind of logic:

```java
@GetMapping
public ResponseEntity<String> fetchOpenContacts(
        @RequestParam @Validated @NotBlank(message = "Status can not be blank") String status) {

    return ResponseEntity.ok("These are the contacts with the given status: " + status);
}
```

**Best Practices for Validations**
- Use DTOs instead of entity classes for request validation.
- Always return meaningful error messages to the client.
- Centralize validation error handling using `@RestControllerAdvice`.

### Global Exception Handling for Validation Errors

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        fieldErrorList.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleException(HandlerMethodValidationException exception) {
        Map<String, String> errors = new HashMap<>();
        List<ParameterValidationResult> results = exception.getParameterValidationResults();
        results.forEach(result -> {
            String paramName = result.getMethodParameter().getParameterName();
            String combinedMessages = result.getResolvableErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "));
            errors.put(paramName, combinedMessages);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
```

---

## 🕒 Auditing in Spring Boot with JPA

Auditing in JPA helps track changes to entities by automatically recording who created or updated a record and when the changes occurred.

**Why Use Auditing?**
- Helps in maintaining logs of data modifications.
- Useful for tracking user actions in the database.
- Reduces manual effort by automatically capturing timestamps and user details.
- Ensures better data integrity and accountability.

**Enable JPA Auditing** — to enable auditing, we need to:
1. Create a `BaseEntity` class to handle auditing fields.
2. Use Spring Security or a custom implementation to capture the user who modified the data.
3. Enable Auditing in Spring Boot using `@EnableJpaAuditing`.

### Step 1: Create a BaseEntity Class

```java
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 20, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", length = 20, insertable = false)
    private String updatedBy;
}
```

### Step 2: Modify the Entity classes to extend BaseEntity

```java
@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    // other fields

}
```

### Step 3: Create an AuditorAware Implementation

We need to tell Spring who the current user is when saving/updating entities. If you use Spring Security, modify the `getCurrentAuditor()` method to return the authenticated username.

```java
@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Anonymous user");
    }
}
```

### Step 4: Enable Auditing in the Main Class

```java
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class EazystoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazystoreApplication.class, args);
    }
}
```

---

## 📄 Springdoc OpenAPI

Documenting REST APIs is essential for making them understandable and usable by other developers. `springdoc-openapi` is a Java library that automatically generates API documentation for your Spring Boot REST APIs. It follows the OpenAPI (Swagger) standard.

**Why Document REST APIs?**
- Helps developers understand available endpoints and their request/response formats.
- Reduces dependency on manual explanations.
- Improves API usability and onboarding.
- Makes integration with other systems easier.

Developers can further enhance the default API documentation using annotations like `@Schema`, `@Tag`, `@Operation`, `@ApiResponse` etc.

To use the Springdoc library, add the below dependency in `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.14</version>
</dependency>
```

Once the dependency is added, start your Spring Boot application, and open:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Spring automatically generates the API documentation! For more details, refer [springdoc.org](https://springdoc.org/)

---

## 📦 Bean Scopes in Spring

Bean scope defines how Spring creates and manages bean instances. It answers two key questions:
- How many instances of a bean will Spring create?
- When will Spring create these instances?

Spring provides six bean scopes:
1. **Singleton** (Default)
2. **Prototype**
3. **Request** (inside web apps)
4. **Session** (inside web apps)
5. **Application** (inside web apps)
6. **WebSocket** (inside web apps)

### Request Bean Scope (`@RequestScope`)

**What is it?** A new bean instance is created for each HTTP request.
**When to use?** When a bean should be valid only for the duration of a request (e.g., storing request-specific data).
**Use case:** Storing temporary user inputs like form data.

```java
@Component
@RequestScope
@Getter @Setter
public class RequestScopedBean {

    private String username;
}
```

**How it works?**
- Each HTTP request gets a new instance of `RequestScopedBean`.
- If multiple users send requests, each gets a separate instance.
- As soon as the request is complete, the bean is destroyed.

### Session Bean Scope (`@SessionScope`)

**What is it?** A new bean instance is created for each user session.
**When to use?** When you need to store user-specific data across multiple requests in a single session.
**Use case:** Storing logged-in user details, shopping cart information.

```java
@Component
@SessionScope
@Getter @Setter
public class SessionScopedBean {

    private String username;
}
```

**How it works?**
- When a user logs in, a `SessionScopedBean` is created.
- The same bean instance is used for multiple requests from the same user.
- When the session expires, the bean is destroyed.

### Application Bean Scope (`@ApplicationScope`)

**What is it?** A single bean instance is shared across the entire application.
**When to use?** When you need global data shared among all users and requests.
**Use case:** Storing global statistics like visitor count.

```java
@Component
@ApplicationScope
@Getter
public class ApplicationScopedBean {
    private int visitorCount = 0;

    public void incrementVisitorCount() {
        visitorCount++;
    }
}
```

**How it works?**
- Only one instance of `ApplicationScopedBean` is created.
- It is shared across all users and requests.
- The bean is destroyed only when the application stops.

### WebSocket Bean Scope (`@WebSocketScope`)

**WebSocket Scope** = a bean that is created for each WebSocket connection and destroyed when that connection closes. A WebSocket is a continuous connection between the client (browser/app) and the server. Unlike HTTP, which is request–response, WebSocket stays open and allows real-time, two-way communication.

**Examples:** Live chat, Stock price updates, Notifications, Multiplayer games

```java
@Component
@WebSocketScope
public class ChatSessionData {

    private String username;
    private String currentRoom;
}
```

**How it works?**
- Each WebSocket client gets its own `ChatSessionData` object.
- They don't interfere with each other.

### Using Scoped Beans in Controllers

```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scope")
public class ScopeController {

    private final RequestScopedBean requestScopedBean;
    private final SessionScopedBean sessionScopedBean;
    private final ApplicationScopedBean applicationScopedBean;

    @GetMapping("/request")
    public String getRequestScope() {
        requestScopedBean.setUsername("JohnDoe");
        return "Request Scope: " + requestScopedBean.getUsername();
    }

    @GetMapping("/session")
    public String getSessionScope() {
        sessionScopedBean.setUsername("JohnDoe");
        return "Session Scope User: " + sessionScopedBean.getUsername();
    }

    @GetMapping("/application")
    public String getApplicationScope() {
        applicationScopedBean.incrementVisitorCount();
        return "Application Visitor Count: " + applicationScopedBean.getVisitorCount();
    }
}
```

### Difference between `@ApplicationScope` and `@Singleton` scope

Use `@ApplicationScope` when working with web applications to store global web-related state. Use Singleton (default scope) for services, repositories, or shared business logic in all types of Spring applications. Singleton beans are managed by the Spring container, whereas Application-scoped beans are managed by the Servlet context.

| Feature | `@ApplicationScope` | `@Singleton` |
|---|---|---|
| Scope | One instance per Spring web application | One instance per Spring container (default scope) |
| Usage | Works only in web applications (Spring MVC, Spring Boot Web) | Works in all Spring applications (Web, CLI, Batch, etc.) |
| Annotation | `@ApplicationScope` | No annotation required (default), but can use `@Scope("singleton")` |
| Instance Sharing | Shared across the entire application for all users and requests | Shared across the entire Spring container |
| Bean Destruction | Destroyed when the web application stops | Destroyed when the Spring container shuts down |
| Use Case | Storing global application state (e.g., visitor count, app-wide config) | Storing singleton service beans (e.g., `@Service`, `@Repository`, `@Component`) |

---

### 🚀 Keep Learning. Keep Building. Keep Improving.