package solid.ocp.good;

/**
 * Concrete discount: subtracts a fixed dollar amount from the price.
 *
 * Example: $20 off $100 -> $80
 * Price never goes below $0.
 *
 * @author Mateen
 */
public class FixedDiscount implements DiscountStrategy {

    private final double discountAmount; // e.g. 20.0 means $20 off

    public FixedDiscount(double discountAmount) {
        if (discountAmount < 0) {
            throw new IllegalArgumentException("discountAmount must be >= 0: " + discountAmount);
        }
        this.discountAmount = discountAmount;
    }

    @Override
    public double apply(double originalPrice) {
        // Ensure price doesn't go negative
        return Math.max(0, originalPrice - discountAmount);
    }

    @Override
    public String getName() {
        return "$" + discountAmount + " Fixed Discount";
    }
}
