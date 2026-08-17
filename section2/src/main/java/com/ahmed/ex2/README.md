# Exercise 2 — Bean names, aliases, and descriptions

This example assigns explicit names to `Vehicle` beans, including multiple aliases for one bean, then retrieves them by those names.

## What you learn

- Use `@Bean(name = ...)`, `@Bean(value = ...)`, or `@Bean(...)` to override a default bean name.
- A single bean can have multiple aliases with `@Bean({"name", "alias"})`.
- `@Description` adds documentation metadata to a bean definition.
