package patterns.builder.bad;

/**
 * BAD: no Builder (the "telescoping constructor" problem).
 *
 * Every combination of fields needs its own constructor, and a call like
 * new StudentBad(1, "Asha", 20, "Java", "Pune") is hard to read:
 * which value is the course? which one is the city? Swap two Strings and
 * the compiler will not warn you (see main below).
 *
 * @author Mateen
 */
public class StudentBad {

    private final int rollNo;
    private final String name;
    private final int age;
    private final String course;
    private final String city;

    public StudentBad(int rollNo, String name) {
        this(rollNo, name, 0, null, null);
    }

    public StudentBad(int rollNo, String name, int age) {
        this(rollNo, name, age, null, null);
    }

    public StudentBad(int rollNo, String name, int age, String course) {
        this(rollNo, name, age, course, null);
    }

    public StudentBad(int rollNo, String name, int age, String course, String city) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.course = course;
        this.city = city;
    }

    @Override
    public String toString() {
        return "StudentBad{rollNo=" + rollNo + ", name=" + name + ", age=" + age
                + ", course=" + course + ", city=" + city + "}";
    }

    public static void main(String[] args) {
        // Mistake on purpose: the order is (course, city), but city and course are swapped here.
        StudentBad student = new StudentBad(1, "Asha", 20, "Pune", "Java");
        System.out.println(student); // prints course=Pune, city=Java and nobody notices
    }
}
