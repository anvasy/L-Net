<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title>Администратор</title>
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
        table {
            border-collapse: collapse;
            width: 100%;
            counter-reset: rowNumber;
        }
        td, th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        tr:nth-child(even){background-color: #f2f2f2;}
        tr:hover {background-color: #ddd;}
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #f87b00;
            color: white;
        }
    </style>
</head>
<body>
<header>
        <form action="bonus" method="get">
            <button style="width: 100px">Выйти</button>
        </form>
</header>
<h3>СПИСОК ПОЛЬЗОВАТЕЛЕЙ:</h3>
<form action="admin" method="POST">
<TABLE class="table table-striped table-hover">
    <TR style="background-color: #2a6496; color: #2a6496">
        <TH>Логин</TH>
        <TH>Имя</TH>
        <TH>Фамилия</TH>
        <TH>Роль</TH>
        <TH>Тип регистрации</TH>
        <TH></TH>
    </TR>
    <c:forEach var="tableUser" items="${users}">
        <TR>
            <TD>${tableUser.username}</TD>
            <TD>${tableUser.name}</TD>
            <TD>${tableUser.surname}</TD>
            <TD>${tableUser.role}</TD>
            <TD>${tableUser.regType}</TD>
            <c:choose>
                <c:when test="${tableUser.role eq 'user'}">
                    <TD><button name="id" value="${tableUser.id}">Повысить</button></TD>
                </c:when>
                <c:otherwise>
                    <TD><button name="id" value="${tableUser.id}">Понизить</button></TD>
                </c:otherwise>
            </c:choose>
        </TR>
    </c:forEach>
</TABLE>
</form>
</body>
</html>
