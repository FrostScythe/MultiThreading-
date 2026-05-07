import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private final int id;

    private final String accountHolder;
    private int balance;

    //Removing dead lock by using ReentrantLock
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(String accountHolder, int balance,int id) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.id = id;
    }

    // DEADLOCK METHOD
    public void transfer(BankAccount targetAccount,
                         int amount,
                         String threadName) {

        ReentrantLock firstLock  = (this.id < targetAccount.id) ? this.lock : targetAccount.lock;
        ReentrantLock secondLock = (this.id < targetAccount.id) ? targetAccount.lock : this.lock;

        System.out.println(threadName + " trying to lock " + this.accountHolder);

        // Lock current account
        firstLock.lock();

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
            secondLock.lock();

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
                secondLock.unlock();
            }

        } finally {

            firstLock.unlock();
        }
    }

    public void checkBalance() {

        System.out.println(accountHolder +" Balance: " + balance);
    }
}