package patterns.registry.college;

import patterns.registry.PrototypeRegistry;

/**
 * One ready-made college per type, new colleges are created by type name.
 *
 * @author Mateen
 */
public class CollegeDemo {

    public static void main(String[] args) {
        // build each prototype once
        College engineering = new College("Maharashtra").name("Template Engineering College")
                .addCourse("Computer Engineering")
                .addCourse("Mechanical Engineering")
                .addCourse("Civil Engineering")
                .addFacility("Library")
                .addFacility("Hostel")
                .addFacility("Computer Lab");

        College pharmacy = new College("Maharashtra").name("Template Pharmacy College")
                .addCourse("B.Pharm")
                .addCourse("D.Pharm")
                .addFacility("Library")
                .addFacility("Pharmacy Lab");

        PrototypeRegistry<College> registry = new PrototypeRegistry<>();
        registry.register("engineering", engineering);
        registry.register("pharmacy", pharmacy);

        // create by type, then set only what is different
        College mgm = registry.create("engineering")
                .name("MGM COE").city("Nanded")
                .university("Swami Ramanand Teerth Marathwada University");

        College matoshiri = registry.create("engineering")
                .name("Matoshiri COE").city("Nanded")
                .university("Swami Ramanand Teerth Marathwada University");

        College bidve = registry.create("engineering")
                .name("Bidve COE").city("Latur")
                .university("Swami Ramanand Teerth Marathwada University")
                .addCourse("Electrical Engineering");  // extra course for this college only

        College sample = registry.create("pharmacy")
                .name("Sample Pharmacy College").city("Nanded")
                .university("Swami Ramanand Teerth Marathwada University");

        System.out.println(mgm);
        System.out.println();
        System.out.println(matoshiri);
        System.out.println();
        System.out.println(bidve);
        System.out.println();
        System.out.println(sample);

        // the registered prototype did not change: no Electrical Engineering here
        System.out.println();
        System.out.println(registry.create("engineering"));
    }
}
