# Prototype Example 2: EmailTemplate

Load a slow template **once**, then copy it for every person.

## Steps

1. Create the template once with the slow constructor (it "loads" the template).
2. Add a **private copy constructor** that copies `to`, `subject`, `body` and the `attachments` list. It does not load anything, so it is fast.
3. Add `copy()`: `return new EmailTemplate(this);`
4. For each person: `welcome.copy().to("asha@example.com")`.
5. Change only the copy (`to`, `attach`). The original template stays the same.

## Code

| File | What it shows |
|------|---------------|
| [EmailTemplate.java](EmailTemplate.java) | The template with a copy constructor and `copy()` |
| [TemplateDemo.java](TemplateDemo.java) | "Loading template..." is printed once, then two copies are made |

## Run

From the `prototype` folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.prototype.template.TemplateDemo
```
