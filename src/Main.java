import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BankAccount account = new BankAccount();

        // TODO 1: Create a fixed thread pool with 3 threads
        ExecutorService pool = newFixedThreadPool(8);

        // TODO 2: Submit 8 deposit tasks of ₹100 each (customers: "C1" through "C8")
        // Hint: pool.submit() or pool.execute() — both work for Runnable
        for (int i = 1; i <= 8; i++) {
            pool.execute( new DepositTask(account, 100, "C" + i) );
        }

        // TODO 3: Shut down the pool — no new tasks accepted
        pool.shutdown();

        // TODO 4: Wait for all tasks to finish (blocks until done OR 10 seconds pass)
        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Final Balance: " + account.getBalance());
    }
}