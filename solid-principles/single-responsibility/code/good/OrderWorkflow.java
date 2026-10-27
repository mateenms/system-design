package solid.srp.good;

import java.util.Arrays;
import java.util.Objects;

/**
 * [OK] GOOD EXAMPLE - Orchestrator (coordinates the focused classes)
 *
 * This class ties the three responsibilities together.
 * Its only job is coordination: call the right service in the right order.
 *
 * Notice:
 *   - Each collaborator (pricing, repo, email) is injected via constructor.
 *   - If any one service changes, only THAT service's class needs to be edited.
 *
 * Note: collaborators are concrete classes to keep this SRP example small.
 * See the dependency-inversion folder for how to hide them behind interfaces
 * so they can be swapped or mocked.
 *
 * @author Mateen
 */
public class OrderWorkflow {

    // Each dependency represents one responsibility - injected, not created here
    private final OrderPricingService pricingService;
    private final OrderRepository     repository;
    private final OrderEmailService   emailService;

    /**
     * Constructor injection - best practice in Spring Boot (@Autowired on constructor).
     */
    public OrderWorkflow(OrderPricingService pricingService,
                         OrderRepository repository,
                         OrderEmailService emailService) {
        this.pricingService = Objects.requireNonNull(pricingService);
        this.repository     = Objects.requireNonNull(repository);
        this.emailService   = Objects.requireNonNull(emailService);
    }

    /**
     * Executes the full order placement workflow:
     *   1. Calculate total
     *   2. Save to DB
     *   3. Send confirmation email
     *
     * Production note: if the email fails after the save, the order stays saved.
     * Real systems handle this with a transaction/outbox or a retry queue.
     *
     * @param order the order to process
     */
    public void placeOrder(Order order) {
        Objects.requireNonNull(order, "order");
        // Step 1: calculate - pricing service's job
        double total = pricingService.calculateTotal(order);
        System.out.println("Order total: $" + String.format(java.util.Locale.ROOT, "%.2f", total));

        // Step 2: persist - repository's job
        repository.save(order);

        // Step 3: notify customer - email service's job
        emailService.sendConfirmation(order, total);

        System.out.println("[OK] Order #" + order.getId() + " placed successfully.");
    }

    // ------------------------------------------------
    // Quick demo - run this to see the output
    // ------------------------------------------------
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
