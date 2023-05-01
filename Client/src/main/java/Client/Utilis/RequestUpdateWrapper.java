package Client.Utilis;

public class RequestUpdateWrapper {
    private int accountNumber;
    private double amount;

    public RequestUpdateWrapper(int accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
