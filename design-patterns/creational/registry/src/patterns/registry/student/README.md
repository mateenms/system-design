# Registry Example 2: Student

One ready-made student per **branch**. A new student is a copy with a name.

## Steps

1. `Student` implements `Prototype<Student>` with a private copy constructor and `copy()`.
2. Build one student per branch with its subjects (Computer Science, Mechanical).
3. Create `new PrototypeRegistry<Student>()`.
4. Register them: `registry.register("cse", cse)`.
5. Create by branch: `registry.create("cse").name("Asha")`.
6. Add anything extra to the copy only (Ravi gets `Machine Learning`).
7. The registered student stays the same.

## Code

| File | What it shows |
|------|---------------|
| [Student.java](Student.java) | Student with a copy constructor and `copy()` |
| [StudentDemo.java](StudentDemo.java) | Creates Asha, Ravi and Meera by branch |

Run: see the [registry README](../../../../README.md#run).
