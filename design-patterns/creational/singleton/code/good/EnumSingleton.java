package patterns.singleton.good;

/**
 * 5. ENUM Singleton (recommended by Effective Java).
 *
 * Java itself guarantees one instance, and it is safe against
 * reflection and serialization, which the other versions are not.
 *
 * Use when: you do not need to extend another class.
 *
 * Usage: EnumSingleton.INSTANCE.getAppName()
 *
 * @author Mateen
 */
public enum EnumSingleton {

    INSTANCE;

    public String getAppName() {
        return "system-design";
    }
}
