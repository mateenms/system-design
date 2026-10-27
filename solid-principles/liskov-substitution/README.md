# Liskov Substitution Principle (LSP)

> Objects of a subclass should be **substitutable** for objects of the parent class without altering correctness.

If `S` is a subtype of `T`, using an `S` anywhere a `T` is expected must still work correctly.

## Bad Example

```python
# bad.py — Square breaks Rectangle's contract
class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

class Square(Rectangle):
    def __init__(self, side):
        super().__init__(side, side)

    # Overriding width setter breaks the Rectangle contract:
    # setting width independently no longer makes sense for a square.
    @Rectangle.width.setter
    def width(self, value):
        self._width = value
        self._height = value  # silently changes height — caller doesn't expect this
```

Code written for `Rectangle` breaks when handed a `Square`.

## Good Example

```python
# good.py — share only a true common interface
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self) -> float: ...

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

class Square(Shape):
    def __init__(self, side):
        self.side = side

    def area(self):
        return self.side ** 2

def print_area(shape: Shape):
    print(shape.area())   # works correctly for any Shape subtype
```
