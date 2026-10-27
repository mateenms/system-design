# Interface Segregation Principle (ISP)

> No client should be forced to **depend on methods it does not use**.

Split fat interfaces into smaller, role-specific ones so every implementor only carries what it genuinely supports.

## Bad Example

```java
// BAD — every device is forced to implement every method
public interface IMultiFunctionDevice {
    void print(Document doc);
    void scan(Document doc);
    void fax(Document doc);       // most printers cannot fax
    void email(Document doc);     // most printers cannot email
}

public class BasicPrinter implements IMultiFunctionDevice {
    @Override public void print(Document doc)  { /* real logic */ }
    @Override public void scan(Document doc)   { /* real logic */ }
    @Override public void fax(Document doc)    { throw new UnsupportedOperationException(); } // violation
    @Override public void email(Document doc)  { throw new UnsupportedOperationException(); } // violation
}
```

## Good Example

```java
// GOOD — segregated interfaces; classes implement only what they support
public interface IPrintable  { void print(Document doc); }
public interface IScannable  { void scan(Document doc); }
public interface IFaxable    { void fax(Document doc); }
public interface IEmailable  { void email(Document doc); }

public class BasicPrinter implements IPrintable {
    @Override public void print(Document doc) { /* real logic */ }
}

public class OfficePrinter implements IPrintable, IScannable, IFaxable, IEmailable {
    @Override public void print(Document doc)  { /* ... */ }
    @Override public void scan(Document doc)   { /* ... */ }
    @Override public void fax(Document doc)    { /* ... */ }
    @Override public void email(Document doc)  { /* ... */ }
}
```

## Real-World Application
- **Spring Data**: `CrudRepository`, `PagingAndSortingRepository`, `JpaRepository` — layered interfaces, clients use only the level they need
- **Repository pattern**: separate `ReadOrderRepository` and `WriteOrderRepository` for reporting vs command services
- **REST API design**: different response DTOs for different consumers (BFF pattern)

## Signs You Are Violating ISP
- Interface has 10+ methods with very different purposes
- Many implementations are mostly `throw new UnsupportedOperationException()`
- Mocking the interface for a unit test requires stubbing many methods the test never calls
