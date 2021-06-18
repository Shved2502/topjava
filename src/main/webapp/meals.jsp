<%--
  Created by IntelliJ IDEA.
  User: shved
  Date: 10.06.2021
  Time: 03:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
  <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<hr>
<table>
  <jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
  <tr>
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
  </tr>
  <c:forEach var="meal" items="${meals}">
    <c:choose>
      <c:when test="${meal.exceed==true}">
        <tr bgcolor="red">
      </c:when>
      <c:when test="${meal.exceed==false}">
        <tr bgcolor="green">
      </c:when>
    </c:choose>
    <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
    <td>${meal.description}</td>
    <td>${meal.calories}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
