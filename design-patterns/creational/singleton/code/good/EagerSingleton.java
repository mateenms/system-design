package patterns.singleton.good;

/**
 * 1. EAGER Singleton.
 *
 * The object is created when the class loads.
 * Java guarantees class loading is thread-safe, so no locks are needed.
 *
 * Use when: the object is cheap and will surely be used.
 *
 * @author Mateen
 */
public final class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() { }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public String getAppName() {
        return "system-design";
    }
}
