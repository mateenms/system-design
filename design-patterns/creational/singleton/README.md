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
| [code/good/EagerSingleton.java](code/good/EagerSingleton.java) | Created when the class loads |
| [code/good/LazySingleton.java](code/good/LazySingleton.java) | Created on first use, `synchronized` |
| [code/good/BillPughSingleton.java](code/good/BillPughSingleton.java) | Lazy and thread-safe with an inner holder class |
| [code/good/EnumSingleton.java](code/good/EnumSingleton.java) | Safest: protected from reflection and serialization |
| [code/good/SingletonDemo.java](code/good/SingletonDemo.java) | Runs all four and checks they return the same object |

Run from this folder:

```bash
javac -d out code/bad/*.java code/good/*.java
java -cp out patterns.singleton.bad.AppConfigBad
java -cp out patterns.singleton.good.SingletonDemo
```

## Which one should I use?

| Approach | Lazy | Thread-safe | Reflection / serialization safe | Complexity |
|----------|:----:|:-----------:|:-------------------------------:|------------|
| Eager | No | Yes | No | Very simple |
| Lazy (`synchronized`) | Yes | Yes | No | Simple, a bit slower |
| Bill Pugh (holder) | Yes | Yes | No | Simple |
| Enum | No | Yes | Yes | Simplest |

**Rule of thumb:** use **Enum** by default. Use **Bill Pugh** if you need lazy creation or must extend a class. Use **Eager** when the object is cheap. In Spring, let the container manage a singleton bean instead.

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
