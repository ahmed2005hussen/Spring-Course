# Exercise 3 — Choosing a primary bean

Several `Vehicle` beans are registered in the context. One is marked with `@Primary`, so Spring chooses it whenever the code requests a `Vehicle` only by type.

## What you learn

- Multiple beans may have the same Java type.
- `@Primary` resolves type-based ambiguity by setting the default candidate.
- Repeated `getBean(Vehicle.class)` calls return the same singleton bean by default.
