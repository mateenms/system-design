package patterns.prototype.college;

/**
 * Builds one ready-made college, then copies it for three colleges in Nanded and Latur.
 *
 * @author Mateen
 */
public class CollegeDemo {

    public static void main(String[] args) {
        // the ready-made college (prototype): built only once
        College template = new College("Maharashtra")
                .name("Template College")
                .addCourse("Computer Engineering")
                .addCourse("Mechanical Engineering")
                .addCourse("Civil Engineering")
                .addFacility("Library")
                .addFacility("Hostel")
                .addFacility("Computer Lab");

        // copy it, then change only what is different
        College mgm = template.copy()
                .name("MGM COE")
                .city("Nanded")
                .university("Swami Ramanand Teerth Marathwada University");

        College matoshiri = template.copy()
                .name("Matoshiri COE")
                .city("Nanded")
                .university("Swami Ramanand Teerth Marathwada University");

        College bidve = template.copy()
                .name("Bidve COE")
                .city("Latur")
                .university("Swami Ramanand Teerth Marathwada University")
                .addCourse("Electrical Engineering");  // only this copy has the extra course

        System.out.println(mgm);
        System.out.println();
        System.out.println(matoshiri);
        System.out.println();
        System.out.println(bidve);

        // the template did not change: no Electrical Engineering here
        System.out.println();
        System.out.println(template);
    }
}
