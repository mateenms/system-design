# Builder Design Pattern

> Build an object **step by step** with readable method calls, instead of one long constructor.

**Category:** Creational

## When to use

When a class has many fields and some are optional: a student with name, age, course, city. Without a Builder you end up with many constructors that are easy to mix up.

## How to build one (4 rules)

1. Make the class **immutable**: `final` fields, no setters.
2. Make its constructor **private**, taking the Builder.
3. Add a **static nested `Builder`** class. Put the required fields in its constructor.
4. Give the Builder one method per optional field that **returns `this`**, plus a `build()` method that validates and creates the object.

## Code

| File | What it shows |
|------|---------------|
| [src/patterns/builder/bad/StudentBad.java](src/patterns/builder/bad/StudentBad.java) | No Builder: many constructors, easy to swap two `String` values |
| [src/patterns/builder/good/Student.java](src/patterns/builder/good/Student.java) | `Student` with a nested `Builder` |
| [src/patterns/builder/good/BuilderDemo.java](src/patterns/builder/good/BuilderDemo.java) | Creates students with the Builder |

Run from this folder:

```bash
javac -d out src/patterns/builder/*/*.java
java -cp out patterns.builder.bad.StudentBad
java -cp out patterns.builder.good.BuilderDemo
```

## Bad vs good

```java
// Bad: the order is (course, city), but here they are swapped and the compiler says nothing
new StudentBad(1, "Asha", 20, "Pune", "Java");

// Good: every value has a name
new Student.Builder(1, "Asha")
        .age(20)
        .course("Java")
        .city("Pune")
        .build();
```

## Good to know

- `age` is an `int`, so `0` means "not given". Use `Integer` or `Optional` if you must tell `0` apart from "missing".
- If you add a mutable field (like a `List`), copy it in the `Student` constructor so the Student and the Builder never share it.

## Interview questions

**1. What is the Builder pattern?**
A way to create a complex object step by step, using named method calls, and get the finished object from `build()`.

**2. What problem does it solve?**
The telescoping constructor problem: too many constructors, or one constructor with many parameters that are easy to mix up.

**3. How is it different from setters (JavaBeans style)?**
With setters the object exists half-filled and is mutable. With a Builder the object is complete and immutable as soon as `build()` returns.

**4. Where do you validate?**
In `build()`, so an invalid object can never be created. In this example: `rollNo` must be positive, `name` is required, `age` cannot be negative, and `course` / `city` cannot be blank when given.

**5. Where is it used in real life?**
`StringBuilder`, `Stream.Builder`, `HttpRequest.newBuilder()`, and Lombok's `@Builder` in Spring Boot projects.

**6. When should you NOT use it?**
When the class has only two or three fields. A plain constructor is simpler.
