package patterns.singleton.good;

/**
 * 3. DOUBLE-CHECKED LOCKING Singleton.
 *
 * Once the object exists, there is no locking at all.
 *
 * @author Mateen
 */
public final class DoubleCheckedLockingSingleton {

    // volatile: makes sure other threads never see a half-built object
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() { }

    public static DoubleCheckedLockingSingleton getInstance() {

        // check 1: already created? return it, no lock needed
        if (instance == null) {

            // only one thread at a time can create it
            synchronized (DoubleCheckedLockingSingleton.class) {

                // check 2: another thread may have created it while we waited
                if (instance == null) {
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
