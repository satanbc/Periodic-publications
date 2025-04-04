<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Periodical Subscriptions</title>
</head>
<body>
<header>
    <h1>${message.welcome.message}</h1>
</header>

<div>
    <h2>Available Publications</h2>
    <ul>
        <c:forEach var="publication" items="${publications}">
            <li>${publication.title} - ${publication.price}</li>
        </c:forEach>
    </ul>
</div>

<footer>
    <p>© 2025 Periodical Subscription System</p>
</footer>
</body>
</html>
