# Dependency Inversion Principle (DIP)

> High-level modules should **not depend on low-level modules**. Both should depend on **abstractions**.

Depend on interfaces/abstractions, not concrete implementations. This makes high-level logic swappable and testable.

## Bad Example

```python
# bad.py — OrderService is tightly coupled to MySQLDatabase
class MySQLDatabase:
    def save(self, data):
        print(f"Saving to MySQL: {data}")

class OrderService:
    def __init__(self):
        self.db = MySQLDatabase()   # hard-coded concrete dependency

    def place_order(self, order):
        self.db.save(order)
```

Switching to PostgreSQL or a test double requires modifying `OrderService`.

## Good Example

```python
# good.py — OrderService depends on an abstraction; any storage works
from abc import ABC, abstractmethod

class Database(ABC):
    @abstractmethod
    def save(self, data): ...

class MySQLDatabase(Database):
    def save(self, data):
        print(f"Saving to MySQL: {data}")

class InMemoryDatabase(Database):       # swap in for tests, no code change
    def __init__(self):
        self.store = []

    def save(self, data):
        self.store.append(data)

class OrderService:
    def __init__(self, db: Database):   # dependency injected from outside
        self.db = db

    def place_order(self, order):
        self.db.save(order)

# Wiring (composition root)
service = OrderService(MySQLDatabase())
test_service = OrderService(InMemoryDatabase())
```
