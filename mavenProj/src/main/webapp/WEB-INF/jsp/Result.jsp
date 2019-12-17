<%@ page language="java" contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="person.Pers" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>


<body>
    <%

        ArrayList<Pers> persons = (ArrayList<Pers>) request.getAttribute("persons");
        PrintWriter pw = response.getWriter();
        for (int i = 0; i < persons.size(); i++){
            pw.write(persons.get(i).getName() );
            pw.write(persons.get(i).getSurname());
            pw.write(persons.get(i).getPatronymic());
            pw.write("<br>");
        }
        pw.write("ALLGOOd");


    %>
</body>
</html>