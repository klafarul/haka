<%@ page language="java" contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="person.PersonEntity" %>
<%@ page import="person.Person" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>


<body>
    <%
        ArrayList<Person> people = (ArrayList<Person>) request.getAttribute("persons");
        PrintWriter pw = response.getWriter();
        for (int i = 0; i < people.size(); i++){
            pw.write(people.get(i).getName() );
            pw.write(people.get(i).getSurname());
            pw.write(people.get(i).getPatronymic());
            pw.write("<br>");
        }
        pw.write("ALL GoOd!!!");


    %>
</body>
</html>