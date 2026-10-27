package solid.lsp.good;

/**
 * [OK] Extended interface - only birds that CAN fly implement this.
 *
 * Key LSP principle:
 *   A method accepting FlyingBird can ALWAYS call fly() safely.
 *   No UnsupportedOperationException possible - the type system enforces it.
 *
 * @author Mateen
 */
public interface FlyingBird extends Bird {

    /**
     * Makes the bird fly.
     * ONLY birds that genuinely support flying implement this interface.
     */
    void fly();
}
