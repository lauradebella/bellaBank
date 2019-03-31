package api.transaction;

import org.eclipse.jetty.util.annotation.ManagedObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransactionServiceTest {

    @Mock
    EntityManager entityManager;

    @Mock
    EntityTransaction transaction;

    @Mock
    Query mockedQuery;

    TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        transactionService = new TransactionService(entityManager);

        when(entityManager.getTransaction()).thenReturn(transaction);

        when(entityManager.createNamedQuery("Transaction.findByAccount")).thenReturn(mockedQuery);
    }

    @Test
    public void shouldPersistTransactionWhenBalanceIsMoreThanEnough() {

        BigDecimal balance = new BigDecimal(1000);
        when(mockedQuery.getSingleResult()).thenReturn(balance);

        int originAccountId = 1;
        int destinationAccountId = 2;
        BigDecimal value = BigDecimal.TEN;

        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, originAccountId, value);
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, destinationAccountId, value);

        TransactionStatus status = transactionService.execute(debitTransaction, creditTransaction);

        verify(entityManager, times(2)).persist(any(Transaction.class));
        assertEquals(TransactionStatus.PROCESSED, status);
    }

    @Test
    public void shouldPersistTransactionWhenBalanceIsExactlyEnough() {
        BigDecimal balance = new BigDecimal(10);
        when(mockedQuery.getSingleResult()).thenReturn(balance);

        int originAccountId = 1;
        int destinationAccountId = 2;
        BigDecimal value = BigDecimal.TEN;

        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, originAccountId, value);
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, destinationAccountId, value);

        TransactionStatus status = transactionService.execute(debitTransaction, creditTransaction);

        verify(entityManager, times(2)).persist(any(Transaction.class));
        assertEquals(TransactionStatus.PROCESSED, status);
    }

    @Test
    public void shouldNotPersistTransactionWhenBalanceIsNotEnough() {
        BigDecimal balance = new BigDecimal(-10);
        when(mockedQuery.getSingleResult()).thenReturn(balance);

        int originAccountId = 1;
        int destinationAccountId = 2;
        BigDecimal value = BigDecimal.TEN;

        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, originAccountId, value);
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, destinationAccountId, value);

        TransactionStatus status = transactionService.execute(debitTransaction, creditTransaction);

        verify(entityManager, times(1)).persist(any(Transaction.class));
        verify(entityManager.getTransaction(), times(1)).rollback();
        assertEquals(TransactionStatus.NOT_ENOUGH_FUNDS, status);
    }
}