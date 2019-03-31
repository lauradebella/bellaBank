package api.account;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

    @Mock
    EntityManager entityManager;

    @Mock
    EntityTransaction transaction;

    @Mock
    Query mockedQuery;

    AccountService accountService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        accountService = new AccountService(entityManager);
    }

    @Test
    public void shouldPersistAccountEntity() {
        when(entityManager.getTransaction()).thenReturn(transaction);

        Account account = new Account("Passport", "Madonna");
        Account savedAccount = accountService.save(account);

        verify(entityManager).persist(account);
        assertEquals(account.getName(), savedAccount.getName());
        assertEquals(account.getPassportNumber(), savedAccount.getPassportNumber());
    }

    @Test
    public void shouldReturnAccountIfItExists() {

        when(entityManager.createNamedQuery("Account.findById")).thenReturn(mockedQuery);
        Account expectedAccount = new Account("Passport", "Madonna");
        when(mockedQuery.getSingleResult()).thenReturn(expectedAccount);

        Long accountId = Long.valueOf(1);
        Account account  = accountService.getAccountById(accountId);

        assertEquals(account.getPassportNumber(), expectedAccount.getPassportNumber());
        assertEquals(account.getName(), expectedAccount.getName());

    }
}