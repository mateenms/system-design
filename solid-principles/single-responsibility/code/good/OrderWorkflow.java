package solid.srp.good;

import java.util.Arrays;

/**
 * ✅ GOOD EXAMPLE — Orchestrator (coordinates the focused classes)
 *
 * This class ties the three responsibilities together.
 * It has NO logic of its own — it just calls the right service in the right order.
 *
 * Notice:
 *   - Each collaborator (pricing, repo, email) is injected via constructor.
 *   - This makes it easy to unit-test by swapping in mock/fake collaborators.
 *   - If any one service changes, only THAT service's class needs to be edited.
 */
public class OrderWorkflow {

    // Each dependency represents one responsibility — injected, not created here
    private final OrderPricingService pricingService;
    private final OrderRepository     repository;
    private final OrderEmailService   emailService;

    /**
     * Constructor injection — best practice in Spring Boot (@Autowired on constructor).
     */
    public OrderWorkflow(OrderPricingService pricingService,
                         OrderRepository repository,
                         OrderEmailService emailService) {
        this.pricingService = pricingService;
        this.repository     = repository;
        this.emailService   = emailService;
    }

    /**
     * Executes the full order placement workflow:
     *   1. Calculate total
     *   2. Save to DB
     *   3. Send confirmation email
     *
     * @param order the order to process
     */
    public void placeOrder(Order order) {
        // Step 1: calculate — pricing service's job
        double total = pricingService.calculateTotal(order);
        System.out.println("Order total: $" + String.format("%.2f", total));

        // Step 2: persist — repository's job
        repository.save(order);

        // Step 3: notify customer — email service's job
        emailService.sendConfirmation(order, total);

        System.out.println("✅ Order #" + order.getId() + " placed successfully.");
    }

    // ────────────────────────────────────────────────
    // Quick demo — run this to see the output
    // ────────────────────────────────────────────────
    public static void main(String[] args) {
        // Build the order
        Order order = new Order(
                101,
                "Mohammad Mateen",
                "mateen@example.com",
                Arrays.asList(
                        new OrderItem("Java Book",  49.99, 2),
                        new OrderItem("Mouse",      29.99, 1)
                )
        );

        // Wire up the services (in Spring Boot, @Autowired handles this)
        OrderWorkflow workflow = new OrderWorkflow(
                new OrderPricingService(),
                new OrderRepository(),
                new OrderEmailService()
        );

        // Place the order
        workflow.placeOrder(order);
    }
}
