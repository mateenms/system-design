package solid.srp.bad;

/**
 * [BAD] BAD EXAMPLE - Single Responsibility Principle Violation
 *
 * Problem:
 *   This class handles THREE completely unrelated responsibilities:
 *     1. Business logic  (calculateTotal)
 *     2. Database access (saveOrder)
 *     3. Email sending   (sendConfirmationEmail)
 *
 * Why this is bad:
 *   - If the Finance team changes pricing rules  -> you edit this class.
 *   - If the DBA changes the DB schema          -> you edit this class.
 *   - If the Comms team updates the email text  -> you edit this class.
 *   Three different stakeholders, one class = three reasons to change.
 *
 * Real pain point:
 *   A bug in the email code can accidentally break the pricing logic
 *   during a refactor, because they live in the same file.
 */
public class OrderServiceBad {

    // ------------------------------------------------
    // Responsibility 1: Business Logic (owned by Finance team)
    // ------------------------------------------------

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

    // ------------------------------------------------
    // Responsibility 2: Database Access (owned by DBA / backend team)
    // ------------------------------------------------

    /**
     * Saves the order to the database.
     * Any schema change forces us to come back to this class.
     */
    public void saveOrder(Order order) {
        // Simulating a JDBC save - in real code this would use EntityManager or JdbcTemplate
        System.out.println("Saving order #" + order.getId() + " to database...");
        // e.g.: jdbcTemplate.update("INSERT INTO orders ...", order.getId(), ...);
    }

    // ------------------------------------------------
    // Responsibility 3: Email Notification (owned by Comms/Marketing team)
    // ------------------------------------------------

    /**
     * Sends a confirmation email to the customer.
     * Any email template change touches this business-logic class.
     */
    public void sendConfirmationEmail(Order order) {
        String subject = "Order Confirmed #" + order.getId();
        String body    = "Hi " + order.getCustomerName()
                       + ", your order total is $" + String.format(java.util.Locale.ROOT, "%.2f", calculateTotal(order));

        // Simulating SMTP send
        System.out.println("Sending email to: " + order.getCustomerEmail());
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }

    // ------------------------------------------------
    // Supporting model (kept here just to keep the file self-contained)
    // ------------------------------------------------

    static class Order {
        private final int id;
        private final String customerName;
        private final String customerEmail;
        private final java.util.List<OrderItem> items;

        Order(int id, String customerName, String customerEmail, java.util.List<OrderItem> items) {
            this.id = id;
            this.customerName = customerName;
            this.customerEmail = customerEmail;
            this.items = items;
        }

        public int getId()                              { return id; }
        public String getCustomerName()                 { return customerName; }
        public String getCustomerEmail()                { return customerEmail; }
        public java.util.List<OrderItem> getItems()     { return items; }
    }

    static class OrderItem {
        private final double price;
        private final int quantity;

        OrderItem(double price, int quantity) {
            this.price = price;
            this.quantity = quantity;
        }

        public double getPrice()    { return price; }
        public int getQuantity()    { return quantity; }
    }

    // ------------------------------------------------
    // Demo - one class does pricing, saving AND emailing
    // ------------------------------------------------
    public static void main(String[] args) {
        OrderServiceBad service = new OrderServiceBad();
        Order order = new Order(101, "Mohammad Mateen", "mateen@example.com",
                java.util.List.of(new OrderItem(49.99, 2), new OrderItem(29.99, 1)));

        service.saveOrder(order);
        service.sendConfirmationEmail(order);
    }
}
