package solid.srp.good;

/**
 * [OK] GOOD EXAMPLE - Responsibility 3: Email Notification
 *
 * Single Responsibility:
 *   This class ONLY sends order-related emails.
 *   It changes ONLY when the email template or SMTP config changes.
 *
 * Who owns this class? -> Comms / Marketing team.
 */
public class OrderEmailService {

    /**
     * Sends an order confirmation email to the customer.
     *
     * In a real Spring Boot app this would use:
     *   - JavaMailSender (Spring Mail)
     *   - SendGrid / Mailgun SDK
     *
     * @param order       the order that was placed
     * @param orderTotal  the final calculated total (injected, not recalculated here)
     */
    public void sendConfirmation(Order order, double orderTotal) {
        String subject = "Order Confirmed - #" + order.getId();
        String body    = "Hi " + order.getCustomerName() + ",\n"
                       + "Your order has been placed successfully.\n"
                       + "Total: $" + String.format(java.util.Locale.ROOT, "%.2f", orderTotal) + "\n"
                       + "Thank you for shopping with us!";

        // Simulating SMTP send
        System.out.println("[EMAIL] To:      " + order.getCustomerEmail());
        System.out.println("[EMAIL] Subject: " + subject);
        System.out.println("[EMAIL] Body:    " + body);
    }
}
