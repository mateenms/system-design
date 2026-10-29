package patterns.singleton.good;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * GOOD EXAMPLE: Singleton using the initialization-on-demand holder idiom.
 *
 * Why this is the best-practice choice for a classic Singleton:
 *   1. Lazy:         the instance is created on the first getInstance() call.
 *   2. Thread-safe:  the JVM initialises a class exactly once, safely, so no
 *                    synchronized block or volatile field is needed.
 *   3. Simple:       no locking code to get wrong.
 *   4. Final class + private constructor: it cannot be subclassed or
 *                    instantiated from outside.
 *   5. Reflection guard: the constructor refuses a second creation.
 *   6. Immutable state: properties never change after construction, so the
 *                    shared instance is safe to read from any thread.
 *
 * Real-world uses: application configuration, logger, connection-pool manager.
 *
 * @author Mateen
 */
public final class AppConfig {

    // Immutable, so sharing this instance across threads needs no locking
    private final Map<String, String> properties;

    private AppConfig() {
        // Holder.INSTANCE is null only during the one legitimate creation,
        // so a later reflective call to this constructor fails here.
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("Already created. Use getInstance().");
        }
        // In real code: load from a file, environment variables, etc.
        this.properties = Map.of(
                "app.name", "system-design",
                "app.env", "dev");
    }

    /** Global access point. */
    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    public String get(String key, String defaultValue) {
        return properties.getOrDefault(key, defaultValue);
    }

    // Not loaded until getInstance() first touches it
    private static final class Holder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Same object every time
        AppConfig a = AppConfig.getInstance();
        AppConfig b = AppConfig.getInstance();
        System.out.println("Same instance? " + (a == b));
        System.out.println("app.name = " + a.get("app.name", "unknown"));

        // 2. Same object under heavy concurrency
        int threads = 50;
        CountDownLatch startSignal = new CountDownLatch(1);
        Set<Integer> distinctInstances = ConcurrentHashMap.newKeySet();
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            pool.submit(() -> {
                try {
                    startSignal.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                distinctInstances.add(System.identityHashCode(AppConfig.getInstance()));
            });
        }
        startSignal.countDown();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Distinct instances across " + threads + " threads: "
                + distinctInstances.size());
    }
}
