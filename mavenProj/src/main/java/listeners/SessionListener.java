package listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private ServletContext context;
    public void sessionCreated(HttpSessionEvent sessionEvent) {

        context = sessionEvent.getSession().getServletContext();
        context.log("\n\n\n\n\nSession Created:: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        context.log("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
    }
}
