<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title>${article.topic}</title>
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
<header></header>
<br>
<h1>${article.topic}</h1>
<p>
    ${article.content}
</p>

<div>
    <form action="/addedit" method="GET">
        <button name="id" value="${article.id}">Изменить</button>
    </form>
    <form action="/article" method="POST">
        <button name="id" value=${article.id}>Удалить</button>
    </form>

    <form action="/" method="POST">
        <button name="id" value=${article.id}>Тест</button>
    </form>
</div>

</body>
<ctg:copyright/>
</html>