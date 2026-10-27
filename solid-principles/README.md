# SOLID Principles — Complete Theory Guide (Java)

> "The goal of the SOLID principles is the creation of mid-level software structures that are **tolerant of change**, **easy to understand**, and the **basis of components that can be reused** in many software systems." — Robert C. Martin

---

## Table of Contents

1. [What is SOLID?](#1-what-is-solid)
2. [Why SOLID Matters](#2-why-solid-matters)
3. [S — Single Responsibility Principle](#3-s--single-responsibility-principle)
4. [O — Open/Closed Principle](#4-o--openclosed-principle)
5. [L — Liskov Substitution Principle](#5-l--liskov-substitution-principle)
6. [I — Interface Segregation Principle](#6-i--interface-segregation-principle)
7. [D — Dependency Inversion Principle](#7-d--dependency-inversion-principle)
8. [How the Principles Work Together](#8-how-the-principles-work-together)
9. [SOLID vs Other Principles](#9-solid-vs-other-principles)
10. [Common Misconceptions](#10-common-misconceptions)
11. [Interview Questions](#11-interview-questions)

---

## 1. What is SOLID?

SOLID is an acronym coined by **Michael Feathers** from five principles introduced and popularised by **Robert C. Martin ("Uncle Bob")** in the early 2000s. They are design guidelines for **object-oriented** systems, and Java — being a strictly OO language — is where they are most naturally expressed.

| Letter | Principle | Author's One-Liner |
|--------|-----------|-------------------|
| **S** | Single Responsibility | A class should have one, and only one, reason to change. |
| **O** | Open/Closed | Open for extension, closed for modification. |
| **L** | Liskov Substitution | Subtypes must be substitutable for their base types. |
| **I** | Interface Segregation | Many client-specific interfaces are better than one general-purpose interface. |
| **D** | Dependency Inversion | Depend upon abstractions, not concretions. |

---

## 2. Why SOLID Matters

Without these principles, large codebases tend toward:

- **Rigidity** — a small change requires modifying many classes
- **Fragility** — unrelated modules break when you touch something
- **Immobility** — hard to extract and reuse components in other projects
- **Viscosity** — it is easier to hack than to do it right

Applying SOLID leads to:
- Easier unit testing (each unit has a single, clear job)
- Easier extension (add new behaviour without touching old code)
- Safer refactoring (change is local, not global)
- Better readability (small, focused classes and interfaces)

---

## 3. S — Single Responsibility Principle

### Core Idea

> A class should have **one, and only one, reason to change**.

"Reason to change" maps to a **stakeholder or actor**. If Finance, HR, and the DBA can all independently force a class to change, that class has three responsibilities.

### Mental Model

Think of it as **cohesion**. Group things that change together; separate things that change for different reasons.

### Bad Example

```java
// BAD — three actors can force this class to change
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

### Good Example

```java
// GOOD — one reason to change per class

public class Employee {
    private String name;
    private double baseSalary;
    // getters/setters
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

### Real-World Application
- **Spring Boot**: `@Service` for business logic, `@Repository` for DB, `@Controller` for HTTP — each has one responsibility
- **Microservices**: SRP at service level — each service owns one bounded context
- **Utility classes**: a `StringUtils` class doing 40 things is an SRP smell

### Signs You Are Violating SRP
- Class name contains "Manager", "Handler", "Helper", "Util", "And"
- The class imports from completely unrelated packages (e.g., both `java.sql` and `javax.mail`)
- Changing a DB column forces you to update email formatting logic

---

## 4. O — Open/Closed Principle

### Core Idea

> Software entities should be **open for extension** but **closed for modification**.

Add new behaviour by **adding new code**, not by editing existing, tested code.

### Mental Model

Think of a **plugin architecture**. Adding a plugin does not modify the core application. The enabling mechanism is **abstraction** — define a stable interface and extend it.

### Bad Example

```java
// BAD — every new discount type requires editing applyDiscount()
public class DiscountService {
    public double applyDiscount(Order order, String discountType) {
        if (discountType.equals("PERCENTAGE")) {
            return order.getTotal() * 0.9;
        } else if (discountType.equals("FIXED")) {
            return order.getTotal() - 10;
        }
        // adding SEASONAL requires editing this method
        return order.getTotal();
    }
}
```

### Good Example

```java
// GOOD — new discount types extend without modifying DiscountService

public interface DiscountStrategy {
    double apply(double total);
}

public class PercentageDiscount implements DiscountStrategy {
    @Override
    public double apply(double total) { return total * 0.9; }
}

public class FixedDiscount implements DiscountStrategy {
    @Override
    public double apply(double total) { return total - 10; }
}

public class SeasonalDiscount implements DiscountStrategy { // new — zero changes to existing code
    @Override
    public double apply(double total) { return total * 0.75; }
}

public class DiscountService {
    public double applyDiscount(Order order, DiscountStrategy strategy) {
        return strategy.apply(order.getTotal()); // never changes
    }
}
```

### Design Patterns That Implement OCP

| Pattern | How |
|---------|-----|
| Strategy | Swap algorithms behind a stable interface |
| Decorator | Add behaviour without modifying the decorated class |
| Template Method | Subclasses override steps; skeleton is fixed |
| Observer | Add new listeners without touching the publisher |

### Signs You Are Violating OCP
- Long `if/else if` or `switch` blocks dispatching on a type string/enum
- Adding a new "type" requires editing 5+ existing files
- Business logic contains `instanceof` checks

---

## 5. L — Liskov Substitution Principle

### Core Idea

> If `S` is a subtype of `T`, then objects of type `T` may be replaced with objects of type `S` **without altering the correctness of the program**.

Anywhere you use the parent class, you must be able to use the child class without surprises.

### Formal Contract Rules (Barbara Liskov, 1987)
1. **Preconditions** cannot be strengthened in the subtype
2. **Postconditions** cannot be weakened in the subtype
3. **Invariants** of the supertype must be preserved
4. The subtype must not throw new checked exceptions not declared by the base

### Classic Violation: Rectangle / Square

```java
// BAD — Square breaks Rectangle's implicit contract
public class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width)   { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public int area() { return width * height; }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;  // side-effect violates caller's expectation
    }
    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}

// Code written for Rectangle breaks when handed a Square:
public void resize(Rectangle r) {
    r.setWidth(5);
    r.setHeight(10);
    assert r.area() == 50; // FAILS for Square — area is 100
}
```

### Good Example

```java
// GOOD — share only a true common abstraction
public interface Shape {
    int area();
}

public class Rectangle implements Shape {
    private int width, height;
    public Rectangle(int w, int h) { this.width = w; this.height = h; }
    @Override public int area() { return width * height; }
}

public class Square implements Shape {
    private int side;
    public Square(int side) { this.side = side; }
    @Override public int area() { return side * side; }
}
```

### Real-World Application
- **Spring Data**: `JpaRepository` extends `CrudRepository` — every CRUD method must work correctly on the child
- **Collection API**: `UnmodifiableList` that throws on `add()` — callers expecting a mutable `List` break
- **Test doubles**: a mock `PaymentGateway` must behave like the real one in all respects callers rely on

### Signs You Are Violating LSP
- A subclass method throws `UnsupportedOperationException`
- Business logic uses `instanceof` to check the concrete type before calling a method
- Tests for the parent class fail when run against the subclass

---

## 6. I — Interface Segregation Principle

### Core Idea

> No client should be forced to **depend on methods it does not use**.

Prefer many small, role-specific interfaces over one large general-purpose interface.

### Mental Model

Think of a **TV remote vs a universal remote**. A universal remote has 80 buttons nobody uses. A role-specific interface exposes only what its client needs.

### Bad Example

```java
// BAD — every device must implement every method
public interface IMultiFunctionDevice {
    void print(Document doc);
    void scan(Document doc);
    void fax(Document doc);       // most printers cannot fax
    void email(Document doc);     // most printers cannot email
}

public class BasicPrinter implements IMultiFunctionDevice {
    @Override public void print(Document doc) { /* real logic */ }
    @Override public void scan(Document doc)  { /* real logic */ }
    @Override public void fax(Document doc)   { throw new UnsupportedOperationException(); }
    @Override public void email(Document doc) { throw new UnsupportedOperationException(); }
}
```

### Good Example

```java
// GOOD — segregated interfaces
public interface IPrintable  { void print(Document doc); }
public interface IScannable  { void scan(Document doc); }
public interface IFaxable    { void fax(Document doc); }
public interface IEmailable  { void email(Document doc); }

public class BasicPrinter implements IPrintable {
    @Override public void print(Document doc) { /* ... */ }
}

public class ScannerPrinter implements IPrintable, IScannable {
    @Override public void print(Document doc) { /* ... */ }
    @Override public void scan(Document doc)  { /* ... */ }
}

public class OfficePrinter implements IPrintable, IScannable, IFaxable, IEmailable {
    @Override public void print(Document doc)  { /* ... */ }
    @Override public void scan(Document doc)   { /* ... */ }
    @Override public void fax(Document doc)    { /* ... */ }
    @Override public void email(Document doc)  { /* ... */ }
}
```

### Real-World Application
- **Spring Data**: `CrudRepository`, `PagingAndSortingRepository`, `JpaRepository` — each layer adds methods; clients use only what they need
- **Repository pattern**: separate `ReadOrderRepository` and `WriteOrderRepository` — reporting services need read-only
- **REST API design**: different response DTOs for different clients (Backend-for-Frontend pattern)

### Signs You Are Violating ISP
- Interface has 10+ methods with very different purposes
- Many implementations are mostly `throw new UnsupportedOperationException()`
- Mocking the interface for a unit test requires stubbing methods the test never calls

---

## 7. D — Dependency Inversion Principle

### Core Idea

> A. High-level modules should **not** depend on low-level modules. Both should depend on **abstractions**.
> B. Abstractions should not depend on details. Details should depend on abstractions.

### Mental Model

Think of a **power socket standard**. Your laptop (high-level) doesn't wire itself directly to a coal plant (low-level). Both depend on the socket standard (abstraction). You can swap the plant (coal → solar) without modifying the laptop.

### Dependency Injection vs Dependency Inversion

| Term | Meaning |
|------|---------|
| **Dependency Inversion** | Design **principle** — depend on abstractions |
| **Dependency Injection** | Implementation **technique** — pass dependencies from outside |
| **IoC Container** | **Tool** that automates injection (Spring, Guice, Dagger) |

DI is the most common way to achieve DIP, but DIP is the goal; DI is the mechanism.

### Bad Example

```java
// BAD — high-level OrderService depends on low-level MySQLOrderRepository
public class MySQLOrderRepository {
    public void save(Order order) {
        // JDBC logic tied to MySQL
    }
}

public class OrderService {
    private MySQLOrderRepository repository = new MySQLOrderRepository(); // tightly coupled

    public void placeOrder(Order order) {
        repository.save(order);
    }
}
```

### Good Example

```java
// GOOD — both depend on the IOrderRepository abstraction

public interface IOrderRepository {
    void save(Order order);
    Optional<Order> findById(long id);
}

public class MySQLOrderRepository implements IOrderRepository {
    @Override public void save(Order order)               { /* JDBC/MySQL logic */ }
    @Override public Optional<Order> findById(long id)    { /* JDBC/MySQL logic */ return Optional.empty(); }
}

public class MongoOrderRepository implements IOrderRepository {
    @Override public void save(Order order)               { /* MongoDB logic */ }
    @Override public Optional<Order> findById(long id)    { /* MongoDB logic */ return Optional.empty(); }
}

public class InMemoryOrderRepository implements IOrderRepository { // for unit tests
    private Map<Long, Order> store = new HashMap<>();
    @Override public void save(Order order)               { store.put(order.getId(), order); }
    @Override public Optional<Order> findById(long id)    { return Optional.ofNullable(store.get(id)); }
}

// High-level module — depends only on the abstraction
public class OrderService {
    private final IOrderRepository repository;

    public OrderService(IOrderRepository repository) { // injected from outside
        this.repository = repository;
    }

    public void placeOrder(Order order) {
        repository.save(order);
    }
}

// Spring wiring (composition root)
@Configuration
public class AppConfig {
    @Bean
    public IOrderRepository orderRepository() { return new MySQLOrderRepository(); }

    @Bean
    public OrderService orderService(IOrderRepository repo) { return new OrderService(repo); }
}
```

### Signs You Are Violating DIP
- `new ConcreteClass()` inside a `@Service` or `@Component` constructor
- Business logic imports `java.sql.*` directly
- Changing the DB layer (MySQL → Postgres) touches many service classes
- Unit tests require a running database

---

## 8. How the Principles Work Together

```
SRP  → small, focused classes with a clear purpose
OCP  → those classes are extended without modification
LSP  → extensions are safe substitutes; contracts are honoured
ISP  → clients depend only on what they use (smaller contracts)
DIP  → high-level code depends on those small contracts, not implementations
```

A typical design flow:

1. **SRP** — identify all responsibilities; split into separate classes
2. **ISP** — define small interfaces for each role those classes play
3. **DIP** — make high-level classes depend on those interfaces
4. **OCP** — add new behaviour by implementing the interface, not by if/else
5. **LSP** — verify every implementation fully honours the interface contract

---

## 9. SOLID vs Other Principles

| Principle | Relationship with SOLID |
|-----------|------------------------|
| **DRY** (Don't Repeat Yourself) | SRP centralises each concern, naturally reducing duplication |
| **KISS** (Keep It Simple) | ISP and SRP push toward simpler, smaller units |
| **YAGNI** (You Aren't Gonna Need It) | Do not add abstractions for OCP/DIP until variation is actually needed |
| **Law of Demeter** | Reinforces SRP and DIP — talk only to direct collaborators |
| **Composition over Inheritance** | Supports OCP and LSP — prefer delegation over deep hierarchies |

---

## 10. Common Misconceptions

| Misconception | Reality |
|---------------|---------|
| SOLID means always creating an interface | Only when variation is expected or testability requires it |
| SRP means one method per class | One *reason to change*, not one method |
| OCP means never modifying code | Bug fixes are fine; hacking new behaviour into if/else is not |
| LSP only applies to inheritance | Also applies to interface implementations |
| DIP requires Spring or a DI framework | Manual constructor injection in a composition root is sufficient |
| SOLID is only for large systems | Small systems benefit too; the cost is low |

---

## 11. Interview Questions

All questions and answers are in one file:

→ **[interview-questions/interview-question-answer.md](interview-questions/interview-question-answer.md)**

Covers: Basic · Intermediate (per principle) · Advanced · Real-World Spring Boot Scenarios · Coding Challenges A–E

---

## Runnable Code Examples

Each principle folder has `code/bad/` (the violation) and `code/good/` (the fix), all plain Java 17+ with no build tool:

| Principle | Good example entry point |
|-----------|--------------------------|
| SRP | `single-responsibility/code/good/OrderWorkflow.java` |
| OCP | `open-closed/code/good/DiscountService.java` |
| LSP | `liskov-substitution/code/good/BirdDemo.java` |
| ISP | `interface-segregation/code/good/Devices.java` |
| DIP | `dependency-inversion/code/good/NotificationService.java` |

Compile and run from the `solid-principles/` folder (SRP shown; change the folder and class for the others):

```bash
javac -d out single-responsibility/code/good/*.java
java -cp out solid.srp.good.OrderWorkflow
```

Package names: `solid.<srp|ocp|lsp|isp|dip>.<bad|good>`. The bad versions have a `main` too, so you can run them and see the problem.

---

## Quick Cheat Sheet

```
S — One class, one reason to change
O — Extend by adding new classes, not by editing existing ones
L — Subclass must keep every promise the parent made
I — Small focused interfaces beat one fat interface
D — Depend on what something does (interface), not what it is (class)
```
