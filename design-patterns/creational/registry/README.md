# Prototype Registry

> Keep ready-made prototypes in one place, find them **by name**, and hand out **copies**.

**Category:** Creational (a helper for the [Prototype](../prototype/) pattern. The GoF book calls it a "prototype manager".)

This folder is separate from the Prototype folder and runs on its own. It has four examples (Character, Student, Employee, College) that all share one generic registry.

## When to use

When you have several prototypes and want to create objects by a name (`"zombie"`, `"plant"`) without writing `if / else` or `new` everywhere.

```java
// Without a registry: the caller must know every class
if (type.equals("zombie")) { c = new ZombieCharacter("Walker", 50, 2); }
else if (type.equals("plant")) { c = new PlantCharacter("Sunflower", 30, 25); }

// With a registry: one line
Character c = registry.create(type);
```

## Steps

1. Make your class implement [Prototype](src/patterns/registry/Prototype.java): `Student implements Prototype<Student>`, so it has `copy()`.
2. Use [PrototypeRegistry](src/patterns/registry/PrototypeRegistry.java), which holds a `Map<String, T>`. `T` is your class.
3. `register(key, prototype)`: reject nulls, then store a **copy** of the prototype.
4. `create(key)`: find the prototype, then return a **copy** of it.
5. Unknown key: throw `IllegalArgumentException` with a clear message.
6. Use it: build each prototype once, register it, then call `create("zombie")` whenever you need one.

## Code

Shared by all examples:

| File | What it shows |
|------|---------------|
| [Prototype.java](src/patterns/registry/Prototype.java) | Generic interface with `T copy()` |
| [PrototypeRegistry.java](src/patterns/registry/PrototypeRegistry.java) | The registry: `register` and `create` |

Examples (each has its own README with steps):

| Example | What it shows |
|---------|---------------|
| [Character](src/patterns/registry/character/README.md) | Human, Zombie and Plant, created by name |
| [Student](src/patterns/registry/student/README.md) | One student template per branch |
| [Employee](src/patterns/registry/employee/README.md) | One employee template per role |
| [College](src/patterns/registry/college/README.md) | One college template per type, for Nanded and Latur |

## Run

From this folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.registry.character.CharacterDemo
java -cp out patterns.registry.student.StudentDemo
java -cp out patterns.registry.employee.EmployeeDemo
java -cp out patterns.registry.college.CollegeDemo
```

## Good to know

- **Why store a copy in `register()`?** If you stored the object itself, changing it later would change the prototype.
- **Why return a copy in `create()`?** If you returned the prototype, two callers would share one object.
- **Thread-safe.** It uses a `ConcurrentHashMap`, so several threads can register and create at the same time.
- **Same key twice:** registering the same key again replaces the old prototype.

## Interview questions

**1. What is a prototype registry?**
A place that stores ready-made prototypes by name and creates new objects by copying them.

**2. Why use one?**
The caller asks for `"zombie"` and gets a new zombie. It does not need `new` or `if / else`, and adding a new type only means registering one more prototype.

**3. Why does `create()` return a copy?**
So the stored prototype is never changed by callers.

**4. Why `T extends Prototype<T>`?**
It makes sure every object in the registry can copy itself, and `create()` returns the right type without casting. One registry class works for Character, Student, Employee and College.

**5. Registry or Factory?**
A factory decides which class to build. A registry holds already-built objects and copies them. They can work together.
