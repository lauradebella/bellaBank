package api.account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AccountService {

    private EntityManager entityManager;

    @Inject
    public AccountService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Account createAccount(Account account) {

        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return account;
    }

    public Account getAccountById(Long accountId) {
        final Query findAccountById = entityManager.createNamedQuery("Account.findById");
        findAccountById.setParameter("ACCOUNTID", accountId);
        return (Account) findAccountById.getSingleResult();
    }
}
