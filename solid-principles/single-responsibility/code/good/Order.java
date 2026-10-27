package solid.srp.good;

import java.util.List;
import java.util.Objects;

/**
 * Pure domain model - holds order data only (immutable: items are defensively copied).
 * No logic, no DB, no email. Just data.
 */
public class Order {

    private final int id;
    private final String customerName;
    private final String customerEmail;
    private final List<OrderItem> items;

    public Order(int id, String customerName, String customerEmail, List<OrderItem> items) {
        this.id            = id;
        this.customerName  = Objects.requireNonNull(customerName, "customerName");
        this.customerEmail = Objects.requireNonNull(customerEmail, "customerEmail");
        this.items         = List.copyOf(Objects.requireNonNull(items, "items"));
    }

    public int getId()                  { return id; }
    public String getCustomerName()     { return customerName; }
    public String getCustomerEmail()    { return customerEmail; }
    public List<OrderItem> getItems()   { return items; }
}
