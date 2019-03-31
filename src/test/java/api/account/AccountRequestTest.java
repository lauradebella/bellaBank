package api.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountRequestTest {

    @Test
    public void shouldReturnAccountEntityForRequest() {
        AccountRequest request = new AccountRequest("Passport", "Beyonce");

        Account account = request.getAccount();

        assertEquals(account.getName(), request.getName());
        assertEquals(account.getPassportNumber(), request.getPassportNumber());

    }

}