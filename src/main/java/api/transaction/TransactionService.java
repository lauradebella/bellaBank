package api.transaction;

import api.account.AccountService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;

public class TransactionService {

    public static final int CONSTANT_TO_LESS_THAN = -1;

    private EntityManager entityManager;
    private AccountService accountService;


    @Inject
    public TransactionService(EntityManager entityManager, AccountService accountService) {
        this.accountService = accountService;
        this.entityManager = entityManager;
    }

    public TransactionStatus execute(Transaction debitTransaction, Transaction creditTransaction) {

        validateIfAccountExists(debitTransaction.getAccountId());
        validateIfAccountExists(creditTransaction.getAccountId());

        entityManager.getTransaction().begin();

        entityManager.persist(debitTransaction);

        final Query balanceQuery = entityManager.createNamedQuery("Transaction.findByAccount");
        balanceQuery.setParameter("ACCOUNT", debitTransaction.getAccountId());
        final Object balance = balanceQuery.getSingleResult() == null ? BigDecimal.valueOf(0) : balanceQuery.getSingleResult();

        BigDecimal balanceOnOriginAccount = (BigDecimal) balance;
        if (balanceOnOriginAccount.compareTo(BigDecimal.ZERO) == CONSTANT_TO_LESS_THAN) {
            entityManager.getTransaction().rollback();
            return TransactionStatus.NOT_ENOUGH_FUNDS;
        }

        entityManager.persist(creditTransaction);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return TransactionStatus.PROCESSED;
    }

    private void validateIfAccountExists(Long accountId) {
        accountService.getAccountById(accountId);
    }

}
