<%--
  Created by IntelliJ IDEA.
  User: noname
  Date: 07.02.2021
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<section>
    <tr>
        <b>Edit meal</b>
        <br></br>
    </tr>
    <form action="${pageContext.request.contextPath}/meals" method="post">
        <section><input required type="datetime-local" name="dateTime" placeholder="DateTime"></section>
        <section><input required type="text" name="description" placeholder="Description"></section>
        <section><input required type="number" name="calories" placeholder="Calories"></section>
        <br></br>
        <section><input type="submit" value="Save">
            <button type="button" class="btn btn-primary" onclick="window.history.back();">Cancel</button>
        </section>
    </form>
</section>
</body>
</html>
