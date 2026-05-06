import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 1000;

    // true = fair lock (FIFO order)
    private ReentrantLock lock = new ReentrantLock(true);

    public void deposit(int amount, String customerName) {

        System.out.println(customerName +
                " is depositing " + amount);

        lock.lock();

        try {

            balance = balance + amount;

            System.out.println(customerName +
                    " deposited successfully. Balance: " + balance);

        } finally {

            lock.unlock();
        }
    }

    public void withdraw(int amount, String customerName) {

        System.out.println(customerName +
                " is trying to withdraw " + amount);

        lock.lock();

        try {

            if (balance >= amount) {

                // Artificial delay
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                balance = balance - amount;

                System.out.println(customerName +
                        " withdrew successfully. Balance: " + balance);

            } else {

                System.out.println(customerName +
                        " -> Insufficient funds. Balance: " + balance);
            }

        } finally {

            lock.unlock();
        }
    }

    public void checkBalance(String customerName) {

        System.out.println(customerName +
                " checked balance: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}