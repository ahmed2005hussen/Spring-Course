# Exercise 8 — Wiring beans declared with `@Bean`

`Person` depends on `Vehicle`. The configuration demonstrates two ways to supply that dependency: calling the `vehicle()` bean method and declaring `Vehicle` as a parameter of the `person1` bean method.

## What you learn

- One Spring bean can depend on another bean.
- `@Configuration` intercepts calls to `@Bean` methods, so `vehicle()` refers to the managed singleton rather than creating an unrelated object.
- Method-parameter injection is a clear way to declare dependencies between `@Bean` methods.
