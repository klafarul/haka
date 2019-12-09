import person.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResultServlet extends HttpServlet {
    Person person;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        person = (Person) session.getAttribute("person");
        person.setPatronymic(req.getParameter("patronymic"));
        req.setAttribute("person", person);
        req.getRequestDispatcher("WEB-INF/jsp/Result.jsp").forward(req, resp);

    }
}
