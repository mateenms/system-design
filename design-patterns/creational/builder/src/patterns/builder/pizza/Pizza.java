package patterns.builder.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder example 2: Pizza.
 *
 * Size is required. Extra cheese and toppings are optional.
 * addTopping() can be called many times, once for each topping.
 *
 * @author Mateen
 */
public final class Pizza {

    public enum Size { SMALL, MEDIUM, LARGE }

    private final Size size;              // required
    private final boolean extraCheese;    // optional
    private final List<String> toppings;  // optional

    private Pizza(Builder builder) {
        this.size = builder.size;
        this.extraCheese = builder.extraCheese;
        // copy the list, so the Pizza and the Builder never share it
        this.toppings = List.copyOf(builder.toppings);
    }

    @Override
    public String toString() {
        return "Pizza{size=" + size + ", extraCheese=" + extraCheese + ", toppings=" + toppings + "}";
    }

    public static class Builder {

        private final Size size;
        private boolean extraCheese;
        private final List<String> toppings = new ArrayList<>();

        public Builder(Size size) {
            this.size = size;
        }

        public Builder extraCheese() {
            this.extraCheese = true;
            return this;
        }

        // each call adds one topping
        public Builder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public Pizza build() {
            if (size == null) {
                throw new IllegalArgumentException("size is required");
            }
            return new Pizza(this);
        }
    }
}
