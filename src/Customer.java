public class Customer implements Runnable {

    private String name;

    private BankAccount fromAccount;
    private BankAccount toAccount;

    public Customer(String name,
                    BankAccount fromAccount,
                    BankAccount toAccount) {

        this.name = name;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    @Override
    public void run() {

        fromAccount.transfer(
                toAccount,
                100,
                name);
    }

}