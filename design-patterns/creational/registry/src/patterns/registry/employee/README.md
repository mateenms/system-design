# Registry Example 3: Employee

One ready-made employee per **role**. A new employee is a copy with an id and a name.

## Steps

1. `Employee` implements `Prototype<Employee>` with a private copy constructor and `copy()`.
2. Build one employee per role with its department and skills (Developer, Tester).
3. Create `new PrototypeRegistry<Employee>()`.
4. Register them: `registry.register("developer", developer)`.
5. Create by role: `registry.create("developer").id("EMP101").name("Asha")`.
6. Add anything extra to the copy only (Ravi gets `Docker`).
7. The registered employee stays the same.

## Code

| File | What it shows |
|------|---------------|
| [Employee.java](Employee.java) | Employee with a copy constructor and `copy()` |
| [EmployeeDemo.java](EmployeeDemo.java) | Creates Asha, Ravi and Meera by role |

Run: see the [registry README](../../../../README.md#run).
