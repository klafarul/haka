<%@ page import="person.Person" %>
<html>
    <title>Result</title>
<body>

<%
    request.getAttribute("person");
    Person person  = (Person) request.getAttribute("person");
%>

    <h3>Hello, <%=person.getSurname()%>  <%=person.getName()%>  <%=person.getPatronymic()%>!!</h3>

</body>
</html>
