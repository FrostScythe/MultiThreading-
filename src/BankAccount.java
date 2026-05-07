import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private String accountHolder;
    private int balance;

    // Each account has its own lock
    private ReentrantLock lock = new ReentrantLock();

    public BankAccount(String accountHolder, int balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // DEADLOCK METHOD
    public void transfer(BankAccount targetAccount,
                         int amount,
                         String threadName) {

        System.out.println(threadName + " trying to lock " + this.accountHolder);

        // Lock current account
        lock.lock();

        try {

            System.out.println(threadName +" locked " + this.accountHolder);

            // Artificial delay
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName +" trying to lock " +targetAccount.accountHolder);

            // Try locking target account
            targetAccount.lock.lock();

            try {

                System.out.println(threadName +
                        " locked " +
                        targetAccount.accountHolder);

                if (balance >= amount) {

                    balance -= amount;
                    targetAccount.balance += amount;

                    System.out.println(threadName +" transferred " + amount +" from " + this.accountHolder +" to " + targetAccount.accountHolder);
                }

            } finally {

                targetAccount.lock.unlock();
            }

        } finally {

            lock.unlock();
        }
    }

    public void checkBalance() {

        System.out.println(accountHolder +" Balance: " + balance);
    }
}