package patterns.prototype.student.bad;

import java.util.ArrayList;
import java.util.List;

/**
 * BAD: a "shallow" copy.
 *
 * The copy gets the SAME subjects list as the original, not its own list.
 * So changing the copy also changes the original (see main below).
 *
 * @author Mateen
 */
public class StudentBad {

    private final String name;
    private final List<String> subjects;

    public StudentBad(String name, List<String> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    // BAD: passes the same list to the new object
    public StudentBad shallowCopy() {
        return new StudentBad(name, subjects);
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    @Override
    public String toString() {
        return "StudentBad{name=" + name + ", subjects=" + subjects + "}";
    }

    public static void main(String[] args) {
        StudentBad original = new StudentBad("Asha", new ArrayList<>(List.of("Maths")));
        StudentBad copy = original.shallowCopy();

        copy.addSubject("Physics");  // we only wanted to change the copy

        System.out.println("original: " + original);  // Physics is here too!
        System.out.println("copy:     " + copy);
    }
}
