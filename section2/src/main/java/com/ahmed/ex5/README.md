# Exercise 5 — Component scanning

Rather than declaring `Vehicle` with a `@Bean` method, this example marks it as a `@Component` and lets Spring discover it through `@ComponentScan`.

## What you learn

- `@Component` makes a class eligible to become a Spring bean.
- `@ComponentScan` discovers annotated classes in the configured package.
- A component-scanned bean can be retrieved from the context by type.
