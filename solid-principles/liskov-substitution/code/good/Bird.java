package solid.lsp.good;

/**
 * [OK] Base interface - every bird shares these capabilities.
 *
 * Design decision:
 *   fly() is NOT here because not all birds can fly.
 *   Only capabilities that ALL birds share belong in the base contract.
 */
public interface Bird {

    /** Every bird has a name. */
    String getName();

    /** Every bird eats. */
    void eat();

    /** Every bird makes a sound. */
    void makeSound();
}
