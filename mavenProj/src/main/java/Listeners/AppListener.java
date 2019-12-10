package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

@WebListener
public class AppListener implements ServletContextListener {
    private ServletContext context;

    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
        context.log("\n\n\n****************APP START*********************");

    }

    public void contextDestroyed(ServletContextEvent sce) {
        context.log("****************APP END*********************");

    }
}
