package solid.ocp.good;

/**
 * ✅ GOOD EXAMPLE — Open/Closed Principle
 *
 * This class is:
 *   ✅ CLOSED for modification — the applyDiscount() method NEVER changes.
 *   ✅ OPEN for extension    — new discount types are new classes, not if-else branches.
 *
 * Key design decision:
 *   - DiscountService depends on the DiscountStrategy INTERFACE, not any concrete class.
 *   - The caller decides WHICH discount to apply by choosing the right implementation.
 *   - Zero if/else, zero switch statements.
 */
public class DiscountService {

    /**
     * Applies the given discount strategy to the price.
     *
     * This method will NEVER need to change, regardless of how many
     * new discount types we add in the future.
     *
     * @param originalPrice the price before discount
     * @param strategy      the discount strategy to apply
     * @return the discounted price
     */
    public double applyDiscount(double originalPrice, DiscountStrategy strategy) {
        double discountedPrice = strategy.apply(originalPrice);

        // Log what happened (useful for receipts)
        System.out.printf("Applied %-30s | $%.2f → $%.2f%n",
                strategy.getName(), originalPrice, discountedPrice);

        return discountedPrice;
    }

    // ────────────────────────────────────────────────
    // Demo — run this to see all discount types working
    // ────────────────────────────────────────────────
    public static void main(String[] args) {
        DiscountService service = new DiscountService();
        double originalPrice   = 100.0;

        System.out.println("=== Discount Examples ===");

        // Existing discount types — no changes needed
        service.applyDiscount(originalPrice, new PercentageDiscount(10));
        service.applyDiscount(originalPrice, new FixedDiscount(20));

        // New discount type — just pass a new strategy, nothing else changes
        service.applyDiscount(originalPrice, new SeasonalDiscount());

        /*
         * Want to add a FLASH_SALE discount tomorrow?
         * Step 1: Create FlashSaleDiscount implements DiscountStrategy
         * Step 2: Call service.applyDiscount(price, new FlashSaleDiscount())
         * Step 3: Done. DiscountService is untouched.
         */
    }
}
