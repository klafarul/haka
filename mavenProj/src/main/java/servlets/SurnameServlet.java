package servlets;

import models.person.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/surname")
public class SurnameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> addresses;
        Person person = new Person();
        HttpSession session = req.getSession();

        //addresses = (ArrayList<String>)session.getAttribute("addresses");
        //resp.getWriter().write(addresses.size() + "");

        person.setName(req.getParameter("name"));
        session.setAttribute("person", person);
        req.getRequestDispatcher("WEB-INF/jsp/Surname.jsp").forward(req, resp);
    }
}