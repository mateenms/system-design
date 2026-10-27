package solid.lsp.bad;

/**
 * ❌ BAD EXAMPLE — Liskov Substitution Principle Violation
 *
 * Problem:
 *   Bird declares fly(). Ostrich extends Bird but CANNOT fly.
 *   Ostrich is forced to override fly() and throw a runtime exception.
 *
 * LSP is violated because:
 *   Any code written to work with a Bird breaks when handed an Ostrich.
 *   The subclass (Ostrich) cannot be substituted for the base class (Bird)
 *   without breaking the program.
 *
 * Real pain point:
 *   The bug is invisible at compile time. It only crashes at runtime when
 *   makeBirdFly(new Ostrich()) is called.
 */

// Base class — assumes ALL birds can fly
class Bird {

    protected String name;

    public Bird(String name) {
        this.name = name;
    }

    /**
     * Makes the bird fly.
     * Problematic assumption: not every bird can fly.
     */
    public void fly() {
        System.out.println(name + " is flying high!");
    }

    public String getName() { return name; }
}

// ✅ Works fine — Eagles can fly
class Eagle extends Bird {

    public Eagle() {
        super("Eagle");
    }

    @Override
    public void fly() {
        System.out.println("Eagle soaring at high altitude!");
    }
}

// ❌ LSP Violation — Ostriches cannot fly!
class Ostrich extends Bird {

    public Ostrich() {
        super("Ostrich");
    }

    @Override
    public void fly() {
        // ❌ Forced to override, but cannot honour the contract
        // Caller expects fly() to work — gets a crash instead
        throw new UnsupportedOperationException("Ostrich cannot fly!");
    }
}

public class BirdBad {

    /**
     * This method is written to work with ANY Bird.
     * LSP says: it should work with any subtype of Bird.
     * But it CRASHES when given an Ostrich.
     */
    public static void makeBirdFly(Bird bird) {
        System.out.println("Making " + bird.getName() + " fly...");
        bird.fly(); // ❌ Crashes with UnsupportedOperationException for Ostrich
    }

    public static void main(String[] args) {
        makeBirdFly(new Eagle());  // ✅ Works fine

        // ❌ Crashes at runtime — LSP violation
        makeBirdFly(new Ostrich());
    }
}
