# Interface Segregation Principle (ISP)

> No client should be forced to depend on **methods it does not use**.

Split fat interfaces into smaller, focused ones so implementors only carry what they need.

## Bad Example

```python
# bad.py — every device is forced to implement fax()
from abc import ABC, abstractmethod

class MultiFunctionDevice(ABC):
    @abstractmethod
    def print(self, doc): ...

    @abstractmethod
    def scan(self, doc): ...

    @abstractmethod
    def fax(self, doc): ...       # most printers can't fax

class BasicPrinter(MultiFunctionDevice):
    def print(self, doc): ...
    def scan(self, doc): ...
    def fax(self, doc):
        raise NotImplementedError  # forced stub — ISP violation
```

## Good Example

```python
# good.py — segregated interfaces; classes implement only what they support
from abc import ABC, abstractmethod

class Printable(ABC):
    @abstractmethod
    def print(self, doc): ...

class Scannable(ABC):
    @abstractmethod
    def scan(self, doc): ...

class Faxable(ABC):
    @abstractmethod
    def fax(self, doc): ...

class BasicPrinter(Printable):
    def print(self, doc): ...     # only what it actually supports

class OfficePrinter(Printable, Scannable, Faxable):
    def print(self, doc): ...
    def scan(self, doc): ...
    def fax(self, doc): ...
```
