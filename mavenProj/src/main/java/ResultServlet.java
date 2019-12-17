import person.Pers;

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
        Pers pers;
        ArrayList<Pers> perss;

        HttpSession session = req.getSession();
        pers = (Pers) session.getAttribute("person");
        pers.setPatronymic(req.getParameter("patronymic"));

        //Person is ready to push in DB
        HibService hibService = new HibService();
        hibService.savePerson(pers);
        perss = (ArrayList<Pers>) hibService.findAllPersons();


        req.setAttribute("person", pers);
        req.setAttribute("persons", perss);

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/Result.jsp").forward(req, resp);

    }
}