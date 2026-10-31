package patterns.prototype.template;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype example 2: Email template.
 *
 * Loading the template is "slow" (imagine reading a file or a database).
 * Do it ONCE, then copy() the template for every person.
 *
 * @author Mateen
 */
public final class EmailTemplate {

    private String to;
    private final String subject;
    private final String body;
    private final List<String> attachments = new ArrayList<>();

    // the slow way: build the template from scratch
    public EmailTemplate(String subject, String body) {
        System.out.println("Loading template... (slow)");
        this.subject = subject;
        this.body = body;
    }

    // copy constructor: fast, no loading
    private EmailTemplate(EmailTemplate other) {
        this.to = other.to;
        this.subject = other.subject;
        this.body = other.body;
        this.attachments.addAll(other.attachments);
    }

    public EmailTemplate copy() {
        return new EmailTemplate(this);
    }

    public EmailTemplate to(String to) {
        this.to = to;
        return this;
    }

    public EmailTemplate attach(String fileName) {
        attachments.add(fileName);
        return this;
    }

    @Override
    public String toString() {
        return "Email{to=" + to + ", subject=" + subject + ", body=" + body
                + ", attachments=" + attachments + "}";
    }
}
