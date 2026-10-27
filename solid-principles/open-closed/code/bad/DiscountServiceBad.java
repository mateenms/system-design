package solid.ocp.bad;

/**
 * [BAD] BAD EXAMPLE - Open/Closed Principle Violation
 *
 * Problem:
 *   Every time we add a new discount type (e.g., SEASONAL, LOYALTY, FLASH_SALE),
 *   we MUST edit this method. That means:
 *     - Touching working, tested code -> risk of regression
 *     - The class is NOT closed for modification
 *     - The switch/if-else block grows indefinitely
 *
 * Real pain point:
 *   Adding a 5th discount type breaks the existing 4 if you make a mistake.
 *   All discount types are coupled in one fragile block.
 */
public class DiscountServiceBad {

    /**
     * Applies a discount based on a string type.
     *
     * [BAD] Every new discount type requires editing THIS method.
     *    This violates OCP because the class is NOT closed for modification.
     *
     * @param price        original price
     * @param discountType type of discount as a string
     * @return final price after discount
     */
    public double applyDiscount(double price, String discountType) {

        if (discountType.equals("PERCENTAGE_10")) {
            // 10% off
            return price * 0.90;

        } else if (discountType.equals("FIXED_20")) {
            // $20 flat discount
            return Math.max(0, price - 20);

        } else if (discountType.equals("BUY_ONE_GET_ONE")) {
            // Half price (simulating BOGO)
            return price / 2;

        }
        // [BAD] To add SEASONAL discount, a developer must come here and add another else-if.
        // That risks breaking the existing three types during editing.

        // No matching discount - return original price
        return price;
    }

    // ------------------------------------------------
    // Demo
    // ------------------------------------------------
    public static void main(String[] args) {
        DiscountServiceBad service = new DiscountServiceBad();

        System.out.println("PERCENTAGE_10 on $100 -> $" + service.applyDiscount(100, "PERCENTAGE_10"));
        System.out.println("FIXED_20      on $100 -> $" + service.applyDiscount(100, "FIXED_20"));
        System.out.println("BOGO          on $100 -> $" + service.applyDiscount(100, "BUY_ONE_GET_ONE"));
    }
}
