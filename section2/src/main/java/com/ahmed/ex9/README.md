# Exercise 9 — Constructor dependency injection

Spring discovers `Car` and `Engine` through component scanning. `Car` receives its `Engine` through its constructor, then the example retrieves and prints both beans.

## What you learn

- Constructor injection makes a bean's required dependencies explicit.
- With one constructor, Spring automatically uses it; `@Autowired` is optional.
- Spring creates the `Engine` before constructing the dependent `Car`.
- Field and setter injection are shown as commented alternatives, but constructor injection is the preferred approach for required dependencies.
