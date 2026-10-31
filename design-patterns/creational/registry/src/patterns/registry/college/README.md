# Registry Example 4: Colleges in Maharashtra

One ready-made college per **type**. A new college is a copy with a name, city and university.

> The courses, facilities and universities in the demo are sample data, not official information.

## Steps

1. `College` implements `Prototype<College>` with a private copy constructor and `copy()`.
2. Build one college per type with its courses and facilities (Engineering, Pharmacy).
3. Create `new PrototypeRegistry<College>()`.
4. Register them: `registry.register("engineering", engineering)`.
5. Create by type: `registry.create("engineering").name("MGM COE").city("Nanded")`.
6. Add anything extra to the copy only (Bidve COE gets `Electrical Engineering`).
7. The registered college stays the same.

## Code

| File | What it shows |
|------|---------------|
| [College.java](College.java) | College with a copy constructor and `copy()` |
| [CollegeDemo.java](CollegeDemo.java) | Creates MGM COE, Matoshiri COE (Nanded) and Bidve COE (Latur) from one template |

Run: see the [registry README](../../../../README.md#run).
