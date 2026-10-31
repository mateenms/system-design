package patterns.registry.student;

import patterns.registry.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry example 2: Student.
 *
 * Each branch (Computer Science, Mechanical ...) has its own ready-made student
 * with the branch subjects. A new student is a copy with a name.
 *
 * @author Mateen
 */
public final class Student implements Prototype<Student> {

    private String name;
    private final String branch;
    private final List<String> subjects = new ArrayList<>();

    public Student(String branch) {
        this.branch = branch;
    }

    // copy constructor
    private Student(Student other) {
        this.name = other.name;
        this.branch = other.branch;
        this.subjects.addAll(other.subjects);  // its own list
    }

    @Override
    public Student copy() {
        return new Student(this);
    }

    // each method returns "this", so calls can be chained
    public Student name(String name) {
        this.name = name;
        return this;
    }

    public Student addSubject(String subject) {
        subjects.add(subject);
        return this;
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", branch=" + branch + ", subjects=" + subjects + "}";
    }
}
