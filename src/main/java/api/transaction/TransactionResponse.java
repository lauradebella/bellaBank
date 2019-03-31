package api.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransactionResponse {

    private long originAccountId;

    private long destinationAccountId;

    private BigDecimal value;

    private TransactionStatus status;

    public TransactionResponse(@JsonProperty("originAccountId") long originAccountId,
                               @JsonProperty("destinationAccountId") long destinationAccountId,
                               @JsonProperty("value") BigDecimal value,
                               @JsonProperty("status") TransactionStatus status) {
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
        this.value = value;
        this.status = status;
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

    public TransactionStatus getStatus() {
        return status;
    }

}
