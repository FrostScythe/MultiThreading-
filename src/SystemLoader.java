import java.util.concurrent.CountDownLatch;

public class SystemLoader implements Runnable {
    private final String componentName;
    private final int loadTimeMs;        // simulates how long loading takes
    private final CountDownLatch latch;

    public SystemLoader(String componentName, int loadTimeMs, CountDownLatch latch) {
        this.componentName = componentName;
        this.loadTimeMs = loadTimeMs;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Loading " + componentName + "...");
        try {
            Thread.sleep(loadTimeMs);    // simulate loading time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(componentName + " loaded! ✓");
        latch.countDown();               // signal: I'm done
    }
}