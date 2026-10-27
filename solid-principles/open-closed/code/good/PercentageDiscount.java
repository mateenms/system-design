package solid.ocp.good;

/**
 * Concrete discount: reduces the price by a given percentage.
 *
 * Example: 10% discount on $100 → $90
 */
public class PercentageDiscount implements DiscountStrategy {

    private final double percent; // e.g. 10.0 means 10%

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double originalPrice) {
        // Subtract the percentage from the original price
        return originalPrice * (1 - percent / 100);
    }

    @Override
    public String getName() {
        return percent + "% Discount";
    }
}
