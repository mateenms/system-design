package patterns.singleton.good;

/**
 * GOOD: Singleton that is created only when first needed (lazy).
 *
 * Same three rules as AppConfig, but the object is built inside getInstance().
 * "synchronized" makes sure two threads cannot build two objects.
 *
 * Use this when creating the object is slow and might never be needed.
 *
 * @author Mateen
 */
public class LazyAppConfig {

    private static LazyAppConfig instance; // empty until first use

    private LazyAppConfig() { }

    // synchronized = only one thread at a time can enter
    public static synchronized LazyAppConfig getInstance() {
        if (instance == null) {
            instance = new LazyAppConfig();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyAppConfig first = LazyAppConfig.getInstance();
        LazyAppConfig second = LazyAppConfig.getInstance();

        System.out.println("Same object? " + (first == second)); // true
    }
}
