<%@ page language="java" contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="person.Person" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>

<body>
    <h3>Hello, <c:out  value="${person.getName()}" default="213"/> <c:out value="${person.getSurname()}"/>  <c:out value="${person.getPatronymic()}"/>
</body>
</html>
