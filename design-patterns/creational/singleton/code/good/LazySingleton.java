package patterns.singleton.good;

/**
 * 2. LAZY Singleton (synchronized).
 *
 * The object is created the first time getInstance() is called.
 * "synchronized" lets only one thread in at a time, so two objects
 * can never be created.
 *
 * Use when: creation is slow and the object may never be needed.
 * Downside: every call takes the lock, which is slightly slower.
 *
 * @author Mateen
 */
public final class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() { }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public String getAppName() {
        return "system-design";
    }
}
