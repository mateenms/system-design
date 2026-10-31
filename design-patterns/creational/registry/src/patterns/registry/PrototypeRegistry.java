package patterns.registry;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A registry of ready-made prototypes, found by name.
 *
 * You register a prototype once. Later, create("zombie") gives you a fresh copy,
 * so nobody needs to know how it is built, and the prototype never changes.
 *
 * T can be any class that implements Prototype<T>:
 *   PrototypeRegistry<Character>, PrototypeRegistry<Student>, PrototypeRegistry<College> ...
 *
 * Steps to write a Registry:
 *   1. keep the prototypes in a Map (key -> prototype)
 *   2. register(): check for null, then store a COPY of the prototype
 *   3. create(): find the prototype by key, then return a COPY of it
 *   4. unknown key: fail with a clear message
 *
 * @param <T> the type of object this registry holds
 * @author Mateen
 */
public class PrototypeRegistry<T extends Prototype<T>> {

    // Step 1: key -> prototype (ConcurrentHashMap, so it is safe to use from several threads)
    private final Map<String, T> prototypes = new ConcurrentHashMap<>();

    // Step 2: registering the same key again replaces the old prototype
    public void register(String key, T prototype) {
        Objects.requireNonNull(key, "key is required");
        Objects.requireNonNull(prototype, "prototype is required");
        prototypes.put(key, prototype.copy());  // our own copy, so the caller cannot change it later
    }

    // Step 3: always hand out a copy, never the prototype itself
    public T create(String key) {
        Objects.requireNonNull(key, "key is required");
        T prototype = prototypes.get(key);
        if (prototype == null) {
            // Step 4
            throw new IllegalArgumentException("No prototype registered for: " + key);
        }
        return prototype.copy();
    }
}
