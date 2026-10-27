package solid.ocp.good;

/**
 * ✅ NEW discount type added WITHOUT touching any existing code.
 *
 * This is OCP in action:
 *   - We extended the system with a new discount.
 *   - DiscountService, PercentageDiscount, FixedDiscount — all untouched.
 *
 * Example: Seasonal sale gives 25% off → $100 becomes $75
 */
public class SeasonalDiscount implements DiscountStrategy {

    @Override
    public double apply(double originalPrice) {
        // 25% seasonal sale
        return originalPrice * 0.75;
    }

    @Override
    public String getName() {
        return "Seasonal Sale (25% off)";
    }
}
