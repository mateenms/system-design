package patterns.singleton.good;

/**
 * 3. DOUBLE-CHECKED LOCKING Singleton.
 *
 * Lazy like LazySingleton, but it only locks while the object is not
 * created yet, so later calls are fast.
 *
 *   - First check  : skip the lock once the object exists.
 *   - Lock         : only one thread builds the object.
 *   - Second check : a thread that waited must not build it again.
 *
 * "volatile" is required. Without it another thread may see a
 * half-built object.
 *
 * @author Mateen
 */
public final class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() { }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {                                  // first check
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {                          // second check
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public String getAppName() {
        return "system-design";
    }
}
