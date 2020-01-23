<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<body>

<form action="car" method="post" >
    <label>Enter your carId</label>
    <input name="model">
    <br>
    <br>

    <label>Enter your model</label>
    <input name="model">
    <br>
    <br>

    <label>Enter your horse Power</label>
    <input name="horsePower">
    <br>
    <br>

    <label>Enter ownerId</label>
    <input name="ownerId">
    <br><br>

    <label>Choose address</label>
    <select name="address">
    </select>
    <button>Add</button>
</form>
<form action="infoPerson" method="get">
    <button>continue</button>
</form>
</body>
</html>
