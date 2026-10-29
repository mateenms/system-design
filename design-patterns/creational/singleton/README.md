# Singleton Design Pattern

> Make sure a class has **only one object**, and give everyone one place to get it.

**Category:** Creational

## When to use

When one shared object is enough and a second one would be wrong: app configuration, logger, connection pool, cache.

## How to build one (3 rules)

1. Make the constructor **private**, so nobody can call `new`.
2. Keep the one object in a **private static** field.
3. Add a **public static `getInstance()`** method that returns it.

## Code

| File | What it shows |
|------|---------------|
| [code/bad/AppConfigBad.java](code/bad/AppConfigBad.java) | No Singleton: `new` gives a different object every time |
| [code/good/AppConfig.java](code/good/AppConfig.java) | Simplest Singleton (eager): created when the class loads |
| [code/good/LazyAppConfig.java](code/good/LazyAppConfig.java) | Lazy Singleton: created on first use, safe with threads |

Run from this folder:

```bash
javac -d out code/bad/*.java code/good/*.java
java -cp out patterns.singleton.bad.AppConfigBad
java -cp out patterns.singleton.good.AppConfig
java -cp out patterns.singleton.good.LazyAppConfig
```

## Eager vs Lazy

| | Eager (`AppConfig`) | Lazy (`LazyAppConfig`) |
|--|--|--|
| Created | When the class loads | At first `getInstance()` call |
| Thread-safe | Yes, automatically | Yes, because of `synchronized` |
| Best for | Cheap objects you will surely use | Slow objects you may never use |

Other versions you may be asked about: double-checked locking (needs a `volatile` field), the holder idiom, and the enum Singleton (`enum X { INSTANCE }`), which is the safest.

## Interview questions

**1. What is the Singleton pattern?**
A class that allows only one object and gives a global way to get it.

**2. How do you create one?**
Private constructor, private static instance, public static `getInstance()`.

**3. What is the difference between eager and lazy?**
Eager creates the object when the class loads. Lazy creates it the first time someone asks for it.

**4. Is a lazy Singleton thread-safe?**
Not by default. Two threads can both see `null` and create two objects. Fix it with `synchronized`, double-checked locking, the holder idiom, or an enum.

**5. How can a Singleton be broken?**
Reflection, serialization, and cloning can all create a second object. An enum Singleton protects against all three.

**6. What are the drawbacks?**
It is global state, so it hides dependencies and makes unit tests harder because you cannot easily swap it for a mock.

**7. How does Spring handle this?**
Spring beans are singletons by default. Spring creates one object and injects it where needed, so you do not write `getInstance()` yourself and tests can still use a mock.
