<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Article</title>
    <style>
        header{
            background-color: #f87b00;
            height: 5%;
        }
        html {
            font-size: 20px;
            font-family: Arial;
        }
        body{
            padding:0;
            margin:0;
        }
        textarea, input {
            width: 100%;
            resize: vertical;
        }
        button {
            font-weight: bold;
            color: white;
            padding: .8em 1em calc(.8em + 3px);
            border-radius: 5px;
            background: #6ec5b8;
            width: 100%;
        }
    </style>
</head>
<body>
<header>
    <c:if test="${sessionScope.get('user') eq null}">
        <form action="login" method="get">
            <button style="width: 100px;">Войти</button>
        </form>
    </c:if>
    <c:if test="${sessionScope.get('user') ne null}">
        <form action="bonus" method="get">
            <button style="width: 100px">Выйти</button>
        </form>
    </c:if>
</header>
<br>
<form action="addedit" method="POST" id="newarticle">
    Тема статьи: <input type="text" name="name" required="required" style="height: 30px" value="${article.topic}">
    <br><br>
    Краткое содержание: <textarea name="summary" required="required" style="height: 10%;">${article.summary}</textarea>
    <br><br>
    Текст статьи: <textarea name="content" required="required" style="height: 50%;">${article.content}</textarea>
    <br>
    <br>
    <button name="id" value="${article.id}">Ок</button>
</form>
<button onclick="history.back();">Отмена</button>
</body>
<ctg:copyright/>
</html>
