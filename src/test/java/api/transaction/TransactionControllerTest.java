package api.transaction;

import api.RestConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class TransactionControllerTest  extends JerseyTest {

    @Override
    protected Application configure() {
        return new RestConfig();
    }

    @Test
    public void returnOkWhenTransactionSucceeds() {
        Response response = target("/transaction").request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\n" +
                        "\t\"originAccountId\": 1,\n" +
                        "\t\"destinationAccountId\": 2,\n" +
                        "\t\"value\": 40\n" +
                        "}"));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void returnBadRequestWhenTransactionFails() {
        Response response = target("/transaction").request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\n" +
                        "\t\"originAccountId\": 1,\n" +
                        "\t\"destinationAccountId\": 2,\n" +
                        "\t\"value\": 40000\n" +
                        "}"));

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void returnNotFoundWhenAccountDoesNotExist() {
        Response response = target("/transaction").request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\n" +
                        "\t\"originAccountId\": 102,\n" +
                        "\t\"destinationAccountId\": 2,\n" +
                        "\t\"value\": 40\n" +
                        "}"));

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}