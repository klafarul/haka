package servlets;

import models.address.Address;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.HibService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContextMVC.xml");
        HibService hibService = context.getBean("hibService", HibService.class);
        //HibService hibService = HibService.getHibService();
        ArrayList<Address> addresses = hibService.findAllAddresses();

        req.setAttribute("addresses", addresses);
        context.close();
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/Result.jsp").forward(req, resp);

    }
}