# Exercise 6 — Bean lifecycle interfaces

The component-scanned `Vehicle` implements Spring's `InitializingBean` and `DisposableBean` interfaces to run code after creation and before destruction.

## What you learn

- `afterPropertiesSet()` runs after Spring creates and configures a bean.
- `destroy()` runs when the application context is closed.
- Closing `AnnotationConfigApplicationContext` triggers destruction callbacks for managed singleton beans.
- Spring lifecycle interfaces work, though annotation-based callbacks are generally less coupled to Spring.
