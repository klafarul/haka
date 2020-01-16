<%@ page language="java" contentType="text/html; UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>


<body>
<form action="result" method="get">
    <button>Show Addresses</button>
    <br>
    <c:forEach items="${addresses}" var="address">
        <br>
        ${address.toString()}
        :
        <br>

        <c:forEach items="${address.getPersons()}" var="person">
            ${person.toString()}<br>
        </c:forEach>
    </c:forEach>
</form>
</body>
</html>