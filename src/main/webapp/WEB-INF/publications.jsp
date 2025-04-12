<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publications</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<header>
    <h1>Publications</h1>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/subscriptions">Manage Subscriptions</a></li>
            <li><a href="/login">Login</a></li>
        </ul>
    </nav>
</header>

<section>
    <h2>Available Publications</h2>
    <ul>
        <c:forEach var="publication" items="${publications}">
            <li>
                <h3>${publication.title}</h3>
                <p>${publication.description}</p>
                <p>Price: $${publication.monthlyPrice}</p>
                <a href="/subscriptions?publicationId=${publication.id}" class="btn">Subscribe</a>
            </li>
        </c:forEach>
    </ul>
</section>

<footer>
    <p>&copy; 2025 Publication System</p>
</footer>
</body>
</html>
