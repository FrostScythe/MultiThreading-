public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("===== PHASE 5: DEADLOCK DEMO =====\n");

        // Two accounts for transfer (uses ReentrantLock)
        BankAccount account1 = new BankAccount("Ayush", 1000, 1);
        BankAccount account2 = new BankAccount("Rahul", 1000, 2);

        Thread t1 = new Thread(() ->
                account1.transfer(account2, 100, "Thread-1"));

        Thread t2 = new Thread(() ->
                account2.transfer(account1, 200, "Thread-2"));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("\n===== PHASE 6: THREAD COMMUNICATION DEMO =====\n");

        // One account for wait/notify (uses synchronized)
        // ⚠️ LEARNING NOTE: Never call transfer() on this account
        // synchronized and ReentrantLock are separate locks — mixing causes data corruption
        BankAccount account3 = new BankAccount("Alice", 100, 3);

        Thread withdrawThread = new Thread(() -> {
            System.out.println("Trying to withdraw .....");
            account3.withdraw("Alice", 500);
        });

        Thread depositThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account3.deposit("Bob", 500);
        });

        withdrawThread.start();
        depositThread.start();
        withdrawThread.join();
        depositThread.join();

        System.out.println("\n===== TRANSACTION COMPLETE =====");
    }
}