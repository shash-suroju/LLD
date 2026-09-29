package model;

public class Balance {
    private final User debtor;
    private final User creditor;
    private final double amount;

    public Balance(User debtor, User creditor, long amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }
}