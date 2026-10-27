# Open/Closed Principle (OCP)

> Software entities should be **open for extension** but **closed for modification**.

Add new behaviour by adding new code, not by editing existing, tested code. The mechanism is **polymorphism via interfaces**.

## Bad Example

```java
// BAD — every new shape requires editing calculate()
public class AreaCalculator {
    public double calculate(String type, double a, double b) {
        if (type.equals("CIRCLE"))       return Math.PI * a * a;
        else if (type.equals("RECTANGLE")) return a * b;
        // adding TRIANGLE requires editing this method
        return 0;
    }
}
```

## Good Example

```java
// GOOD — new shapes extend without modifying AreaCalculator

public interface Shape {
    double area();
}

public class Circle implements Shape {
    private final double radius;
    public Circle(double r) { this.radius = r; }
    public double area()    { return Math.PI * radius * radius; }
}

public class Rectangle implements Shape {
    private final double width, height;
    public Rectangle(double w, double h) { this.width = w; this.height = h; }
    public double area()                  { return width * height; }
}

public class Triangle implements Shape {   // new — zero changes to existing code
    private final double base, height;
    public Triangle(double b, double h) { this.base = b; this.height = h; }
    public double area()                 { return 0.5 * base * height; }
}

public class AreaCalculator {
    public double calculate(Shape shape) {
        return shape.area();               // stable — never changes
    }
}
```

## Design Patterns That Implement OCP

| Pattern | How |
|---------|-----|
| Strategy | Swap algorithms behind a stable interface |
| Decorator | Add behaviour without changing the decorated class |
| Template Method | Subclasses fill in steps; skeleton stays fixed |
| Observer | Add new listeners without touching the publisher |

## Signs You Are Violating OCP
- Long `if/else if` or `switch` blocks dispatching on a type string or enum
- Adding a new "type" requires editing 5+ existing files
- Business logic contains `instanceof` checks
