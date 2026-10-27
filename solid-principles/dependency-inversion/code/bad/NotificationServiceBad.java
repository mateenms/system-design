package solid.dip.bad;

/**
 * BAD EXAMPLE: Dependency Inversion Principle violation.
 *
 * Problem:
 *   NotificationService (high-level business logic) creates and depends
 *   directly on EmailClient (low-level detail).
 *
 * Why it hurts:
 *   - Cannot switch to SMS/push without editing NotificationService
 *   - Cannot unit test without a real email client sending real emails
 *   - The dependency is hidden inside the class
 *
 * @author Mateen
 */
class EmailClient {
    void send(String to, String message) {
        System.out.println("Sending EMAIL to " + to + ": " + message);
    }
}

public class NotificationServiceBad {

    // Hard-coded concrete dependency: tight coupling
    private final EmailClient emailClient = new EmailClient();

    public void notifyUser(String user, String message) {
        emailClient.send(user, message);
    }

    public static void main(String[] args) {
        new NotificationServiceBad().notifyUser("mateen@example.com", "Your order shipped");
    }
}
