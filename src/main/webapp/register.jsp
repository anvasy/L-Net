<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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
<div class="container">
    <div class="no-gutter row">
        <div class="col-md-12" id="content">
            <div class="panel-heading" style="background-color:#f87b00;color:#f87b00;height: 40px">
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-6" style="height: 55%;margin-top: 20px;">
            <form action="register" method="POST" id="register">
                Логин: &nbsp&nbsp&nbsp&nbsp&nbsp <input type="text" name="username" required="required" style="width: 85%; height: 30px"/>
                <br><br>
                Пароль: &nbsp&nbsp <input type="password" name="password" required="required" style="width: 85%; height: 30px"/>
                <br><hr><br>
                Имя: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type="text" name="name" style="width: 85%; height: 30px"/>
                <br><br>
                Фамилия: <input type="text" name="surname" style="width: 85%; height: 30px"/>
                <br>
                <br>
                <button class="btn-block">Зарегистрироваться</button>
            </form>
            </div>
        </div>
        </div>
    </div>
</div>
</body>
</html>

