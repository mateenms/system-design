package patterns.builder.employee;

/**
 * Builder example 4: Employee.
 *
 * "employeeId" and "name" are required. "email" and "age" are optional.
 *
 * @author Mateen
 */
public final class Employee {

    private final String employeeId;   // required
    private final String name;         // required
    private final String email;        // optional
    private final int age;             // optional, 0 means "not given"

    // private: only the Builder can create an Employee
    private Employee(Builder builder) {
        this.employeeId = builder.employeeId;
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }
    public int getAge()           { return age; }

    @Override
    public String toString() {
        return "Employee{employeeId=" + employeeId + ", name=" + name
                + ", email=" + email + ", age=" + age + "}";
    }

    public static class Builder {

        private final String employeeId;
        private final String name;
        private String email;
        private int age;

        // required fields
        public Builder(String employeeId, String name) {
            this.employeeId = employeeId;
            this.name = name;
        }

        // optional fields, "return this" lets you chain the calls
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        // check the values, then create the Employee
        public Employee build() {
            if (employeeId == null || employeeId.isBlank()) {
                throw new IllegalArgumentException("employeeId is required");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("name is required");
            }
            if (age < 0) {
                throw new IllegalArgumentException("age cannot be negative");
            }
            // optional: null (not given) or a real email, never something without "@"
            if (email != null && !email.contains("@")) {
                throw new IllegalArgumentException("email must contain @");
            }
            return new Employee(this);
        }
    }
}
