import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}

class Acc {
    private BankAccount bankAccount;

    public Acc(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void withdraw(double amount) {
        if (bankAccount.withdraw(amount)) {
            System.out.println("\nWithdrawal successful.");
            System.err.println("Current balance: " + bankAccount.checkBalance());
        } else {
            System.err.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (bankAccount.deposit(amount)) {
            System.out.println("Deposit successful. Current balance: " + bankAccount.checkBalance());
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: " + bankAccount.checkBalance());
    }
}

public class Atm {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000);
        Acc atmMachine = new Acc(userAccount);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("	ATM\n");
            System.out.println("1. Balance enquire");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawal");
            System.out.println("4. Exit");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    atmMachine.checkBalance();
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    atmMachine.deposit(depositAmount);
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    atmMachine.withdraw(withdrawAmount);
                    break;
                case "4":
                    System.out.println("\nThank you for using the ATM. \n	Goodbye!:)");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.Try again :( .");
            }
        }
    }
}



