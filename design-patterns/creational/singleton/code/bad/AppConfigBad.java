package patterns.singleton.bad;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * BAD EXAMPLE: lazy Singleton that is not thread-safe.
 *
 * Problem:
 *   Two threads can both see instance == null at the same moment, and each
 *   creates its own object. The "single" instance is no longer single, so
 *   state (here: configuration) can silently diverge.
 *
 * The constructor sleeps briefly to widen the race window so the bug shows
 * up reliably in the demo. In real code the bug is rarer, which makes it
 * much harder to find.
 *
 * @author Mateen
 */
public final class AppConfigBad {

    private static AppConfigBad instance;

    private AppConfigBad() {
        try {
            Thread.sleep(10); // simulates expensive initialisation (file / network read)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Race condition: "check then create" is not atomic
    public static AppConfigBad getInstance() {
        if (instance == null) {
            instance = new AppConfigBad();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 20;
        CountDownLatch startSignal = new CountDownLatch(1);
        Set<Integer> distinctInstances = ConcurrentHashMap.newKeySet();
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            pool.submit(() -> {
                try {
                    startSignal.await(); // release all threads at the same moment
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                distinctInstances.add(System.identityHashCode(getInstance()));
            });
        }

        startSignal.countDown();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Distinct instances created: " + distinctInstances.size()
                + " (expected 1)");
    }
}
