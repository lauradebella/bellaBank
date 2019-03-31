package api.transaction;

public enum TransactionType {
    DEBIT(-1),
    CREDIT(1);

    public int operator;

    TransactionType(int operator) {
        this.operator = operator;
    }
}
