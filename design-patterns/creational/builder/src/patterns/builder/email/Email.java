package patterns.builder.email;

/**
 * Builder example 3: Email.
 *
 * "to" and "subject" are required. "cc" and "body" are optional.
 * The Builder methods are named after the fields (cc, body), like real libraries do.
 *
 * @author Mateen
 */
public final class Email {

    private final String to;        // required
    private final String subject;   // required
    private final String cc;        // optional
    private final String body;      // optional

    private Email(Builder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.cc = builder.cc;
        this.body = builder.body;
    }

    @Override
    public String toString() {
        return "Email{to=" + to + ", subject=" + subject + ", cc=" + cc + ", body=" + body + "}";
    }

    public static class Builder {

        private final String to;
        private final String subject;
        private String cc;
        private String body;

        public Builder(String to, String subject) {
            this.to = to;
            this.subject = subject;
        }

        public Builder cc(String cc) {
            this.cc = cc;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Email build() {
            if (to == null || !to.contains("@")) {
                throw new IllegalArgumentException("to must be a valid email address");
            }
            if (subject == null || subject.isBlank()) {
                throw new IllegalArgumentException("subject is required");
            }
            return new Email(this);
        }
    }
}
