# 🔐 Section 10 — Spring Security Fundamentals & Internal Flow

Section 10 introduces **Spring Security** — how it protects a Spring Boot application out of the box, how to customize authentication and authorization rules, and how requests actually flow through its internal components.

---

## 📚 Topics Covered

* 🔐 Introduction to Spring Security
* ⚙️ Default (zero-configuration) behavior
* 🔑 Default credentials & custom user configuration
* 🧱 `defaultSecurityFilterChain` and `SecurityFilterChainConfiguration`
* 🚫 Disabling Form Login
* 🚫 Disabling HTTP Basic Login
* ✅ `permitAll()` configuration
* ⛔ `denyAll()` configuration
* 🛠️ Custom `SecurityFilterChain` configuration (path-based rules, regex matchers)
* 🌍 CORS configuration with Spring Security
* 🔄 Spring Security internal flow
* 📦 Analogy of Spring Security internal flow with SDLC

---

## 🔐 Spring Security Introduction

Spring Security is a powerful framework that helps protect Spring applications.

* Handles **authentication** (who are you?) and **authorization** (what can you access?)
* Works seamlessly with Spring Boot
* Can protect both web and REST APIs
* Highly customizable

### Why use Spring Security?

* Protects against common attacks like CSRF, CORS misconfiguration, session fixation
* Easy login systems — adds username/password checks quickly
* Customizable — you can tweak it to fit your needs
* Easily integrates with databases, LDAP, OAuth2 / JWT / SAML

---

## ⚙️ Default Behavior (Zero Configuration) of Spring Security

Just by adding this dependency to `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### What happens automatically?

* A login form is shown
* A default user is created
* Console logs show the default password
* **All URLs are secured by default**

No extra code is needed — Spring Security locks down the entire application the moment the starter is on the classpath.

---

## 🔑 Default Credentials & Custom Configuration

By default, Spring Security provides the following credentials:

* Default username → `user`
* Default password → **randomly generated in the console at startup**

The password changes every time the application restarts, so this is meant only for development / testing — never for production.

### Disabling the generated password & defining our own

We can override the defaults using the following properties:

```properties
spring.security.user.name=madan
spring.security.user.password=Madan@123
spring.security.user.roles=USER,ADMIN
```

---

## 🧱 Default Spring Security Configurations

By default, the Spring Security framework protects **all** the paths present inside the web application. This behavior comes from the code inside the `defaultSecurityFilterChain(HttpSecurity http)` method of the `SecurityFilterChainConfiguration` class:

```java
@Bean
@Order(SecurityFilterProperties.BASIC_AUTH_ORDER)
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
    http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
    http.formLogin(withDefaults());
    http.httpBasic(withDefaults());
    return http.build();
}
```

This is the baseline behavior that our own custom configuration will override.

---

## 🚫 Disable Form Login

If Spring Security–protected endpoints are only accessed programmatically (using APIs) by clients — such as mobile apps or other services — and there's no need for a web-based login form, disabling form login can be appropriate:

```java
@Bean
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
    return http
            .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
            .formLogin(flc -> flc.disable())
            .httpBasic(withDefaults())
            .build();
}
```

## 🚫 Disable HTTP Basic Login

If the application primarily serves web pages and requires users to interact via a browser, then HTTP Basic authentication may not be required. HTTP Basic–style logic can be disabled like this:

```java
@Bean
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
    return http
            .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
            .formLogin(withDefaults())
            .httpBasic(hbc -> hbc.disable())
            .build();
}
```

---

## ✅ `permitAll()` Configuration

We can permit all requests coming toward our web application APIs/paths using Spring Security:

```java
@Configuration
@EnableWebSecurity
public class JobPortalSecurityConfig {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll())
                .formLogin(flc -> flc.disable())
                .httpBasic(withDefaults()).build();
    }
}
```

> ⚠️ **Not recommended for production** — permitting every request removes all protection.

## ⛔ `denyAll()` Configuration

We can also deny all requests coming toward our web application APIs/paths:

```java
@Configuration
@EnableWebSecurity
public class JobPortalSecurityConfig {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll())
                .formLogin(flc -> flc.disable())
                .httpBasic(withDefaults()).build();
    }
}
```

> ⚠️ **Not recommended for production** — this blocks every single request.

---

## 🛠️ Custom Spring Security Configurations

We can secure the web application's APIs/paths based on our own custom requirements:

```java
@Bean
SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
    return http.csrf(csrfConfig -> csrfConfig.disable())
            .authorizeHttpRequests((requests) ->
                    requests.requestMatchers("/api/companies/public").permitAll()
                            .requestMatchers("/api/contacts/public").permitAll()
                            .requestMatchers(RegexRequestMatcher.regexMatcher(".*public$")).permitAll())
            .formLogin(flc -> flc.disable())
            .httpBasic(withDefaults())
            .build();
}
```

A more structured version that separates **public** and **secured** paths explicitly:

```java
@Bean
SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
    return http.csrf(csrfConfig -> csrfConfig.disable())
            .authorizeHttpRequests(requests -> {
                publicPaths.forEach(path -> requests.requestMatchers(path).permitAll());
                securedPaths.forEach(path -> requests.requestMatchers(path).authenticated());
                requests.anyRequest().denyAll();
            })
            .formLogin(flc -> flc.disable())
            .httpBasic(withDefaults())
            .build();
}
```

* Public paths → open to everyone (`permitAll()`)
* Secured paths → require authentication
* Anything else → denied by default (`denyAll()`), which is a safer fallback than leaving it unmatched

---

## 🌍 Spring Security CORS Configurations

Spring Security also lets us configure CORS rules to control which origins can call our APIs:

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
    config.setAllowedMethods(Collections.singletonList("*"));
    config.setAllowedHeaders(Collections.singletonList("*"));
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}
```

