import person.Pers;

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


    private ResultSet sqlQuery(String query){
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

        }
        catch (SQLException e){}
        return resultSet;
    }

    public void insertRaw(Pers pers){

        String sqlQP = "INSERT INTO PERSON(ID, NAME, SURNAME, PATRONYMIC) VALUES (nextval('i'),?,?,?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQP);
            preparedStatement.setString(1, pers.getName());
            preparedStatement.setString(2, pers.getSurname());
            preparedStatement.setString(3, pers.getPatronymic());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e){}
    }

    public ArrayList<Pers> getAllRaws(){
        ArrayList<Pers> perss = new ArrayList<Pers>();
        Pers pers;
        try{
            ResultSet resultSet = sqlQuery("SELECT * FROM PERSON;");
            while (resultSet.next()){
                pers = new Pers();
                pers.setName(resultSet.getString("NAME"));
                pers.setSurname(resultSet.getString("SURNAME"));
                pers.setPatronymic(resultSet.getString("PATRONYMIC"));
                perss.add(pers);
            }
            resultSet.close();
        }
        catch (SQLException ex){}
        return perss;
    }
}
