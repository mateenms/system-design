package patterns.registry.character;

/**
 * A human survivor. Extra field: weapon.
 *
 * @author Mateen
 */
public final class HumanCharacter extends Character {

    private final String weapon;

    public HumanCharacter(String name, int health, String weapon) {
        super(name, health);
        this.weapon = weapon;
    }

    private HumanCharacter(HumanCharacter other) {
        super(other);                // copy name, health, abilities
        this.weapon = other.weapon;  // copy our own field
    }

    @Override
    public HumanCharacter copy() {
        return new HumanCharacter(this);
    }

    @Override
    public String toString() {
        return "HumanCharacter{" + super.toString() + ", weapon=" + weapon + "}";
    }
}
