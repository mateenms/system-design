# Builder Design Pattern

> Build an object **step by step** with readable method calls, instead of one long constructor.

**Category:** Creational

## When to use

When a class has many fields and some are optional: a student with name, age, course, city. Without a Builder you end up with many constructors that are easy to mix up.

## How to build one (6 steps)

1. Make the class `final`, with `final` fields and no setters.
2. Make its constructor `private`, taking the Builder.
3. Add a **static nested `Builder`** class with the same fields.
4. Put the required fields in the Builder constructor.
5. Add one method per optional field that **returns `this`**.
6. Add `build()`: check the values, then create the object.

## Code

| File | What it shows |
|------|---------------|
| [src/patterns/builder/student/bad/StudentBad.java](src/patterns/builder/student/bad/StudentBad.java) | No Builder: many constructors, easy to swap two `String` values |
| [src/patterns/builder/student/good/Student.java](src/patterns/builder/student/good/Student.java) | `Student` with a nested `Builder` |
| [src/patterns/builder/student/good/StudentDemo.java](src/patterns/builder/student/good/StudentDemo.java) | Creates students with the Builder |
| [src/patterns/builder/pizza/Pizza.java](src/patterns/builder/pizza/Pizza.java) | Example 2: required size, optional cheese, a list of toppings |
| [src/patterns/builder/pizza/PizzaDemo.java](src/patterns/builder/pizza/PizzaDemo.java) | Runs the Pizza builder |
| [src/patterns/builder/email/Email.java](src/patterns/builder/email/Email.java) | Example 3: required `to` and `subject`, optional `cc` and `body` |
| [src/patterns/builder/email/EmailDemo.java](src/patterns/builder/email/EmailDemo.java) | Runs the Email builder |
| [src/patterns/builder/employee/Employee.java](src/patterns/builder/employee/Employee.java) | Example 4: required `employeeId` and `name`, optional `email` and `age` |
| [src/patterns/builder/employee/EmployeeDemo.java](src/patterns/builder/employee/EmployeeDemo.java) | Runs the Employee builder |

Run from this folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.builder.student.bad.StudentBad
java -cp out patterns.builder.student.good.StudentDemo
java -cp out patterns.builder.pizza.PizzaDemo
java -cp out patterns.builder.email.EmailDemo
java -cp out patterns.builder.employee.EmployeeDemo
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
- A mutable field (like a `List`) must be copied in the constructor so the object and the Builder never share it. [Pizza.java](src/patterns/builder/pizza/Pizza.java) does this with `List.copyOf(...)`.
- `build()` throws `IllegalArgumentException` when a value is invalid.

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
