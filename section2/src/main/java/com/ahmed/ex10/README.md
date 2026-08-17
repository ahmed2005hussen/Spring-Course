# Exercise 10 — Programmatic conditional bean registration

This exercise uses Spring's `BeanRegistrar` API to decide which beans should
exist in the application context at startup. `ProjectConfig` imports
`MyBeanRegister`, which generates a random number and registers a different
set of beans depending on whether that number is even or odd.

## What happens

- **Even number:** registers an `Engine` bean and a `Vehicle` bean. The
  `Vehicle` supplier obtains the registered `Engine` from the bean supplier
  context and passes it to the `Vehicle` constructor.
- **Odd number:** registers only a `Bike` bean.
- `Example10` checks for each possible bean with `containsBean(...)` before
  retrieving and printing it, because not every bean is registered on every
  run.

## What you learn

- `@Import` can add a `BeanRegistrar` to Java-based Spring configuration.
- `BeanRegistry#registerBean(...)` registers beans programmatically, without
  declaring `@Bean` methods.
- A bean supplier can customize an object before Spring exposes it as a bean.
- Programmatic registration can be conditional, and registered beans can
  depend on other beans registered in the same context.

Run the example more than once to see both registration paths.
