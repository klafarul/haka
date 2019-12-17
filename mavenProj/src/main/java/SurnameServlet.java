import person.Pers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/surname")
public class SurnameServlet extends HttpServlet {
    Pers pers;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        pers = new Pers();
        pers.setName(req.getParameter("name"));
        session.setAttribute("person", pers);
        req.getRequestDispatcher("WEB-INF/jsp/Surname.jsp").forward(req, resp);
    }
}