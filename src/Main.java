public class Main {

    public static void main(String[] args) {

        BankAccount sharedAccount = new BankAccount();

        Customer c1 = new Customer("Ayush", sharedAccount);
        Customer c2 = new Customer("Rahul", sharedAccount);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        t1.start();
        t2.start();
    }
}