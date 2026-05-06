public class Customer implements Runnable {

    private String name;
    private BankAccount account;

    // Constructor
    public Customer(String name, BankAccount account) {
        this.name = name;
        this.account = account;
    }

    @Override
    public void run() {

        // Multiple withdrawals to create conflict
        for (int i = 0; i < 3; i++) {

            account.withdraw(700, name);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        account.checkBalance(name);
    }
}