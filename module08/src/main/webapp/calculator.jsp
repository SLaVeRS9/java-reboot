<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Калькулятор доходности вклада</title>
    <h1>Калькулятор доходности вклада</h1>
</head>
<body>
    <form method="post" action="/finance">
        <div><p>Сумма на момент открытия <input class="form1" type="text" name="amount"/></p></div>
        <div><p>Процентная ставка <input class="form2" type="text" name="interestRate"/></p></div>
        <div><p>Количество лет <input class="form3" type="text" name="yearsAmount"/></p></div>
        <div><button>Посчитать</button></div>
    </form>

</body>
</html>