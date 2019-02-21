<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Article list</title>
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
    </style>
</head>
<body>
<header></header>

    <H3>СПИСОК СТАТЕЙ: </H3>
    <TABLE BORDER="1">
        <TR>
            <TH>№</TH>
            <TH>НАЗВАНИЕ</TH>
            <TH>КРАТКОЕ СОДЕРЖАНИЕ</TH>
            <TH style="width: 55px"></TH>
        </TR>
        <c:set var="count" value="0" scope="page" />
        <c:forEach var="article" items="${articles}">
            <TR>
                <TD>${count + 1}</TD>
                <TD>${article.topic}</TD>
                <TD>${article.summary}</TD>
                <form action="/article" method="POST" id="show">
                    <TD style="background-color: #6ec5b8;"><button name="id" value="${article.id}" style="height: 50px;
                    width: 50px; background: transparent; border: none"></button></TD>
                </form>
            </TR>
        </c:forEach>
    </TABLE>
<br>
    <form action="/addedit" method="GET" id="add">
        <button name="id" value="0" style="width: 100%">ДОБАВИТЬ СТАТЬЮ</button>
    </form>
</body>
</html>