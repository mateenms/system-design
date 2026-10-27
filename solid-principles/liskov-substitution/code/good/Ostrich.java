package solid.lsp.good;

/**
 * Ostrich CANNOT fly, so it implements only Bird.
 *
 * No fly() method, no UnsupportedOperationException.
 * It is impossible to pass an Ostrich where a FlyingBird is required,
 * so the compiler prevents the bug the "bad" version had at runtime.
 */
public class Ostrich implements Bird {

    @Override
    public String getName() { return "Ostrich"; }

    @Override
    public void eat() { System.out.println("Ostrich is eating plants and seeds."); }

    @Override
    public void makeSound() { System.out.println("Ostrich: Boom!"); }

    // Ostrich-specific capability, not part of any shared contract
    public void run() { System.out.println("Ostrich is running at 70 km/h!"); }
}
