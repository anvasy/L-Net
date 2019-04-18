<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${article.topic}</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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
<h1>${article.topic}</h1>
<h3>Оценка: ${article.rate}</h3>
<c:if test="${sessionScope.get('user') ne null}">
<form action="bonus" method="post">
    <select style="height: 50px; margin-left: 20px" name="rated">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
    </select>
    <button name="arid" style="width: 100px" value="${article.id}">Оценить</button>
</form>
</c:if>
<p>
    ${article.content}
</p>
<c:if test="${sessionScope.get('user') ne null}">
<div>
    <form action="addedit" method="GET">
        <button name="id" value="${article.id}">Изменить</button>
    </form>
    <c:if test="${role eq 'admin'}">
    <form action="article" method="POST">
        <button name="id" value=${article.id}>Удалить</button>
    </form>
    </c:if>
</div>
</c:if>

</body>
<ctg:copyright/>
</html>