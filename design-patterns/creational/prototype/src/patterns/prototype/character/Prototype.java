package patterns.prototype.character;

/**
 * The Prototype contract: "I can make a copy of myself".
 *
 * T is the type of the copy, so any class can use it:
 *   Prototype<Character>, Prototype<Student>, Prototype<College> ...
 *
 * @param <T> the type that copy() returns
 * @author Mateen
 */
public interface Prototype<T> {

    T copy();
}
