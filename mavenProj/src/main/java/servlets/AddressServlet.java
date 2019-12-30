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


@WebServlet("/address")
public class AddressServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContextMVC.xml");
        HibService hibService = context.getBean("hibService", HibService.class);

        //HibService hibService = HibService.getHibService();
        ArrayList<Address> list =  hibService.findAllAddresses();


        req.setAttribute("list", list);
        context.close();
        req.getRequestDispatcher("WEB-INF/jsp/Person.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Address address = new Address();

        address.setCity(req.getParameter("city"));
        address.setHouse(Integer.parseInt(req.getParameter("house")));
        address.setApartment(Integer.parseInt(req.getParameter("apartment")));

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContextMVC.xml");
        HibService hibService = context.getBean("hibService", HibService.class);
        //HibService hibService = HibService.getHibService();
        hibService.saveAddress(address);
        context.close();
        req.getRequestDispatcher("WEB-INF/jsp/Address.jsp").forward(req, resp);
    }
}