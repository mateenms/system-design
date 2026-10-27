# Single Responsibility Principle (SRP)

> A class should have **one, and only one, reason to change**.

Each class or module should do exactly one thing and own that concern completely. "Reason to change" maps to a stakeholder — if Finance, HR, and the DBA can each independently force a class to change, it has three responsibilities.

## Bad Example

```java
// BAD — three different actors can force this class to change
public class Employee {

    // Finance team controls this
    public double calculatePay() {
        return baseSalary * 1.2;
    }

    // DBA controls this (schema changes)
    public void save() {
        // JDBC logic
    }

    // HR team controls this
    public String generateReport() {
        return "Employee Report: " + name;
    }
}
```

## Good Example

```java
// GOOD — one reason to change per class

public class Employee {
    private String name;
    private double baseSalary;
    // getters / setters
}

public class PayCalculator {
    public double calculatePay(Employee employee) {
        return employee.getBaseSalary() * 1.2;
    }
}

public class EmployeeRepository {
    public void save(Employee employee) {
        // JDBC logic — only changes when DB schema changes
    }
}

public class EmployeeReporter {
    public String generateReport(Employee employee) {
        return "Employee Report: " + employee.getName();
    }
}
```

## Real-World Application
- **Spring Boot**: `@Service` for business logic, `@Repository` for DB, `@Controller` for HTTP — each has one responsibility
- **Microservices**: SRP at the service level — each service owns one bounded context

## Signs You Are Violating SRP
- Class name contains "Manager", "Handler", "Helper", "Util", or "And"
- Imports from completely unrelated packages (e.g., both `java.sql` and `javax.mail`)
- Changing a DB column forces you to update email formatting logic
