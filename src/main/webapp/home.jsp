<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title>Article list</title>
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
        button {
            font-weight: bold;
            color: white;
            padding: .8em 1em calc(.8em + 3px);
            border-radius: 5px;
            background: #6ec5b8;
        }
        table tr:not(:first-child) {
            counter-increment: rowNumber;
        }
        table tr td:first-child::before {
            content: counter(rowNumber);
            min-width: 1em;
            margin-right: 0.5em;
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

<H3>СПИСОК СТАТЕЙ: </H3>
<form action="addedit" method="GET">
    <button name="id" value="0" style="width: 100%">ДОБАВИТЬ СТАТЬЮ</button>
</form>
    <TABLE BORDER="1">
        <thead>
        <TR>
            <TH>№</TH>
            <TH>НАЗВАНИЕ</TH>
            <TH>КРАТКОЕ СОДЕРЖАНИЕ</TH>
            <TH></TH>
            <c:if test="${sessionScope.get('user') ne null}">
            <TH></TH>
            <c:if test="${role eq 'admin'}">
                <TH></TH>
            </c:if>
            </c:if>
        </TR>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}">
            <TR>
                <TD></TD>
                <TD>${article.topic}</TD>
                <TD>${article.summary}</TD>
                <form action="article" method="GET" id="show">
                    <TD><button name="id" value="${article.id}">
                        Просмотреть
                    </button>
                    </TD>
                </form>
                <c:if test="${sessionScope.get('user') ne null}">
                <td>
                <form action="addedit" method="GET">
                    <button name="id" value=${article.id}>
                        Изменить
                </button>
                </form>
                </td>
                    <c:if test="${role eq 'admin'}">
                <td>
                <form action="article" method="POST">
                    <button name="id" value=${article.id}>
                        Удалить
                    </button>
                </form>
                </td>
                    </c:if>
                </c:if>
            </TR>
        </c:forEach>
        </tbody>
    </TABLE>
<br>
    <form action="addedit" method="GET">
        <button name="id" value="0" style="width: 100%">ДОБАВИТЬ СТАТЬЮ</button>
    </form>
</body>
<ctg:copyright/>
</html>