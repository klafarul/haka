import person.Person;

import java.sql.*;
import java.util.ArrayList;

public final class DBService {

    private static  DBService instance;
    private final static String URL = "jdbc:postgresql://localhost:5432/Persons";
    private final static String USER = "postgres";
    private final static String PASSWORD = "1234";
    private Connection connection;

    private DBService(){

        try{
             Class.forName("org.postgresql.Driver");
             connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (Exception e ){
        }
    }

    public static DBService getInstance(){
        if (instance == null){
            instance = new DBService();
        }
        return instance;
    }
    

    public ResultSet sqlQuery(String query){
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

        }
        catch (SQLException e){}
        return resultSet;
    }

    public void insertRaw(Person person){

        String sqlQP = "INSERT INTO PERSON(ID, NAME, SURNAME, PATRONYMIC) VALUES (nextval('i'),?,?,?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQP);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getPatronymic());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e){}
    }

    public ArrayList<Person> getAllRaws(){
        ArrayList<Person> persons = new ArrayList<Person>();
        Person person;
        try{
            ResultSet resultSet = sqlQuery("SELECT * FROM PERSON;");
            while (resultSet.next()){
                person = new Person();
                person.setName(resultSet.getString("NAME"));
                person.setSurname(resultSet.getString("SURNAME"));
                person.setPatronymic(resultSet.getString("PATRONYMIC"));
                persons.add(person);
            }
            resultSet.close();
        }
        catch (SQLException ex){}
        return persons;
    }
}
