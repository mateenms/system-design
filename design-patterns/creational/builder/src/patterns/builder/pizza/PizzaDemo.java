package patterns.builder.pizza;

/**
 * Runs the Pizza builder.
 *
 * @author Mateen
 */
public class PizzaDemo {

    public static void main(String[] args) {
        Pizza plain = new Pizza.Builder(Pizza.Size.SMALL).build();

        Pizza loaded = new Pizza.Builder(Pizza.Size.LARGE)
                .extraCheese()
                .addTopping("Onion")
                .addTopping("Mushroom")
                .addTopping("Olives")
                .build();

        System.out.println(plain);
        System.out.println(loaded);
    }
}
