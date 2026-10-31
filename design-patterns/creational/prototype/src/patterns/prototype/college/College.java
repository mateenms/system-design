package patterns.prototype.college;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype example 4: Colleges in Maharashtra, India.
 *
 * Most colleges in the state are set up the same way: same state, same
 * courses, same facilities. Only the name, city and university change.
 * So we make ONE ready-made college and copy it for each city.
 *
 * (The courses, facilities and universities in the demo are sample data, not official information.)
 *
 * Steps to write a Prototype:
 *   1. fields: simple ones (String) and a mutable one (List)
 *   2. private copy constructor
 *   3. copy simple fields directly
 *   4. copy each List into a NEW list
 *   5. copy() returns new College(this)
 *
 * @author Mateen
 */
public final class College {

    // Step 1: fields
    private String name;
    private String city;
    private String university;
    private final String state;
    private final List<String> courses = new ArrayList<>();
    private final List<String> facilities = new ArrayList<>();

    public College(String state) {
        this.state = state;
    }

    // Step 2: private copy constructor
    private College(College other) {
        // Step 3: simple fields, copy directly
        this.name = other.name;
        this.city = other.city;
        this.university = other.university;
        this.state = other.state;
        // Step 4: lists, copy into NEW lists
        this.courses.addAll(other.courses);
        this.facilities.addAll(other.facilities);
    }

    // Step 5: make a copy of this college
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
