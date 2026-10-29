package patterns.singleton.good;

/**
 * 4. BILL PUGH (holder) Singleton.
 *
 * Lazy and thread-safe with no locks and no volatile.
 * The inner Holder class is loaded only when getInstance() is first called,
 * and Java loads a class exactly once, safely.
 *
 * Use when: you want lazy creation with the simplest safe code.
 *
 * @author Mateen
 */
public final class BillPughSingleton {

    private BillPughSingleton() { }

    private static final class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public String getAppName() {
        return "system-design";
    }
}
