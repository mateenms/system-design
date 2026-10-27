package solid.srp.bad;

/**
 * ❌ BAD EXAMPLE — Single Responsibility Principle Violation
 *
 * Problem:
 *   This class handles THREE completely unrelated responsibilities:
 *     1. Business logic  (calculateTotal)
 *     2. Database access (saveOrder)
 *     3. Email sending   (sendConfirmationEmail)
 *
 * Why this is bad:
 *   - If the Finance team changes pricing rules  → you edit this class.
 *   - If the DBA changes the DB schema          → you edit this class.
 *   - If the Comms team updates the email text  → you edit this class.
 *   Three different stakeholders, one class = three reasons to change.
 *
 * Real pain point:
 *   A bug in the email code can accidentally break the pricing logic
 *   during a refactor, because they live in the same file.
 */
public class OrderServiceBad {

    // ────────────────────────────────────────────────
    // Responsibility 1: Business Logic (owned by Finance team)
    // ────────────────────────────────────────────────

    /**
     * Calculates the total price of the order.
     * Finance team decides the discount/tax rules here.
     */
    public double calculateTotal(Order order) {
        double subtotal = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Apply 10% tax
        return subtotal * 1.10;
    }

    // ────────────────────────────────────────────────
    // Responsibility 2: Database Access (owned by DBA / backend team)
    // ────────────────────────────────────────────────

    /**
     * Saves the order to the database.
     * Any schema change forces us to come back to this class.
     */
    public void saveOrder(Order order) {
        // Simulating a JDBC save — in real code this would use EntityManager or JdbcTemplate
        System.out.println("Saving order #" + order.getId() + " to database...");
        // e.g.: jdbcTemplate.update("INSERT INTO orders ...", order.getId(), ...);
    }

    // ────────────────────────────────────────────────
    // Responsibility 3: Email Notification (owned by Comms/Marketing team)
    // ────────────────────────────────────────────────

    /**
     * Sends a confirmation email to the customer.
     * Any email template change touches this business-logic class.
     */
    public void sendConfirmationEmail(Order order) {
        String subject = "Order Confirmed #" + order.getId();
        String body    = "Hi " + order.getCustomerName()
                       + ", your order total is $" + calculateTotal(order);

        // Simulating SMTP send
        System.out.println("Sending email to: " + order.getCustomerEmail());
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }

    // ────────────────────────────────────────────────
    // Supporting model (kept here just to keep the file self-contained)
    // ────────────────────────────────────────────────

    static class Order {
        private int id;
        private String customerName;
        private String customerEmail;
        private java.util.List<OrderItem> items;

        public int getId()                              { return id; }
        public String getCustomerName()                 { return customerName; }
        public String getCustomerEmail()                { return customerEmail; }
        public java.util.List<OrderItem> getItems()     { return items; }
    }

    static class OrderItem {
        private double price;
        private int quantity;

        public double getPrice()    { return price; }
        public int getQuantity()    { return quantity; }
    }
}
