# Exercise 11 — Bean scopes and lazy initialization

This exercise compares Spring's two common bean scopes: **singleton** and
**prototype**. `ProjectConfig` component-scans the `Beans` package, allowing
Spring to discover `MyService` and `UserSession` automatically.

## What happens

- `MyService` is a singleton bean (the default scope). Although the
  `@Scope(SCOPE_SINGLETON)` annotation makes that explicit, it is optional.
  The class is also marked `@Lazy`, so Spring waits until the first
  `getBean(MyService.class)` call before constructing it.
- Both `m1` and `m2` reference that one `MyService` object, so
  `m1 == m2` prints `true`.
- `UserSession` has prototype scope. Spring creates a fresh instance every
  time it is requested from the application context.
- Therefore `s1 == s2` prints `false`.

## What you learn

- `@ComponentScan` discovers classes annotated with `@Component`.
- Singleton is Spring's default bean scope: one instance per application
  context.
- Prototype scope creates a new instance for each bean lookup.
- `@Lazy` postpones singleton creation until the bean is first needed, rather
  than creating it during context startup.
- Reference comparison with `==` is a simple way to observe whether two bean
  lookups returned the same object.

## Expected console output

The exact order shows when each object is created:

```text
Created Service
true
Created user
Created user
false
```
