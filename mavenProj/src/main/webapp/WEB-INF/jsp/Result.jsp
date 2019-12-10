<%@ page language="java" contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="person.Person" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>

<body>
<h3>Hello, <c:out  value="${person.surname}" default="213"/>45 <c:out value="${person.name}"/>  <c:out value="${person.patronymic}"/>
</body>
</html>