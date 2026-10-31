package patterns.registry.employee;

import patterns.registry.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry example 3: Employee.
 *
 * Each role (Developer, Tester ...) has a ready-made employee with the
 * department and skills of that role. A new employee is a copy with a name and id.
 *
 * @author Mateen
 */
public final class Employee implements Prototype<Employee> {

    private String id;
    private String name;
    private final String designation;
    private final String department;
    private final List<String> skills = new ArrayList<>();

    public Employee(String designation, String department) {
        this.designation = designation;
        this.department = department;
    }

    // copy constructor
    private Employee(Employee other) {
        this.id = other.id;
        this.name = other.name;
        this.designation = other.designation;
        this.department = other.department;
        this.skills.addAll(other.skills);  // its own list
    }

    @Override
    public Employee copy() {
        return new Employee(this);
    }

    // each method returns "this", so calls can be chained
    public Employee id(String id) {
        this.id = id;
        return this;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public Employee addSkill(String skill) {
        skills.add(skill);
        return this;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name=" + name + ", " + designation
                + ", department=" + department + ", skills=" + skills + "}";
    }
}
