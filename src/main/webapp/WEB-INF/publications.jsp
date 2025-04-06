<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.ee/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Publications</title>
</head>
<body>
<h1>Active Publications</h1>
<ul>
    <c:forEach var="publication" items="${publications}">
        <li>
            <strong>${publication.title}</strong> -
                ${publication.monthlyPrice} USD
            <br>
            <em>${publication.description}</em>
        </li>
    </c:forEach>
</ul>

<h2>Add a New Publication</h2>
<form action="/publications" method="POST">
    <label for="title">Title: </label>
    <input type="text" name="title" required><br><br>
    <label for="description">Description: </label>
    <textarea name="description" required></textarea><br><br>
    <label for="monthlyPrice">Monthly Price: </label>
    <input type="number" step="0.01" name="monthlyPrice" required><br><br>
    <button type="submit">Add Publication</button>
</form>
</body>
</html>
