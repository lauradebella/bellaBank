import api.RestConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;


public class Application {
    public static void main(String[] args) throws Exception {
        ResourceConfig config = new RestConfig();
        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.addServlet(jerseyServlet, "/*");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}