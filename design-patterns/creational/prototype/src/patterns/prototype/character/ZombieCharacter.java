package patterns.prototype.character;

/**
 * A zombie. Extra field: speed.
 *
 * @author Mateen
 */
public final class ZombieCharacter extends Character {

    private final int speed;

    public ZombieCharacter(String name, int health, int speed) {
        super(name, health);
        this.speed = speed;
    }

    private ZombieCharacter(ZombieCharacter other) {
        super(other);                // copy name, health, abilities
        this.speed = other.speed;    // copy our own field
    }

    @Override
    public ZombieCharacter copy() {
        return new ZombieCharacter(this);
    }

    @Override
    public String toString() {
        return "ZombieCharacter{" + super.toString() + ", speed=" + speed + "}";
    }
}
