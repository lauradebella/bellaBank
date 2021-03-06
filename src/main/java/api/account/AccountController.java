package api.account;

import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("account")
public class AccountController {

    @Inject
    @Singleton
    private AccountService accountService;

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountRequest accountRequest) {
        Account account = accountService.createAccount(accountRequest.getAccount());
        return Response.created(URI.create("/account/" + account.getId())).entity(account).build();
    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@PathParam("accountId") Long accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            return Response.ok().entity(account).build();
        } catch (NoResultException exception) {
            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }
    }

}
