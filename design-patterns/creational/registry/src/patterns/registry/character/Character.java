package patterns.registry.character;

import patterns.registry.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Game character, used by the registry example.
 *
 * Base class for Human, Zombie and Plant. It implements Prototype<Character>,
 * so every character can copy itself with copy(), and each subclass returns
 * a copy of its own type.
 *
 * Note: inside this package, "Character" means this class, not java.lang.Character.
 *
 * @author Mateen
 */
public abstract class Character implements Prototype<Character> {

    private String name;
    private int health;
    private final List<String> abilities = new ArrayList<>();

    protected Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // copy constructor: subclasses call it with super(other)
    protected Character(Character other) {
        this.name = other.name;
        this.health = other.health;
        this.abilities.addAll(other.abilities);  // its own list
    }

    // from Prototype: every subclass says how to copy itself
    @Override
    public abstract Character copy();

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addAbility(String ability) {
        abilities.add(ability);
    }

    @Override
    public String toString() {
        return "name=" + name + ", health=" + health + ", abilities=" + abilities;
    }
}
