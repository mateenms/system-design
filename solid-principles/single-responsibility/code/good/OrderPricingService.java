package solid.srp.good;

/**
 * [OK] GOOD EXAMPLE - Responsibility 1: Pricing / Business Logic
 *
 * Single Responsibility:
 *   This class ONLY calculates prices and applies tax rules.
 *   It changes ONLY when Finance team updates pricing or tax logic.
 *
 * Who owns this class? -> Finance / Business team.
 */
public class OrderPricingService {

    // Teaching note: double keeps the example simple. Use BigDecimal for real money.

    // Tax rate - in a real app this would come from configuration
    private static final double TAX_RATE = 0.10;

    /**
     * Calculates the order subtotal (sum of price x quantity for all items).
     *
     * @param order the order to calculate for
     * @return subtotal before tax
     */
    public double calculateSubtotal(Order order) {
        return order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    /**
     * Calculates the final total including tax.
     *
     * @param order the order to calculate for
     * @return total price after tax
     */
    public double calculateTotal(Order order) {
        double subtotal = calculateSubtotal(order);
        double tax      = subtotal * TAX_RATE;
        return subtotal + tax;
    }
}
