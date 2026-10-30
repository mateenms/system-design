package patterns.builder.employee;

/**
 * Runs the Employee builder.
 *
 * @author Mateen
 */
public class EmployeeDemo {

    public static void main(String[] args) {
        // required fields only
        Employee basic = new Employee.Builder("EMP100", "Asha").build();

        // required + optional fields
        Employee full = new Employee.Builder("EMP101", "John")
                .email("john@example.com")
                .age(30)
                .build();

        System.out.println(basic);
        System.out.println(full);

        // build() rejects bad values
        reject(new Employee.Builder("", "Ravi"));                      // blank id
        reject(new Employee.Builder("EMP102", "Meera").age(-5));       // negative age
        reject(new Employee.Builder("EMP103", "Kiran").email("kiran")); // email without @
    }

    private static void reject(Employee.Builder builder) {
        try {
            builder.build();
            System.out.println("Accepted (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
