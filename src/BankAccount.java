import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

    private int balance = 1000;

    // true = fair lock (FIFO order)
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void deposit(int amount, String customerName) {

        System.out.println(customerName +
                " is depositing " + amount);

        writeLock.lock();

        try {
            balance = balance + amount;

            System.out.println(customerName + " deposited successfully. Balance: " + balance);
        } finally {

            writeLock.unlock();
        }
    }

    public void withdraw(int amount, String customerName) {

        System.out.println(customerName +
                " is trying to withdraw " + amount);

        writeLock.lock();

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

            writeLock.unlock();
        }
    }

    public void checkBalance(String customerName) {
        readLock.lock();
        try{
            // Artificial delay
            try {
                Thread.sleep(100);
                System.out.println(customerName + " checked balance: " + balance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            readLock.unlock();
        }
    }

    public int getBalance() {
        return balance;
    }
}