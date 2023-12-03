<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Title</title>
        <h1>${requestScope.get("title")}</h1>
    </head>
    <body>
        <div>
            <p1>${requestScope.get("description")}</p1>
        </div>
    </body>
</html>