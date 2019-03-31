package api.transaction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NamedQuery(name = "Transaction.findByAccount",
        query = "SELECT SUM(CASE WHEN t.transactionType = 0 THEN (t.value * -1) ELSE (t.value) END) " +
                "FROM Transaction t WHERE t.accountId=:ACCOUNT")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private TransactionType transactionType;

    private long accountId;

    private BigDecimal value;

    public Transaction() {

    }

    public Transaction(TransactionType transactionType, long accountId, BigDecimal value) {
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public long getAccountId() {
        return accountId;
    }

    public BigDecimal getValue() {
        return value;
    }
}