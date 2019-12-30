package servlets;

import models.address.Address;
import models.person.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.HibService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/person")
public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("WEB-INF/jsp/Result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContextMVC.xml");
        HibService hibService = context.getBean("hibService", HibService.class);
        //HibService hibService = HibService.getHibService();
        Person person = new Person();
        Address address = new Address();

        person.setName(req.getParameter("name"));
        person.setSurname(req.getParameter("surname"));
        person.setPatronymic(req.getParameter("patronymic"));

        String str = req.getParameter("address");

        address.setCity(str.substring(0,str.indexOf(',')));
        address.setHouse(Integer.parseInt(str.substring(str.lastIndexOf(' ')+1, str.indexOf('/'))));
        address.setApartment(Integer.parseInt(str.substring(str.indexOf('/') +1)));

        person.setAddress(address);
        address.addPerson(person);

        hibService.updateAddress(address);


        ArrayList<Address> addresses;
        addresses = (ArrayList<Address>) hibService.findAllAddresses();
        req.setAttribute("list", addresses);


        context.close();
        req.getRequestDispatcher("WEB-INF/jsp/Person.jsp").forward(req, resp);

    }
}
