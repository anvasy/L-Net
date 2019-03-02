<%--
  Created by IntelliJ IDEA.
  User: anya_adm
  Date: 24.02.2019
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти</title>
</head>
<body>
<form action="/login" method="POST" id="login">
    Имя: <input type="text" name="name" required="required" style="height: 30px"/>
    <br><br>
    Пароль: <input type="password" name="password" required="required" style="height: 30px;"/>
    <button>Войти</button>
</form>
<form action="/register" method="GET">
    <button>Зарегистрироваться</button>
</form>
</body>
</html>
