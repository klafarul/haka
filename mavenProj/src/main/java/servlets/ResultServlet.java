package servlets;

import person.Person;
import person.PersonEntity;
import services.HibService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person;
        ArrayList<Person> persons;

        HttpSession session = req.getSession();
        person = (Person) session.getAttribute("person");
        person.setPatronymic(req.getParameter("patronymic"));

        //Person is ready to push in DB
        HibService hibService = new HibService();
        hibService.savePerson(person);
        persons = (ArrayList<Person>) hibService.findAllPersons();


        req.setAttribute("person", person);
        req.setAttribute("persons", persons);

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/Result.jsp").forward(req, resp);

    }
}