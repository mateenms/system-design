package patterns.prototype.character;

/**
 * A plant. Extra field: sun it makes.
 *
 * @author Mateen
 */
public final class PlantCharacter extends Character {

    private final int sunPower;

    public PlantCharacter(String name, int health, int sunPower) {
        super(name, health);
        this.sunPower = sunPower;
    }

    private PlantCharacter(PlantCharacter other) {
        super(other);                    // copy name, health, abilities
        this.sunPower = other.sunPower;  // copy our own field
    }

    @Override
    public PlantCharacter copy() {
        return new PlantCharacter(this);
    }

    @Override
    public String toString() {
        return "PlantCharacter{" + super.toString() + ", sunPower=" + sunPower + "}";
    }
}
