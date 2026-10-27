# Dependency Inversion Principle (DIP)

> High-level modules should **not depend on low-level modules**. Both should depend on **abstractions**.

Depend on interfaces, not concrete classes. This makes high-level logic swappable and testable.

## Bad Example

```java
// BAD — OrderService is tightly coupled to MySQLDatabase
public class MySQLDatabase {
    public void save(Order order) {
        // JDBC logic tied to MySQL
    }
}

public class OrderService {
    private MySQLDatabase db = new MySQLDatabase(); // hard-coded concrete dependency

    public void placeOrder(Order order) {
        db.save(order);
    }
}
```

Switching to PostgreSQL or MongoDB requires modifying `OrderService`.

## Good Example

```java
// GOOD — OrderService depends on an abstraction; any storage works

public interface IOrderRepository {
    void save(Order order);
    Optional<Order> findById(long id);
}

public class MySQLOrderRepository implements IOrderRepository {
    @Override public void save(Order order)          { /* JDBC/MySQL */ }
    @Override public Optional<Order> findById(long id) { return Optional.empty(); }
}

public class MongoOrderRepository implements IOrderRepository {
    @Override public void save(Order order)          { /* MongoDB */ }
    @Override public Optional<Order> findById(long id) { return Optional.empty(); }
}

public class InMemoryOrderRepository implements IOrderRepository { // for unit tests
    private final Map<Long, Order> store = new HashMap<>();
    @Override public void save(Order order)            { store.put(order.getId(), order); }
    @Override public Optional<Order> findById(long id) { return Optional.ofNullable(store.get(id)); }
}

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

## Dependency Injection vs Dependency Inversion

| Term | Meaning |
|------|---------|
| **Dependency Inversion** | Design **principle** — depend on abstractions |
| **Dependency Injection** | Implementation **technique** — pass dependencies from outside |
| **IoC Container** | **Tool** that automates injection (Spring, Guice) |

DI is the most common way to achieve DIP, but DIP is the goal; DI is the mechanism.

## Signs You Are Violating DIP
- `new ConcreteClass()` inside a `@Service` constructor
- Business logic imports `java.sql.*` directly
- Changing the DB layer (MySQL → Postgres) touches many service classes
- Unit tests require a running database
