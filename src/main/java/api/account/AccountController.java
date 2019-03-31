package api.account;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("account")
public class AccountController {

    @Inject
    @Singleton
    private AccountService accountService;

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountRequest accountRequest) {
        Account account = accountService.save(accountRequest.getAccount());
        return Response.ok().entity(account).build();
    }

    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts(@PathParam("accountId") Long accountId){
        Account account = accountService.getAccountById(accountId);
        return Response.ok().entity(account).build();
    }

}
