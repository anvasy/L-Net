<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        header{
            background-color: #f87b00;
            height: 5%;
        }
        body{
            padding:0;
            margin:0;
        }
        h3 {
            font-family: Arial;
            font-size: 20px;
        }
        button {
            font-weight: bold;
            color: white;
            padding: .8em 1em calc(.8em + 3px);
            border-radius: 5px;
            background: #6ec5b8;
        }
    </style>
</head>
<body>
<form action="register" method="POST" id="register">
    Имя: <input type="text" name="name" required="required" style="height: 30px"/>
    <br><br>
    Пароль: <input type="password" name="password" required="required" style="height: 30px;"/>
    <button>Зарегистрироваться</button>
</form>
</body>
</html>

