package solid.dip.good;

/**
 * The abstraction. High-level code depends on THIS, never on a concrete sender.
 * Defined next to the business logic that needs it, so the dependency points inward.
 */
public interface MessageSender {
    void send(String to, String message);
}
