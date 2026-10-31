package patterns.registry.student;

import patterns.registry.PrototypeRegistry;

/**
 * One ready-made student per branch, new students are created by branch name.
 *
 * @author Mateen
 */
public class StudentDemo {

    public static void main(String[] args) {
        // build each prototype once
        Student cse = new Student("Computer Science").name("Template Student")
                .addSubject("Java")
                .addSubject("DBMS")
                .addSubject("Operating Systems");

        Student mech = new Student("Mechanical").name("Template Student")
                .addSubject("Thermodynamics")
                .addSubject("Fluid Mechanics");

        PrototypeRegistry<Student> registry = new PrototypeRegistry<>();
        registry.register("cse", cse);
        registry.register("mechanical", mech);

        // create by branch, then set only what is different
        Student asha = registry.create("cse").name("Asha");
        Student ravi = registry.create("cse").name("Ravi").addSubject("Machine Learning");  // extra subject for Ravi only
        Student meera = registry.create("mechanical").name("Meera");

        System.out.println(asha);
        System.out.println(ravi);
        System.out.println(meera);

        // the registered prototype did not change
        System.out.println("registered: " + registry.create("cse"));
    }
}
