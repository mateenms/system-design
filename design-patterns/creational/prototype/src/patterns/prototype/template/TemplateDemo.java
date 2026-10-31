package patterns.prototype.template;

/**
 * Loads the template once and copies it for each student.
 *
 * @author Mateen
 */
public class TemplateDemo {

    public static void main(String[] args) {
        // loaded only once
        EmailTemplate welcome = new EmailTemplate("Welcome!", "Your classes start on Monday.");
        welcome.attach("timetable.pdf");

        // each copy is fast, and each one can be changed on its own
        EmailTemplate toAsha = welcome.copy().to("asha@example.com");
        EmailTemplate toRavi = welcome.copy().to("ravi@example.com").attach("fees.pdf");

        System.out.println(toAsha);
        System.out.println(toRavi);
        System.out.println(welcome);  // the original is not changed
    }
}
