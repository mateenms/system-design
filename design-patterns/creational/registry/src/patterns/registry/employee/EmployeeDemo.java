package patterns.registry.employee;

import patterns.registry.PrototypeRegistry;

/**
 * One ready-made employee per role, new employees are created by role name.
 *
 * @author Mateen
 */
public class EmployeeDemo {

    public static void main(String[] args) {
        // build each prototype once
        Employee developer = new Employee("Developer", "IT")
                .addSkill("Java")
                .addSkill("Spring Boot")
                .addSkill("SQL");

        Employee tester = new Employee("Tester", "QA")
                .addSkill("Test cases")
                .addSkill("Selenium");

        PrototypeRegistry<Employee> registry = new PrototypeRegistry<>();
        registry.register("developer", developer);
        registry.register("tester", tester);

        // create by role, then set only what is different
        Employee asha = registry.create("developer").id("EMP101").name("Asha");
        Employee ravi = registry.create("developer").id("EMP102").name("Ravi").addSkill("Docker");  // extra skill for Ravi only
        Employee meera = registry.create("tester").id("EMP103").name("Meera");

        System.out.println(asha);
        System.out.println(ravi);
        System.out.println(meera);

        // the registered prototype did not change
        System.out.println("registered: " + registry.create("developer"));
    }
}
