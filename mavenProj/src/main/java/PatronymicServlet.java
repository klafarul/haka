
import person.Pers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/patronymic")
public class PatronymicServlet extends HttpServlet {
    Pers pers;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        pers = (Pers) session.getAttribute("person");
        pers.setSurname(req.getParameter("surname"));
        session.setAttribute("person", pers);

        req.getRequestDispatcher("WEB-INF/jsp/Patronymic.jsp").forward(req, resp);
    }
}