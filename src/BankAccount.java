public class BankAccount {

    private int balance = 1000;

    public void deposit(int amount, String customerName) {

        System.out.println(customerName +
                " is depositing " + amount);

        balance = balance + amount;

        System.out.println(customerName +
                " deposited successfully. Balance: " + balance);
    }

    public void withdraw(int amount, String customerName) {

        System.out.println(customerName +
                " is trying to withdraw " + amount);

        if (balance >= amount) {

            // Artificial delay to increase race condition chance
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
    }

    public void checkBalance(String customerName) {

        System.out.println(customerName +
                " checked balance: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}