import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Bank system starting...");

        // TODO 1: Create a CountDownLatch for 3 components
        CountDownLatch latch = new CountDownLatch(3);

        // TODO 2: Create a thread pool with 3 threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // TODO 3: Submit 3 loaders — Customer Data (2000ms),
        //         Transaction History (3000ms), Account Balances (1500ms)
        pool.execute(new SystemLoader("Customer Data", 100, latch));
        pool.execute(new SystemLoader("Transaction History", 100, latch));
        pool.execute(new SystemLoader("Account Balances", 100, latch));

        System.out.println("Main thread waiting for system to load...");

        // TODO 4: Main thread waits here until all 3 countDown() calls happen
        latch.await();

        // This line should only print after ALL 3 components are loaded
        System.out.println("\n✅ All systems loaded! Bank is now open for transactions.");

        pool.shutdown();
    }
}