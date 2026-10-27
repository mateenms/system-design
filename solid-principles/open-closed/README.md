# Open/Closed Principle (OCP)

> Software entities should be **open for extension** but **closed for modification**.

Add new behaviour by adding new code, not by changing existing code.

## Bad Example

```python
# bad.py — adding a new discount type forces editing this function
class DiscountCalculator:
    def calculate(self, order, discount_type):
        if discount_type == "percentage":
            return order.total * 0.9
        elif discount_type == "fixed":
            return order.total - 10
        # every new type requires editing here
```

## Good Example

```python
# good.py — new discount types extend without touching existing code
from abc import ABC, abstractmethod

class Discount(ABC):
    @abstractmethod
    def apply(self, total: float) -> float: ...

class PercentageDiscount(Discount):
    def apply(self, total):
        return total * 0.9

class FixedDiscount(Discount):
    def apply(self, total):
        return total - 10

class SeasonalDiscount(Discount):          # new type — no existing code touched
    def apply(self, total):
        return total * 0.75

class DiscountCalculator:
    def calculate(self, order, discount: Discount):
        return discount.apply(order.total)
```
