package patterns.registry.character;

import patterns.registry.PrototypeRegistry;

/**
 * Registers one human, zombie and plant, then creates characters by name.
 *
 * @author Mateen
 */
public class CharacterDemo {

    public static void main(String[] args) {
        // build each prototype once
        HumanCharacter human = new HumanCharacter("Survivor", 100, "Axe");
        human.addAbility("Run");

        ZombieCharacter zombie = new ZombieCharacter("Walker", 50, 2);
        zombie.addAbility("Bite");

        PlantCharacter plant = new PlantCharacter("Sunflower", 30, 25);
        plant.addAbility("Make sun");

        // register them by name
        PrototypeRegistry<Character> registry = new PrototypeRegistry<>();
        registry.register("human", human);
        registry.register("zombie", zombie);
        registry.register("plant", plant);

        // create by name, no "new" and no need to know how a zombie is built
        Character zombie1 = registry.create("zombie");

        Character zombie2 = registry.create("zombie");
        zombie2.setName("Runner");
        zombie2.addAbility("Roar");

        System.out.println(registry.create("human"));
        System.out.println(registry.create("plant"));
        System.out.println(zombie1);
        System.out.println(zombie2);

        // neither zombie2 (changed above) nor the original zombie (changed now) affects the registry
        zombie.setHealth(1);
        System.out.println("registered: " + registry.create("zombie"));

        // unknown key
        try {
            registry.create("dragon");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
