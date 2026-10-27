# Single Responsibility Principle (SRP)

> A class should have only **one reason to change**.

Each class or module should do exactly one thing and own that concern completely.

## Bad Example

```python
# bad.py — Order handles business logic AND persistence AND email
class Order:
    def __init__(self, items):
        self.items = items

    def calculate_total(self):
        return sum(item["price"] for item in self.items)

    def save_to_db(self, db):
        db.execute("INSERT INTO orders ...", self.items)

    def send_confirmation_email(self, email_client, address):
        email_client.send(address, f"Your total is {self.calculate_total()}")
```

Three unrelated reasons to change: pricing logic, database schema, email format.

## Good Example

```python
# good.py — each class owns one concern

class Order:
    def __init__(self, items):
        self.items = items

    def calculate_total(self):
        return sum(item["price"] for item in self.items)


class OrderRepository:
    def __init__(self, db):
        self.db = db

    def save(self, order):
        self.db.execute("INSERT INTO orders ...", order.items)


class OrderNotifier:
    def __init__(self, email_client):
        self.email_client = email_client

    def confirm(self, order, address):
        self.email_client.send(address, f"Your total is {order.calculate_total()}")
```

Now each class changes for exactly one reason.
