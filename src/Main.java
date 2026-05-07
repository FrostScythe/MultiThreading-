public class Main {

    public static void main(String[] args) {

        // Two separate accounts
        BankAccount account1 =
                new BankAccount("Ayush", 1000,1);

        BankAccount account2 =
                new BankAccount("Rahul", 1000,2);

        // Thread-1
        Thread t1 = new Thread(() -> {

            account1.transfer(
                    account2,
                    100,
                    "Thread-1");

        });

        // Thread-2
        Thread t2 = new Thread(() -> {

            account2.transfer(
                    account1,
                    200,
                    "Thread-2");

        });

        t1.start();
        t2.start();

        try {

            t1.join();
            t2.join();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("Program Finished");
    }
}