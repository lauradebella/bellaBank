package api.account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
public class AccountService {

    private EntityManager entityManager;

    @Inject
    public AccountService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Account save(Account account) {

        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return account;
    }

}
