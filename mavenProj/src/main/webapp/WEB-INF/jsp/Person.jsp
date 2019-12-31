<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<body>

<form action="person" method="post">
    <label>Enter your name</label>
    <input name="name">
    <br>
    <br>

    <label>Enter your Surname</label>
    <input name="surname">
    <br>
    <br>

    <label>Enter your Patronymic</label>
    <input name="patronymic">
    <br>
    <br>

    <label>Choose address</label>
    <select name="address">
        <c:forEach items="${list}" var="value">
            <option value="${value}">
                ${value.toString()}
            </option>
        </c:forEach>
    </select>
    <button>Add</button>
</form>
<form action="result" method="post">
    <button>continue</button>
</form>
</body>
</html>
