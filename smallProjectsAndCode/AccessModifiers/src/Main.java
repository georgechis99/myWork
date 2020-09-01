public class Main {

    public static void main(String[] args) {
        Account georgesAccount = new Account("George");
        georgesAccount.deposit(1000);
        georgesAccount.withdraw(500);
        georgesAccount.withdraw(-200);
        georgesAccount.deposit(-20);
        georgesAccount.calculateBalance();
//        georgesAccount.balance = 5000;
//        georgesAccount.transactions.add(4500);
        georgesAccount.calculateBalance();

        System.out.println("Balance on account is " + georgesAccount.getBalance());
    }
}
