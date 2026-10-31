package patterns.prototype.student.good;

/**
 * Shows that a copy is independent from the original.
 *
 * @author Mateen
 */
public class StudentDemo {

    public static void main(String[] args) {
        Student original = new Student("Asha");
        original.addSubject("Maths");

        Student copy = original.copy();
        copy.setName("Ravi");
        copy.addSubject("Physics");  // only the copy changes

        System.out.println("original: " + original);
        System.out.println("copy:     " + copy);
    }
}
