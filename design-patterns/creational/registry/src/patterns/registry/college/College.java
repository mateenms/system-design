package patterns.registry.college;

import patterns.registry.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry example 4: Colleges in Maharashtra, India.
 *
 * Each college type (Engineering, Pharmacy ...) has a ready-made college with
 * its courses and facilities. A new college is a copy with a name, city and university.
 *
 * (The courses, facilities and universities in the demo are sample data, not official information.)
 *
 * @author Mateen
 */
public final class College implements Prototype<College> {

    private String name;
    private String city;
    private String university;
    private final String state;
    private final List<String> courses = new ArrayList<>();
    private final List<String> facilities = new ArrayList<>();

    public College(String state) {
        this.state = state;
    }

    // copy constructor
    private College(College other) {
        this.name = other.name;
        this.city = other.city;
        this.university = other.university;
        this.state = other.state;
        this.courses.addAll(other.courses);        // its own list
        this.facilities.addAll(other.facilities);  // its own list
    }

    @Override
    public College copy() {
        return new College(this);
    }

    // each method returns "this", so calls can be chained
    public College name(String name) {
        this.name = name;
        return this;
    }

    public College city(String city) {
        this.city = city;
        return this;
    }

    public College university(String university) {
        this.university = university;
        return this;
    }

    public College addCourse(String course) {
        courses.add(course);
        return this;
    }

    public College addFacility(String facility) {
        facilities.add(facility);
        return this;
    }

    @Override
    public String toString() {
        return name + " (" + city + ", " + state + ")"
                + "\n  University : " + university
                + "\n  Courses    : " + courses
                + "\n  Facilities : " + facilities;
    }
}
