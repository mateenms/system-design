package solid.ocp.good;

/**
 * [OK] The stable abstraction (interface) that every discount type implements.
 *
 * This is the "closed" part of OCP:
 *   - DiscountService depends on THIS interface - it never changes.
 *
 * This is the "open" part of OCP:
 *   - We add new discount types by creating a new class that implements this.
 *   - No existing code is touched.
 *
 * @author Mateen
 */
public interface DiscountStrategy {

    /**
     * Applies the discount to the given price.
     *
     * @param originalPrice the price before discount
     * @return the price after discount is applied
     */
    double apply(double originalPrice);

    /**
     * Human-readable name of this discount (useful for logging/receipts).
     *
     * @return name of the discount
     */
    String getName();
}
