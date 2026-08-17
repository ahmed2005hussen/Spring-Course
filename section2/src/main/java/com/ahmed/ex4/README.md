# Exercise 4 — Combining configuration classes

`ProjectConfig` imports `AnotherConfig`, making the `helloWorld` bean defined there available in the same application context.

## What you learn

- Split bean definitions across focused `@Configuration` classes.
- Use `@Import` to compose configuration classes.
- Imported configuration is an alternative to listing every configuration class when creating the context.
