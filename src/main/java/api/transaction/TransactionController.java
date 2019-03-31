package api.transaction;

import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("transaction")
public class TransactionController {

    @Inject
    @Singleton
    private TransactionService transactionService;

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateTransaction(TransactionRequest transactionRequest) {

        Transaction debitTransaction = transactionRequest.getDebitTransaction();
        Transaction creditTransaction = transactionRequest.getCreditTransaction();

        TransactionStatus transactionStatus = transactionService.execute(debitTransaction, creditTransaction);

        TransactionResponse response =  new TransactionResponse(debitTransaction.getAccountId(), creditTransaction.getAccountId(), debitTransaction.getValue(), transactionStatus);

        if(transactionStatus == TransactionStatus.NOT_ENOUGH_FUNDS) {
            return Response.status(HttpStatus.BAD_REQUEST_400).entity(response).build();
        }
        return Response.ok().entity(response).build();
    }

}
