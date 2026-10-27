package solid.lsp.good;

import java.util.List;

/**
 * Demo: every method works correctly with every subtype it accepts.
 */
public class BirdDemo {

    /** Works for ANY Bird (Eagle, Ostrich, ...). */
    static void feed(Bird bird) {
        System.out.println("Feeding " + bird.getName());
        bird.eat();
    }

    /** Accepts only FlyingBird, so fly() is always safe to call. */
    static void makeFly(FlyingBird bird) {
        System.out.println("Making " + bird.getName() + " fly...");
        bird.fly();
    }

    public static void main(String[] args) {
        List<Bird> allBirds = List.of(new Eagle(), new Ostrich());
        allBirds.forEach(BirdDemo::feed);   // both substitute safely for Bird

        makeFly(new Eagle());               // OK
        // makeFly(new Ostrich());          // compile error: Ostrich is not a FlyingBird
    }
}
