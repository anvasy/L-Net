<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="/register" method="POST" id="register">
    Имя: <input type="text" name="name" required="required" style="height: 30px"/>
    <br><br>
    Пароль: <input type="password" name="password" required="required" style="height: 30px;"/>
    <button>Зарегистрироваться</button>
</form>
</body>
</html>

