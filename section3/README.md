# Spring MVC – Request Handling and API Versioning

This project is a hands-on Spring Boot application for learning how a REST API receives requests and builds responses. It contains small, focused endpoints rather than a complete business application, so each Spring MVC feature can be tested independently with Postman or `curl`.

## What we learned

- Creating a Spring Boot web application with Maven and Java 25.
- Building REST controllers with `@RestController`.
- Mapping URLs and HTTP methods with `@RequestMapping`, `@GetMapping`, and `@PostMapping`.
- Reading query parameters, path variables, request headers, and request bodies.
- Converting JSON to Java objects and Java objects to JSON using Jackson.
- Inspecting a complete HTTP request with `RequestEntity`.
- Creating custom HTTP responses with status codes, headers, and bodies through `ResponseEntity`.
- Restricting endpoints by supported methods, consumed content type, and produced content type.
- Comparing traditional API-versioning patterns with Spring Framework 7's built-in API versioning support.

## Requirements

- Java 25
- Maven (or the included Maven Wrapper)

## Run the application

```bash
cd backend
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080` by default. The port and context path examples are available, but commented out, in `src/main/resources/application.properties`.

## Features and endpoints

### 1. Request mapping

Base path: `/api`

| Endpoint | Methods | Feature demonstrated |
| --- | --- | --- |
| `/api/hello1` | Any | Basic `@RequestMapping` |
| `/api/hello2`, `/api/hello2a` | GET, POST | Multiple URL paths and selected HTTP methods |
| `/api/hello3` | GET, POST | `produces = application/json` |
| `/api/hello4` | GET, POST | `consumes = application/json` |

Example:

```bash
curl http://localhost:8080/api/hello1
curl -X POST http://localhost:8080/api/hello4 \
  -H 'Content-Type: application/json'
```

### 2. Query parameters with `@RequestParam`

Base path: `/api/users`

| Endpoint | Feature demonstrated |
| --- | --- |
| `/search?name=Ahmed&sex=male` | Required parameters |
| `/search2?sex=male` | Optional parameter with `required = false` |
| `/search3?sex=male` | Default parameter value |
| `/search4?name=Ahmed&sex=male` | Naming a request parameter separately from the Java variable |
| `/search5?name=Ahmed&sex=male&a=value` | Reading all parameters into a `Map` |

### 3. Path variables with `@PathVariable`

Base path: `/api/dummy/users`

| Pattern | Feature demonstrated |
| --- | --- |
| `/{userId}/posts` | Extracting a typed path variable |
| `/{userId}/posts/{postId}` | Multiple path variables |
| `/{userId}` | Mapping a URI variable to a differently named Java variable |
| `/{id}/{postId}/{classId}` | Reading path variables into a `Map` |

Examples:

```bash
curl http://localhost:8080/api/dummy/users/10
curl http://localhost:8080/api/dummy/users/10/posts/50
curl http://localhost:8080/api/dummy/users/1/2/3
```

### 4. Request headers with `@RequestHeader`

| Endpoint | Feature demonstrated |
| --- | --- |
| `/header1` | Required `Content-Type` header |
| `/header2` | Optional `User-Location` header |
| `/header3` | Header default value |
| `/header4` | Headers as `Map<String, String>` |
| `/header5` | Headers as `HttpHeaders`, including multi-value headers |

```bash
curl http://localhost:8080/header3
curl http://localhost:8080/header4 \
  -H 'Content-Type: application/json' \
  -H 'User-Location: Cairo'
```

### 5. JSON request body and DTO

`POST /userdto` accepts a JSON `UserDto` object. It demonstrates the Jackson conversion flow:

`JSON request → Java object (`@RequestBody`) → controller → HTTP response`

```bash
curl -X POST http://localhost:8080/userdto \
  -H 'Content-Type: application/json' \
  -d '{"name":"Ahmed","age":22}'
```

`UserDto` contains `name` and `age` fields with standard getters and setters.

### 6. Inspecting the complete request

`POST /requestEntity` accepts `RequestEntity<UserDto>` and reads:

- HTTP headers
- HTTP method
- Full URL
- Query string
- Request path
- Deserialized body

```bash
curl -X POST 'http://localhost:8080/requestEntity?id=10&name=Ahmed' \
  -H 'Content-Type: application/json' \
  -H 'User-Location: Cairo' \
  -d '{"name":"Ahmed","age":22}'
```

### 7. Building a response with `ResponseEntity`

`GET /responseEntity` returns a `UserDto` while explicitly setting:

- Status: `201 Created`
- Header: `Location: el giza`
- JSON body: `{ "name": "ahmed", "age": 22 }`

### 8. API versioning

#### Legacy approaches

Base path: `/api/legacy/versions`

| Approach | Example |
| --- | --- |
| URL path | `/api/legacy/versions/v1`, `/api/legacy/versions/v2` |
| Query parameter | `/api/legacy/versions?version=2` |
| Request header | `X-API-Version: 1` or `X-API-VERSION: 2` |
| Media type | `Accept: application/vnd.ahmed.v1+json` or `application/vnd.ahmed.v2+json` |

#### Spring Framework 7 API versioning

`WebConfig` configures version resolution from the `v` parameter of this media type:

```text
Accept: application/vnd.ahmed+json;v=1.0
```

Supported versions are `1.0`, `2.0`, and `3.0`. The version-aware endpoints are under `/api/versions`:

```bash
curl http://localhost:8080/api/versions \
  -H 'Accept: application/vnd.ahmed+json;v=1.0'

curl http://localhost:8080/api/versions \
  -H 'Accept: application/vnd.ahmed+json;v=2.0'
```

The `2.0+` mapping demonstrates version-range matching for version 2.0 and later supported versions.

## Project structure

```text
backend/
├── src/main/java/com/ahmed/backend/
│   ├── controllers/          # Request and response handling examples
│   ├── versionControllers/   # Legacy and Spring 7 versioning examples
│   ├── config/WebConfig.java # API-versioning configuration
│   └── dto/UserDto.java      # JSON request/response model
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## Test the project

```bash
cd backend
./mvnw test
```
