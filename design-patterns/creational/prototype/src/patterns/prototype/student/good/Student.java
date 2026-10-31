package patterns.prototype.student.good;

import java.util.ArrayList;
import java.util.List;

/**
 * GOOD: Prototype pattern.
 *
 * A Student can make a copy of itself with copy().
 * The copy gets its own list, so adding a subject to the copy
 * never changes the original.
 *
 * Steps to write a Prototype:
 *   1. add a copy constructor that takes another object of the same class
 *   2. copy simple fields (String, int) directly
 *   3. copy mutable fields (List, Date, ...) into NEW objects, not the same one
 *   4. add a copy() method that calls the copy constructor
 *
 * @author Mateen
 */
public class Student {

    private String name;
    private final List<String> subjects = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    // Step 1: copy constructor
    private Student(Student other) {
        this.name = other.name;                // Step 2: simple field, copy directly
        this.subjects.addAll(other.subjects);  // Step 3: new list with the same items
    }

    // Step 4: make a copy of this student
    public Student copy() {
        return new Student(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", subjects=" + subjects + "}";
    }
}
