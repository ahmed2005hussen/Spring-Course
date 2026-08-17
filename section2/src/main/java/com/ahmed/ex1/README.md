# Exercise 1 — Declaring and retrieving beans

This example creates Spring beans with `@Bean` methods in `ProjectConfig` and retrieves a `Vehicle` from the application context by its bean name and type.

## What you learn

- A `@Configuration` class defines beans for the Spring container.
- A `@Bean` method's default bean name is its method name.
- When several beans share a type, `getBean(Vehicle.class)` is ambiguous; retrieve the desired bean by name and type instead.
