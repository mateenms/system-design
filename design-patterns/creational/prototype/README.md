# Prototype Design Pattern

> Create a new object by **copying an existing one**, instead of building it from scratch.

**Category:** Creational

## When to use

- Creating the object is slow or costly (reading a file, a database call), but copying it is cheap.
- You need many objects that are almost the same, and each one changes only a little (an email template sent to many students).

## How to build one (4 steps)

1. Add a **copy constructor** that takes another object of the same class.
2. Copy simple fields (`String`, `int`) directly.
3. Copy mutable fields (`List`, `Date`) into **new objects**, so the copy never shares them.
4. Add a `copy()` method that returns `new MyClass(this)`.

## The Prototype interface

[Prototype.java](src/patterns/prototype/character/Prototype.java) is a small generic interface. `T` is the type of the copy, so any class can use it:

```java
public interface Prototype<T> {
    T copy();
}
```

The Character example uses it: `Character implements Prototype<Character>`.

## Examples

Each example has its own README with its own steps.

| Example | What it shows |
|---------|---------------|
| [Student](src/patterns/prototype/student/README.md) | Copy an object with a list. Bad (shared list) vs good (own list) |
| [EmailTemplate](src/patterns/prototype/template/README.md) | Load a slow template once, copy it for each person |
| [Character](src/patterns/prototype/character/README.md) | Copy Human, Zombie and Plant, each type copies itself |
| [College](src/patterns/prototype/college/README.md) | One ready-made college of Maharashtra, copied for MGM COE and Matoshiri COE (Nanded) and Bidve COE (Latur) |

Run all of them from this folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.prototype.student.bad.StudentBad
java -cp out patterns.prototype.student.good.StudentDemo
java -cp out patterns.prototype.template.TemplateDemo
java -cp out patterns.prototype.character.CharacterDemo
java -cp out patterns.prototype.college.CollegeDemo
```

## Shallow copy vs deep copy

```java
// Shallow (bad): both objects point to the SAME list
new StudentBad(name, subjects);

// Good: the copy gets its OWN list with the same items
this.subjects.addAll(other.subjects);
```

Copying a `String` or `int` is always safe. Copying a **reference** (a `List`, another object) needs a new object, or the two copies share it.

Note: `addAll` makes a new list, but the items inside are still the same objects. That is fine for `String` because it never changes. If the items are mutable objects, copy each item too.

## Good to know

- Java has `Cloneable` and `clone()`, but it is hard to use correctly. A copy constructor is simpler and safer.
- A `String` is immutable, so sharing it between copies is fine.

## Interview questions

**1. What is the Prototype pattern?**
Creating a new object by copying an existing object (the prototype).

**2. When do you use it?**
When creating an object from scratch is expensive, or when you need many similar objects with small differences.

**3. What is the difference between a shallow copy and a deep copy?**
A shallow copy shares the inner objects (like a `List`) with the original. A deep copy makes new inner objects, so the two are fully independent.

**4. Why is `Cloneable` / `clone()` not recommended?**
It is easy to get wrong: it makes shallow copies by default, it skips constructors, and the `Cloneable` interface has no `clone()` method. A copy constructor or a `copy()` method is clearer.

**5. How is it different from Builder?**
Builder creates a new object step by step. Prototype starts from an existing object and copies it.

**6. Where is it used in real life?**
Copying `ArrayList` with `new ArrayList<>(other)`, document or email templates, game objects (many similar enemies), and Spring's `prototype` bean scope.
