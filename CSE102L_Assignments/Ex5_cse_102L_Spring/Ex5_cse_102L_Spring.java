import java.util.ArrayList;

public class Ex5_cse_102L_Spring {
    public static void main(String[] args) throws InvalidTransactionException{
        Account acc  = new Account("2023000", 10000);
        Account acc1 = new Account("2023001", 8000);
        Account acc2 = new Account("2023002", 5000);
        Account acc3 = new Account("2023003", 3000);

        //Account acc4 = new Account("2023000", -1000); // should throw InsufficientFundsException

        Customer customer = new Customer("isim");

        customer.addAccount(acc);
        customer.addAccount(acc1);
        customer.addAccount(acc2);
        customer.addAccount(acc3);

        acc.deposit(10);
        //   acc1.deposit(-100); // should throw InvalidTransactionException

        acc2.withdraw(10);
        //   acc2.withdraw(-100); // should throw InvalidTransactionException
        //   acc3.withdraw(10000);  // should throw InsufficientFundsException

        System.out.println(customer.getAccount("2023000"));

        //   customer.getAccount("15"); // should throw AccountNotFoundException

        //  customer.addAccount(acc2); // should throw AccountAlreadyExistsException

        customer.removeAccount("2023001"); // will remove acc1

        // customer.removeAccount("ABC");  // should throw AccountNotFoundException

        customer.transfer(acc2, acc3, 100.0);
        System.out.println("acc 2 new situation "+acc2);
        System.out.println("acc 3 new situation "+acc3);
        // customer.transfer(acc2, acc3, -100); // should throw InvalidTransactionException
    }
}
class Account{
    private String accountNumber;
    private double balance;

    Account(String accountNumber, double balance) {
        if (balance < 0) {
            throw new InsufficientFundsException(balance);
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount)throws InvalidTransactionException{
        if(amount < 0)
            throw new InvalidTransactionException(amount);
        this.balance += amount;
    }
    public void withdraw(double amount)throws InvalidTransactionException{
        if(amount < 0)
            throw new InvalidTransactionException(amount);

        else if (balance < amount)
            throw new InsufficientFundsException(balance, amount);

        this.balance -= amount;
    }
    public String toString(){
        return "Account: " + accountNumber + " Balance: " + balance;
    }
}

class Customer {
    private String name;
    private ArrayList<Account> accounts;

    Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber))
                return account;

        }
        throw new AccountNotFoundException(accountNumber);
    }
    public void addAccount(Account account){
        try{
            getAccount(account.getAccountNumber());
            throw new AccountAlreadyExistsException(account.getAccountNumber());
        }catch (AccountNotFoundException ex){
            accounts.add(account);
        }finally {
            System.out.println("Added account: " + account.getAccountNumber()
                    + " with " + account.getBalance());
        }
    }
    public void removeAccount(String accountNumber){
        accounts.remove(getAccount(accountNumber));
    }
    public void transfer(Account fromAccount, Account toAccount, double amount) throws InvalidTransactionException{
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }catch (InvalidTransactionException  e){
            throw new InvalidTransactionException(e, "cannot transfer funds from account " + fromAccount
                    + " to account " + toAccount);
        }
    }
    public String toString(){
        return "Customer " + name + ":\n" + "each account in a new line and indented with \t";

    }
}

class InsufficientFundsException extends RuntimeException {

    InsufficientFundsException(double balance) {
        super("Wrong balance: " + balance);
    }

    InsufficientFundsException(double balance, double amount) {
        super("Required amount is " + amount + " but only " + balance + " remaining");
    }
}
class AccountAlreadyExistsException extends RuntimeException {
    AccountAlreadyExistsException(String accountNumber) {
        super("Account number " + accountNumber + " already exists");
    }
}
class AccountNotFoundException extends RuntimeException {

    AccountNotFoundException(String accountNumber) {
        super("Account number " + accountNumber + " not found");
    }
}
class InvalidTransactionException extends Exception {

    InvalidTransactionException(double amount) {
        super("Invalid amount: " + amount);
    }

    InvalidTransactionException(InvalidTransactionException e, String message) {
        super(message + ":\n\t" + e.getMessage(), e);
    }
}

