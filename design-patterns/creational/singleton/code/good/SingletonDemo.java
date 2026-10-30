package patterns.singleton.good;

/**
 * Runs every Singleton and checks that asking twice gives the same object.
 *
 * @author Mateen
 */
public class SingletonDemo {

    public static void main(String[] args) {
        System.out.println("Eager               : " + (EagerSingleton.getInstance() == EagerSingleton.getInstance()));
        System.out.println("Lazy                : " + (LazySingleton.getInstance() == LazySingleton.getInstance()));
        System.out.println("Double-checked lock : " + (DoubleCheckedLockingSingleton.getInstance() == DoubleCheckedLockingSingleton.getInstance()));
        System.out.println("Bill Pugh (holder)  : " + (BillPughSingleton.getInstance() == BillPughSingleton.getInstance()));
        System.out.println("Enum                : " + (EnumSingleton.INSTANCE == EnumSingleton.INSTANCE));

        System.out.println("App name: " + EnumSingleton.INSTANCE.getAppName());
    }
}
