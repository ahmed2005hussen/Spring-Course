# Exercise 7 — Annotation-based lifecycle callbacks

This example replaces Spring lifecycle interfaces with Jakarta's `@PostConstruct` and `@PreDestroy` annotations on a component-scanned `Vehicle`.

## What you learn

- `@PostConstruct` marks a method to run after bean initialization.
- `@PreDestroy` marks a method to run before bean destruction.
- Closing the context invokes `@PreDestroy` callbacks.
- Jakarta lifecycle annotations keep the bean independent of Spring lifecycle interfaces.
