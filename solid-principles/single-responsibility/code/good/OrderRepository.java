package solid.srp.good;

import java.util.Optional;

/**
 * [OK] GOOD EXAMPLE - Responsibility 2: Database Access
 *
 * Single Responsibility:
 *   This class ONLY reads and writes orders to/from the database.
 *   It changes ONLY when the DB schema or persistence technology changes.
 *
 * Who owns this class? -> Backend / DBA team.
 */
public class OrderRepository {

    /**
     * Persists the order to the database.
     *
     * In a real Spring Boot app this would use:
     *   - JPA:    entityManager.persist(order)
     *   - JDBC:   jdbcTemplate.update("INSERT INTO orders ...", ...)
     *
     * @param order the order to save
     */
    public void save(Order order) {
        // Simulating a DB save
        System.out.println("[DB] Saving order #" + order.getId()
                + " for customer: " + order.getCustomerName());
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID to look up
     * @return the found order, or empty if not found
     */
    public Optional<Order> findById(int orderId) {
        // Simulating a DB read
        System.out.println("[DB] Fetching order #" + orderId);
        return Optional.empty(); // placeholder - real code would query the DB
    }
}
