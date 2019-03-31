package api.transaction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionRequesTest {

    private TransactionRequest transactionRequest;

    @Before
    public void setUp() throws Exception {

        long originAccountId = 1;
        long destinationAccountId = 2;
        double value = 10;
        transactionRequest = new TransactionRequest(originAccountId, destinationAccountId, value);
    }

    @Test
    public void shouldReturnDebitTransaction(){

        Transaction debitTransaction = transactionRequest.getDebitTransaction();

        assertEquals(TransactionType.DEBIT, debitTransaction.getTransactionType());
        assertEquals(transactionRequest.getOriginAccountId(), debitTransaction.getAccountId());
        assertEquals(transactionRequest.getValue(), debitTransaction.getValue());

    }

    @Test
    public void shouldReturnCreditTransaction(){

        Transaction creditTransaction = transactionRequest.getCreditTransaction();

        assertEquals(TransactionType.CREDIT, creditTransaction.getTransactionType());
        assertEquals(transactionRequest.getDestinationAccountId(), creditTransaction.getAccountId());
        assertEquals(transactionRequest.getValue(), creditTransaction.getValue());

    }
}