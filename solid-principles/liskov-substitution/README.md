# Liskov Substitution Principle (LSP)

> Objects of a subclass should be **substitutable** for objects of the parent class without altering the correctness of the program.

If `S` is a subtype of `T`, using an `S` anywhere a `T` is expected must still work correctly.

## Bad Example

```java
// BAD — Square breaks Rectangle's implicit contract
public class Rectangle {
    protected int width, height;

    public void setWidth(int w)  { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public int area()            { return width * height; }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int w) {
        this.width = w;
        this.height = w;  // unexpected side-effect breaks caller's expectation
    }
    @Override
    public void setHeight(int h) {
        this.width = h;
        this.height = h;
    }
}

// Code written for Rectangle breaks when handed a Square
void resize(Rectangle r) {
    r.setWidth(4);
    r.setHeight(5);
    System.out.println(r.area()); // expects 20, gets 25 when r is a Square
}
```

## Good Example

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

// Works correctly for any Shape subtype — true substitutability
void printArea(Shape s) { System.out.println("Area: " + s.area()); }
```

## Real-World Application
- **Spring Data**: child repository must fully honour all parent repository contracts
- **Collections**: `UnmodifiableList` that throws on `add()` should not extend a mutable `List`
- **Test doubles**: a mock `PaymentGateway` must behave exactly like the real one in all respects callers rely on

## Signs You Are Violating LSP
- Subclass method throws `UnsupportedOperationException`
- Business logic uses `instanceof` to check the concrete type before calling a method
- Tests for the parent class fail when run against the subclass
