# Section 2 — Spring Core: Bean Management and Dependency Injection

This section builds on the basic bean configuration introduced in Section 1. It contains eleven focused exercises that show how Spring creates, finds, selects, configures, connects, and manages the lifecycle of application objects.

The goal is to understand the Spring IoC container before moving on to Spring Boot and REST APIs.

## What you will learn

- How Spring identifies beans by type, name, and alias.
- How to handle multiple beans with the same type using explicit names and `@Primary`.
- How to split and combine Java configuration with `@Import`.
- How component scanning finds classes annotated with `@Component`.
- How to run bean initialization and destruction callbacks.
- How to inject one bean into another using `@Bean` methods and constructors.
- How to register beans programmatically with `BeanRegistrar`.
- The difference between singleton and prototype scopes.
- How `@Lazy` delays a bean's creation until it is needed.

## Exercises

| Exercise | Topic | Key annotations / APIs |
| --- | --- | --- |
| [Exercise 1](src/main/java/com/ahmed/ex1/README.md) | Declaring and retrieving beans | `@Configuration`, `@Bean` |
| [Exercise 2](src/main/java/com/ahmed/ex2/README.md) | Bean names, aliases, and descriptions | `@Bean(name = ...)`, `@Description` |
| [Exercise 3](src/main/java/com/ahmed/ex3/README.md) | Choosing a default bean | `@Primary` |
| [Exercise 4](src/main/java/com/ahmed/ex4/README.md) | Combining configuration classes | `@Import` |
| [Exercise 5](src/main/java/com/ahmed/ex5/README.md) | Automatic bean discovery | `@Component`, `@ComponentScan` |
| [Exercise 6](src/main/java/com/ahmed/ex6/README.md) | Lifecycle interfaces | `InitializingBean`, `DisposableBean` |
| [Exercise 7](src/main/java/com/ahmed/ex7/README.md) | Lifecycle annotations | `@PostConstruct`, `@PreDestroy` |
| [Exercise 8](src/main/java/com/ahmed/ex8/README.md) | Beans depending on beans | `@Bean` method injection |
| [Exercise 9](src/main/java/com/ahmed/ex9/README.md) | Constructor dependency injection | Constructor injection |
| [Exercise 10](src/main/java/com/ahmed/ex10/README.md) | Conditional programmatic registration | `BeanRegistrar`, `BeanRegistry` |
| [Exercise 11](src/main/java/com/ahmed/ex11/README.md) | Scopes and lazy initialization | `@Scope`, `@Lazy` |

## How to use this section

1. Read the exercise README.
2. Open its `Example*.java` class and configuration class.
3. Run the example from your IDE.
4. Change one thing at a time—for example, remove `@Primary`, change a scope, or close the context—and observe Spring's behavior.

## Important ideas

### The IoC container

`AnnotationConfigApplicationContext` is the Spring container used by these exercises. It reads configuration, creates beans, injects dependencies, and manages bean lifecycle callbacks.

### Dependency injection

Instead of a class creating the objects it needs, Spring supplies those dependencies. Constructor injection, shown in Exercise 9, is the preferred style for dependencies that a class cannot work without.

### Bean scope

Spring beans are singleton by default: one object exists for each application context. A prototype bean is different: Spring creates a new object every time the bean is requested.

## Requirements

- Java 25
- Maven
- Spring Context 7.0.8

## Compile the project

```bash
cd section2
mvn compile
```

Then run the desired `Example*.java` class from your IDE.
