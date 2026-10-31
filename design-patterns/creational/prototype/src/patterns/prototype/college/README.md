# Prototype Example 4: Colleges in Maharashtra

Most colleges in Maharashtra are set up the same way: same state, same courses, same facilities. Only the **name, city and university** change.

So we build **one ready-made college** (the prototype) and **copy** it for each city, instead of building every college from scratch.

> The courses, facilities and universities in the demo are sample data, not official information.

## Steps

1. Create the `College` class with simple fields (`name`, `city`, `university`, `state`) and lists (`courses`, `facilities`).
2. Add a **private copy constructor**: `private College(College other)`.
3. In it, copy the simple fields directly.
4. In it, copy each list into a **new list** with `addAll`, so the copy never shares a list with the original.
5. Add `copy()`: `return new College(this);`
6. Build the template once with the common courses and facilities.
7. For each college: `template.copy()`, then change only the name, city and university.
8. Add anything extra to the copy only (Bidve COE gets `Electrical Engineering`). The template stays the same.

## Code

| File | What it shows |
|------|---------------|
| [College.java](College.java) | The college with a copy constructor and `copy()` |
| [CollegeDemo.java](CollegeDemo.java) | One template, copied for MGM COE and Matoshiri COE (Nanded) and Bidve COE (Latur) |

## Run

From the `prototype` folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.prototype.college.CollegeDemo
```
