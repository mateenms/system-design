package solid.dip.good;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Low-level details. Each one implements the abstraction.
 * Adding a new channel means adding a new class here, nothing else changes.
 *
 * @author Mateen
 */
public final class Senders {

    private Senders() { }   // holder class: not meant to be instantiated

    public static class EmailSender implements MessageSender {
        @Override
        public void send(String to, String message) {
            System.out.println("EMAIL to " + to + ": " + message);
        }
    }

    public static class SmsSender implements MessageSender {
        @Override
        public void send(String to, String message) {
            System.out.println("SMS to " + to + ": " + message);
        }
    }

    /** Test double: records messages instead of sending them. */
    public static class FakeSender implements MessageSender {
        private final List<String> sent = new ArrayList<>();

        public List<String> getSent() { return Collections.unmodifiableList(sent); }

        @Override
        public void send(String to, String message) {
            sent.add(to + ":" + message);
        }
    }
}
