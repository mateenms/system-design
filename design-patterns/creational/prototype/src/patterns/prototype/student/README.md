# Prototype Example 1: Student

Copy a student that has a list of subjects. Shows why the copy needs its **own list**.

## Steps

1. Create the class with a simple field (`name`) and a mutable field (`subjects`, a `List`).
2. Add a **private copy constructor**: `private Student(Student other)`.
3. In it, copy the simple field directly: `this.name = other.name;`
4. In it, copy the list into a **new list**: `this.subjects.addAll(other.subjects);`
5. Add `copy()`: `return new Student(this);`
6. Use it: `Student copy = original.copy();`, then change the copy as you like.

## Bad vs good

| File | What it shows |
|------|---------------|
| [bad/StudentBad.java](bad/StudentBad.java) | Skips step 4 and shares the list, so changing the copy changes the original |
| [good/Student.java](good/Student.java) | Follows all 6 steps, the copy gets its own list |
| [good/StudentDemo.java](good/StudentDemo.java) | The copy is independent from the original |

## Run

From the `prototype` folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.prototype.student.bad.StudentBad
java -cp out patterns.prototype.student.good.StudentDemo
```
