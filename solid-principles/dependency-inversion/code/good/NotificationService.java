package solid.dip.good;

/**
 * GOOD EXAMPLE: Dependency Inversion Principle.
 *
 * NotificationService (high-level) depends only on the MessageSender
 * abstraction. The concrete sender is injected through the constructor
 * (Dependency Injection), so it can be swapped without touching this class.
 */
public class NotificationService {

    private final MessageSender sender;

    // Constructor injection: dependency is explicit and replaceable
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String user, String message) {
        sender.send(user, message);
    }

    public static void main(String[] args) {
        // Production wiring: choose the implementation at the edge of the app
        new NotificationService(new Senders.EmailSender())
                .notifyUser("mateen@example.com", "Your order shipped");

        new NotificationService(new Senders.SmsSender())
                .notifyUser("+1-555-0100", "Your order shipped");

        // Test wiring: no real email/SMS needed
        Senders.FakeSender fake = new Senders.FakeSender();
        new NotificationService(fake).notifyUser("test-user", "hello");
        System.out.println("Fake captured: " + fake.sent);
    }
}
