<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <h1>${requestScope.get("title")}</h1>
    ${requestScope.title}
</head>
<body>
    <div>
        <p1>${requestScope.get("description")}</p1>
    </div>

</body>
</html>