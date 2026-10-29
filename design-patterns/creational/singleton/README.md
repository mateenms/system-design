# Singleton Design Pattern

> Ensure a class has **only one instance** and provide a **global access point** to it.

**Category:** Creational

## When to use

- Exactly one object must coordinate something shared: application configuration, logger, connection-pool manager, cache.
- Creating a second instance would be wrong or wasteful.

## Structure

1. A **private constructor**, so nobody outside can call `new`.
2. A **private static** field that holds the single instance.
3. A **public static** `getInstance()` method that returns it.
4. A **final** class, so it cannot be subclassed into a second type.

## Code

| File | What it shows |
|------|---------------|
| [code/bad/AppConfigBad.java](singleton/code/bad/AppConfigBad.java) | Lazy Singleton without thread safety: 20 threads create 20 "singletons" |
| [code/good/AppConfig.java](singleton/code/good/AppConfig.java) | Best-practice version using the initialization-on-demand holder idiom |

Run from `design-patterns/creational/singleton/code`:

```bash
javac -d out bad/*.java good/*.java
java -cp out patterns.singleton.bad.AppConfigBad
java -cp out patterns.singleton.good.AppConfig
```

## Why the good version is correct

| Concern | How it is handled |
|---------|-------------------|
| Single instance | Created once inside the `Holder` class |
| Thread safety | The JVM initialises a class exactly once, safely; no `synchronized` or `volatile` needed |
| Lazy creation | `Holder` loads only when `getInstance()` is first called |
| Reflection | Constructor throws if the instance already exists |
| Shared state | Fields are `final` and immutable, so any thread can read safely |

## Other ways to implement it (know these for interviews)

| Approach | Lazy | Thread-safe | Note |
|----------|------|-------------|------|
| Eager (`static final INSTANCE`) | No | Yes | Simplest; fine when creation is cheap |
| `synchronized getInstance()` | Yes | Yes | Locks on every call, so slower |
| Double-checked locking | Yes | Yes | Field **must** be `volatile` |
| Holder idiom (used here) | Yes | Yes | Simple and fast |
| Enum (`enum X { INSTANCE }`) | No | Yes | Also safe against reflection and serialization; cannot extend a class |

## Interview questions

**1. What is the Singleton pattern?**
A creational pattern that guarantees one instance of a class and a global access point to it.

**2. How do you make it thread-safe?**
Use eager initialisation, the holder idiom, an enum, or double-checked locking with a `volatile` field. A plain lazy `if (instance == null)` is not safe.

**3. Why must the field be `volatile` in double-checked locking?**
Without it, the compiler or CPU may reorder the writes so another thread sees a non-null reference to an object that is not fully constructed.

**4. How can a Singleton be broken?**
Reflection (`setAccessible(true)` on the constructor), serialization (deserializing creates a new object; fix with `readResolve()`), cloning (do not implement `Cloneable`), and multiple class loaders. An enum Singleton resists the first three.

**5. Why is the enum approach recommended?**
The JVM guarantees one instance and blocks reflection and serialization attacks. Limitation: an enum cannot extend another class.

**6. What are the drawbacks of Singleton?**
It is global state in disguise, which hides dependencies and makes unit testing harder because it cannot be easily replaced by a mock. It also gives the class two jobs (its own logic plus controlling its lifecycle), which strains the Single Responsibility Principle.

**7. How does this relate to Spring?**
Spring beans are singleton-scoped by default, but the container manages them and injects them through constructors. You get one shared instance without a static `getInstance()`, and tests can still swap in a mock. This is the preferred approach in Spring applications (see Dependency Inversion in [solid-principles](../../../solid-principles/)).

**8. Is a Singleton one instance per JVM?**
One per class loader, not strictly one per JVM. It is also per process, so it is not shared across multiple servers.
