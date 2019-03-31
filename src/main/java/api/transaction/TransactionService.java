package api.transaction;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;

public class TransactionService {

    public static final int CONSTANT_TO_LESS_THAN = -1;

    private EntityManager entityManager;

    @Inject
    public TransactionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TransactionStatus execute(Transaction debitTransaction, Transaction creditTransaction) {

        entityManager.getTransaction().begin();

        entityManager.persist(debitTransaction);

        final Query sumQuery = entityManager.createNamedQuery("Transaction.findByAccount");
        sumQuery.setParameter("ACCOUNT",debitTransaction.getAccountId());
        final Object balance = sumQuery.getSingleResult() == null ? BigDecimal.valueOf(0) : sumQuery.getSingleResult();

        BigDecimal balanceOnOriginAccount = (BigDecimal) balance;
        if(balanceOnOriginAccount.compareTo(BigDecimal.ZERO) == CONSTANT_TO_LESS_THAN) {
            entityManager.getTransaction().rollback();
            return TransactionStatus.NOT_ENOUGH_FUNDS;
        }

        entityManager.persist(creditTransaction);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return TransactionStatus.PROCESSED;
    }

}
