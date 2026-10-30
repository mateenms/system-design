package patterns.builder.student.good;

/**
 * GOOD: Builder pattern.
 *
 * A Student cannot change after it is created. The only way to make one is
 * Student.Builder, so every value has a name and the order does not matter.
 *
 * Steps to write a Builder (same numbers in the code below):
 *   1. final class, final fields, no setters
 *   2. private constructor that takes the Builder
 *   3. static nested Builder with the same fields
 *   4. required fields go in the Builder constructor
 *   5. one method per optional field, each returns "this"
 *   6. build() checks the values, then creates the object
 *
 * @author Mateen
 */
public final class Student {

    // Step 1: final fields, no setters
    private final int rollNo;      // required
    private final String name;     // required
    private final int age;         // optional, 0 means "not given"
    private final String course;   // optional
    private final String city;     // optional

    // Step 2: private constructor, so only the Builder can create a Student
    // (a List field would be copied here with List.copyOf(...), see Pizza)
    private Student(Builder builder) {
        this.rollNo = builder.rollNo;
        this.name = builder.name;
        this.age = builder.age;
        this.course = builder.course;
        this.city = builder.city;
    }

    public int getRollNo()    { return rollNo; }
    public String getName()   { return name; }
    public int getAge()       { return age; }
    public String getCourse() { return course; }
    public String getCity()   { return city; }

    @Override
    public String toString() {
        return "Student{rollNo=" + rollNo + ", name=" + name + ", age=" + age
                + ", course=" + course + ", city=" + city + "}";
    }

    // Step 3: nested Builder
    public static class Builder {

        private final int rollNo;
        private final String name;
        private int age;
        private String course;
        private String city;

        // Step 4: required fields
        public Builder(int rollNo, String name) {
            this.rollNo = rollNo;
            this.name = name;
        }

        // Step 5: optional fields, "return this" lets you chain the calls
        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder course(String course) {
            this.course = course;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        // Step 6: check the values, then create the Student
        public Student build() {
            if (rollNo <= 0) {
                throw new IllegalArgumentException("rollNo must be positive");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("name is required");
            }
            if (age < 0) {
                throw new IllegalArgumentException("age cannot be negative");
            }
            // optional text: null (not given) or a real value, never blank
            if (course != null && course.isBlank()) {
                throw new IllegalArgumentException("course cannot be blank");
            }
            if (city != null && city.isBlank()) {
                throw new IllegalArgumentException("city cannot be blank");
            }
            return new Student(this);
        }
    }
}
