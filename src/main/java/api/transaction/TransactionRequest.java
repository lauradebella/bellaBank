package api.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransactionRequest {
    private long originAccountId;

    private long destinationAccountId;

    private BigDecimal value;

    public TransactionRequest(@JsonProperty("originAccountId") long originAccountId,
                              @JsonProperty("destinationAccountId") long destinationAccountId,
                              @JsonProperty("value") double value) {
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
        this.value = BigDecimal.valueOf(value);
    }

    public long getOriginAccountId() {
        return originAccountId;
    }

    public long getDestinationAccountId() {
        return destinationAccountId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Transaction getDebitTransaction(){
        return new Transaction(TransactionType.DEBIT,
                this.originAccountId,
                this.value);
    }

    public Transaction getCreditTransaction(){
        return new Transaction(TransactionType.CREDIT,
                this.destinationAccountId,
                this.value);
    }

}
