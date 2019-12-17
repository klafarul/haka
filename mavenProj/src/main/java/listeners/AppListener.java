package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
