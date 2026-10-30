package patterns.singleton.good;

/**
 * 3. DOUBLE-CHECKED LOCKING Singleton.
 *
 * Goal: once the object exists, do NOT lock again.
 *
 *   Step 1: if (instance == null)  -> object exists? return it, no lock.
 *   Step 2: synchronized           -> only when not created yet.
 *   Step 3: if (instance == null)  -> check again inside the lock, because
 *                                     another thread may have created it
 *                                     while this one was waiting.
 *
 * "volatile" is required. Without it, another thread may see a
 * half-built object.
 *
 * Use when: you want lazy creation and no lock after startup.
 *
 * @author Mateen
 */
public final class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() { }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {                                  // no lock if already created
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {                          // check again inside the lock
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
