package api.account;

import api.RestConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class AccountControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new RestConfig();
    }

    @Test
    public void hello() {
        Response response = target("/account").request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\n" +
                        "\t\"passportNumber\": \"qert\",\n" +
                        "\t\"name\": \"laura122\"\n" +
                        "}"));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

}