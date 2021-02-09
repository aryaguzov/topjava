<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<form action="add.jsp">
    <input type="submit" value="Add Meal">
    <table border=1>
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mealsWithExcess}" var="meal">
            <tr style="background-color:${meal.excess ? 'red' : 'greenyellow'}">
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <td>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                </td>
                <td><c:out value="${meal.description}"/></td>
                <td><c:out value="${meal.calories}"/></td>
                </td>
                <td>
                    <form action="add.jsp?action=update&id=${meal.id}" method="get">
                        <input type="submit" value="Update" style="float:left">
                    </form>
                </td>
                <td>
                    <form action="meals.jsp?action=delete&id=${meal.id}" method="get">
                        <input type="submit" value="Delete" style="float:left">
                    </form>
                </td>
            </tr>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
