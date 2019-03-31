package api;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class RestConfig extends ResourceConfig
{
    public RestConfig()
    {
        packages(this.getClass().getPackage().getName());
        register(JacksonFeature.class);
    }
}