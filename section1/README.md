# Spring Core — Java Configuration and Beans

This project is a small Spring Core exercise that demonstrates how to create and use a Spring application context with Java-based configuration.

## What this project does

`ProjectConfig` is a configuration class marked with `@Configuration`. It defines three Spring beans using `@Bean` methods:

- `Vehicle` — a custom object with its `name` set to `"vvvv"`.
- `String` — a bean named `hello` with the value `"hello"`.
- `Integer` — a bean named `luckyNumber` with the value `10`.

`Main` creates an `AnnotationConfigApplicationContext` using `ProjectConfig`, then reads and prints these beans.

## What we learn

- How `@Configuration` identifies a Java class that supplies Spring configuration.
- How `@Bean` registers an object in the Spring IoC container.
- How Spring assigns the bean name from the `@Bean` method name by default.
- How to retrieve a bean by its type, for example `context.getBean(Vehicle.class)`.
- How to retrieve a bean by its name, for example `context.getBean("vehicle")`.
- Why `getBean("hello")` returns `Object`, and why a cast is needed when retrieving a bean by name:

```java
String greeting = (String) context.getBean("hello");
```

## Project structure

```text
src/main/java/com/ahmed/
├── Main.java                       # Creates and uses the Spring context
├── Beans/Vehicle.java               # Simple custom bean
└── config/ProjectConfig.java        # Java configuration and bean definitions
```

## Requirements

- Java 25
- Maven

The project uses Spring Context `7.0.8`.