This CORS configuration is then wired into the `SecurityFilterChain`:

```java
@Bean
SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
    return http.csrf(csrfConfigurer -> csrfConfigurer.disable())
            .cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> { ... })
            .formLogin(withDefaults())
            .httpBasic(withDefaults()).build();
}
```

---

## 🔄 Spring Security Internal Flow

When a user submits credentials, the request travels through a well-defined chain of components:

```
User entered
credentials ──1──▶ Spring Security Filters ──2──▶ Authentication
                          │  ▲                          │
                          3  8                           
                          ▼  │
                  Authentication Manager ──4──▶ Authentication Providers
                                                        │      │
                                                        5      6
                                                        ▼      ▼
                                          UserDetailsManager/Service   PasswordEncoder
                          │
                          9
                          ▼
                   Security Context
                          │
                         10
                          ▼
                    Response to user
```

### ⭐ Spring Security Filters
A series of Spring Security filters intercept each request & work together to identify if authentication is required or not. If required, they accordingly navigate the user to the login page, or use the existing details stored during initial authentication.

### ⭐ Authentication
Filters like `UsernamePasswordAuthenticationFilter` extract username/password from the HTTP request & prepare an `Authentication`-type object. Authentication is the core standard for storing authenticated user details inside the Spring Security framework.

### ⭐ AuthenticationManager
Once it receives the request from the filter, it delegates the validation of user details to the available authentication providers. Since an app can have multiple providers, it's the `AuthenticationManager`'s responsibility to manage all the available authentication providers. In simple words, the authentication manager takes responsibility *for* authentication.

### ⭐ AuthenticationProvider
`AuthenticationProvider`s hold all the core logic of validating user details for authentication.

### ⭐ UserDetailsManager / UserDetailsService
Helps in retrieving, creating, updating, and deleting user details from the DB/storage systems.

### ⭐ PasswordEncoder
A service interface that helps in encoding & hashing passwords — otherwise we'd have to live with plain-text passwords 😬.

### ⭐ SecurityContext
Once the request has been authenticated, the `Authentication` object is usually stored in a thread-local `SecurityContext`, managed by the `SecurityContextHolder`. This helps during upcoming requests from the same user.

---

## 📦 Analogy of Spring Security Internal Flow with SDLC

To make the flow easier to remember, it can be mapped to a typical Software Development Life Cycle:

Just like an end user provides credentials, a Client/Business person provides requirements.

| Spring Security Component | SDLC Equivalent |
|---|---|
| Spring Security Filters | Business Analysts / Product Owners |
| Authentication | User Story |
| AuthenticationManager | Delivery Manager |
| AuthenticationProvider | Team Leads |
| UserDetailsManager/Service | Developers/QAs |
| PasswordEncoder | Security Guy |
| SecurityContext | Jira |

The request/response cycle mirrors the same back-and-forth: requirements flow from the client, through business owners and delivery management, down to team leads and developers, and the result is tracked and stored (Jira) before flowing back to the client — just like credentials flow through filters, the authentication manager, and providers before being stored in the `SecurityContext` and a response is returned to the user.

---

## 🛠️ Technologies Used in This Section

```text
🔐 Spring Security
🌱 Spring Boot
🌍 CORS
🧾 Jakarta / HttpSecurity DSL
```

---

### 🚀 Keep Learning. Keep Building. Keep Improving.