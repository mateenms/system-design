package patterns.prototype.character;

/**
 * Builds one character of each type, then makes more by copying.
 *
 * @author Mateen
 */
public class CharacterDemo {

    public static void main(String[] args) {
        // the prototypes
        HumanCharacter human = new HumanCharacter("Survivor", 100, "Axe");
        human.addAbility("Run");

        ZombieCharacter zombie = new ZombieCharacter("Walker", 50, 2);
        zombie.addAbility("Bite");

        PlantCharacter plant = new PlantCharacter("Sunflower", 30, 25);
        plant.addAbility("Make sun");

        // copy, then change only what is different
        Character zombie1 = zombie.copy();

        Character zombie2 = zombie.copy();
        zombie2.setName("Runner");
        zombie2.setHealth(80);
        zombie2.addAbility("Roar");

        System.out.println(human.copy());
        System.out.println(plant.copy());
        System.out.println(zombie1);
        System.out.println(zombie2);

        // the original zombie is not changed
        System.out.println("original: " + zombie);
    }
}
