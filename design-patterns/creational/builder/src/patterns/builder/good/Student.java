package patterns.builder.good;

/**
 * GOOD: Builder pattern.
 *
 * Student is immutable. The only way to create one is through Student.Builder,
 * so every call reads like a sentence and the order of the values does not matter.
 *
 * Steps to write a Builder:
 *   Step 1: make the class final, with final fields (no setters)
 *   Step 2: make the constructor private and let it take the Builder
 *   Step 3: add a static nested Builder class with the same fields
 *   Step 4: put the required fields in the Builder constructor
 *   Step 5: add one method per optional field that returns "this"
 *   Step 6: add build(): validate, then create the object
 *
 * @author Mateen
 */
public final class Student {

    // Step 1: final fields, no setters
    private final int rollNo;      // required
    private final String name;     // required
    private final int age;         // optional (0 means "not given")
    private final String course;   // optional
    private final String city;     // optional

    // Step 2: private constructor. Nobody can call new Student(...), they must use the Builder.
    // Tip: if you add a mutable field (like a List), copy it here, e.g. List.copyOf(builder.subjects),
    // so the Student never shares it with the Builder.
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

    // Step 3: static nested Builder with the same fields
    public static class Builder {

        private final int rollNo;
        private final String name;
        private int age;
        private String course;
        private String city;

        // Step 4: required fields go in the Builder constructor
        public Builder(int rollNo, String name) {
            this.rollNo = rollNo;
            this.name = name;
        }

        // Step 5: optional fields. Set a value and return "this" so calls can be chained
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

        // Step 6: validate everything once, then create the Student
        public Student build() {
            if (rollNo <= 0) {
                throw new IllegalStateException("rollNo must be positive");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("name is required");
            }
            if (age < 0) {
                throw new IllegalStateException("age cannot be negative");
            }
            // optional text fields: either not given (null) or a real value, never blank
            if (course != null && course.isBlank()) {
                throw new IllegalStateException("course cannot be blank");
            }
            if (city != null && city.isBlank()) {
                throw new IllegalStateException("city cannot be blank");
            }
            return new Student(this);
        }
    }
}
