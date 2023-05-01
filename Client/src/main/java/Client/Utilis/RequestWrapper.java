package Client.Utilis;

public class RequestWrapper {
    private int fromAccountNumber;
    private int toAccountNumber;
    private double amount;

    public RequestWrapper(int fromAccountNumber, int toAccountNumber, double amount) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public int getFromAccountNumber() {
        return fromAccountNumber;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
