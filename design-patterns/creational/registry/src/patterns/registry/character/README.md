# Registry Example 1: Character (Human, Zombie, Plant)

Create characters by name: `registry.create("zombie")`.

## Steps

1. `Character` implements `Prototype<Character>`, so every character has `copy()`.
2. Build one human, one zombie and one plant (the prototypes).
3. Create `new PrototypeRegistry<Character>()`.
4. Register each one: `registry.register("zombie", zombie)`.
5. Create by name: `registry.create("zombie")`. You get a copy.
6. Change only the copy (`setName`, `addAbility`). The registered zombie stays the same.
7. An unknown name (`"dragon"`) throws `IllegalArgumentException`.

## Code

| File | What it shows |
|------|---------------|
| [Character.java](Character.java) | Base class that can copy itself |
| [HumanCharacter.java](HumanCharacter.java), [ZombieCharacter.java](ZombieCharacter.java), [PlantCharacter.java](PlantCharacter.java) | The three types |
| [CharacterDemo.java](CharacterDemo.java) | Registers three characters and creates them by name |

Run: see the [registry README](../../../../README.md#run).
