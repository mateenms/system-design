package patterns.singleton.bad;

/**
 * BAD: no Singleton.
 *
 * The constructor is public, so anyone can create as many objects as they want.
 * Two parts of the app end up with two different configs.
 *
 * @author Mateen
 */
public class AppConfigBad {

    public String appName = "system-design";

    public static void main(String[] args) {
        AppConfigBad first = new AppConfigBad();
        AppConfigBad second = new AppConfigBad();

        System.out.println("Same object? " + (first == second)); // false
    }
}
