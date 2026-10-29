package patterns.singleton.good;

/**
 * GOOD: the simplest Singleton (eager).
 *
 * Three rules:
 *   1. Private constructor     -> nobody outside can call "new".
 *   2. One static instance     -> created once, when the class loads.
 *   3. getInstance() method    -> the only way to get it.
 *
 * Java creates the instance safely when the class loads,
 * so this is also thread-safe.
 *
 * @author Mateen
 */
public class AppConfig {

    // 2. the one and only instance
    private static final AppConfig INSTANCE = new AppConfig();

    private final String appName = "system-design";

    // 1. private constructor
    private AppConfig() { }

    // 3. global access point
    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public String getAppName() {
        return appName;
    }

    public static void main(String[] args) {
        AppConfig first = AppConfig.getInstance();
        AppConfig second = AppConfig.getInstance();

        System.out.println("Same object? " + (first == second)); // true
        System.out.println("App name: " + first.getAppName());
    }
}
