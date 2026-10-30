package patterns.builder.student.good;

/**
 * Shows how to create Student objects with the Builder.
 *
 * @author Mateen
 */
public class StudentDemo {

    public static void main(String[] args) {
        // required fields only
        Student basic = new Student.Builder(1, "Asha").build();

        // required + optional fields, in any order
        Student full = new Student.Builder(2, "Ravi")
                .city("Pune")
                .course("Java")
                .age(21)
                .build();

        System.out.println(basic);
        System.out.println(full);

        // build() rejects bad values
        reject(new Student.Builder(3, " "));              // blank name
        reject(new Student.Builder(0, "Meera"));          // rollNo must be positive
        reject(new Student.Builder(4, "Kiran").age(-1));  // negative age
        reject(new Student.Builder(5, "Zoya").city(""));  // blank city
    }

    private static void reject(Student.Builder builder) {
        try {
            builder.build();
            System.out.println("Accepted (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
