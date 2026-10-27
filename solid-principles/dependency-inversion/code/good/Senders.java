package solid.dip.good;

import java.util.ArrayList;
import java.util.List;

/**
 * Low-level details. Each one implements the abstraction.
 * Adding a new channel means adding a new class here, nothing else changes.
 */
public class Senders {

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
        public final List<String> sent = new ArrayList<>();

        @Override
        public void send(String to, String message) {
            sent.add(to + ":" + message);
        }
    }
}
