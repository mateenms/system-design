package patterns.builder.email;

/**
 * Runs the Email builder.
 *
 * @author Mateen
 */
public class EmailDemo {

    public static void main(String[] args) {
        Email simple = new Email.Builder("asha@example.com", "Hello").build();

        Email full = new Email.Builder("ravi@example.com", "Class notes")
                .cc("teacher@example.com")
                .body("Please find the notes attached.")
                .build();

        System.out.println(simple);
        System.out.println(full);

        try {
            new Email.Builder("not-an-email", "Hi").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
