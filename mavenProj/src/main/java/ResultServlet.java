import person.Person;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;


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
        DBService dbService = DBService.getInstance();
        dbService.insertRaw(person);
        persons = dbService.getAllRaws();

        req.setAttribute("persons", persons);

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/Result.jsp").forward(req, resp);

    }
}