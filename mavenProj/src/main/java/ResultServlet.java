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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;


@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    Person person;
    Connection connection;
    Statement statement;
    ArrayList<Person> persons;
    private final static String URL = "jdbc:postgresql://localhost:5432/Persons";
    private final static String USER = "postgres";
    private final static String PASSWORD = "1234";

    @Override
    public void init() throws ServletException {
        super.init();
        try {

        }catch (Exception e){}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        person = (Person) session.getAttribute("person");
        person.setPatronymic(req.getParameter("patronymic"));
        persons = new ArrayList<Person>();
        //Person is ready to push in DB

        resp.setContentType("text/plain");
        resp.setLocale(Locale.getDefault());
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();



            ResultSet resultSet = statement.executeQuery("SELECT Count(*) AS rowcount FROM PERSON;");
            resultSet.next();
            int nextId = resultSet.getInt("rowcount");
            String sqlQ = "INSERT INTO PERSON(ID, NAME, SURNAME, PATRONYMIC) VALUES (" + (nextId+1) + ", '" + person.getName() + "', '" + person.getSurname() + "', '" + person.getPatronymic() + "');";
            statement.executeUpdate(sqlQ);

            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM PERSON;");

            while (resultSet1.next()){
                person = new Person();
                person.setName(resultSet1.getString("NAME"));

                person.setSurname(resultSet1.getString("SURNAME"));
                person.setPatronymic(resultSet1.getString("PATRONYMIC"));
                persons.add(person);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        req.setAttribute("persons", persons);


        getServletContext().getRequestDispatcher("/WEB-INF/jsp/Result.jsp").forward(req, resp);

    }
}