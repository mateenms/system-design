# Prototype Example 3: Character (Human, Zombie, Plant)

Copy characters of different types. Each type knows how to copy itself.

## Steps

1. Create the generic interface [Prototype](Prototype.java): `interface Prototype<T> { T copy(); }`. `T` can be any class.
2. Make the base class implement it: `abstract class Character implements Prototype<Character>`, and override `copy()` as `abstract`, so every type must say how to copy itself.
3. In `Character`, add a **protected copy constructor** that copies `name`, `health` and the `abilities` list.
4. In each subclass (`HumanCharacter`, `ZombieCharacter`, `PlantCharacter`), add a **private copy constructor**.
5. In it, call `super(other)` first. This copies the shared fields.
6. Then copy the subclass's own field (`weapon`, `speed`, `sunPower`).
7. Override `copy()` to return `new HumanCharacter(this)` (and the same for the other two).
8. Use it: `Character zombie2 = zombie.copy();`, then change `zombie2` only.

## Code

| File | What it shows |
|------|---------------|
| [Prototype.java](Prototype.java) | Generic interface `Prototype<T>` with `T copy()` |
| [Character.java](Character.java) | Base class, implements `Prototype<Character>`, with the copy constructor and abstract `copy()` |
| [HumanCharacter.java](HumanCharacter.java) | Adds `weapon` |
| [ZombieCharacter.java](ZombieCharacter.java) | Adds `speed` |
| [PlantCharacter.java](PlantCharacter.java) | Adds `sunPower` |
| [CharacterDemo.java](CharacterDemo.java) | Makes copies and changes only the copy |

Inside this package, `Character` means this class, not `java.lang.Character`.

## Run

From the `prototype` folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out patterns.prototype.character.CharacterDemo
```
