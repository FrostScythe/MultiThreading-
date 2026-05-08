public class DepositTask implements Runnable {
    private final BankAccount account;
    private final double amount;
    private final String customerName;

    public DepositTask(BankAccount account, double amount, String customerName) {
        this.account = account;
        this.amount = amount;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        account.deposit((int)amount, customerName);
        System.out.println(customerName + " deposited ₹" + amount +
                " | Thread: " + Thread.currentThread().getName() +
                " | Balance: " + account.getBalance());
    }
}