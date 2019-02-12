<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Cats Go To Heaven</title>
    <link rel="stylesheet" href="../../resources/css/main.css" type="text/css">
</head>
<body>
    <H3>СПИСОК СТАТЕЙ: </H3>
    <TABLE BORDER="1">
        <TR>
            <TH>№</TH>
            <TH>НАЗВАНИЕ</TH>
            <TH>КРАТКОЕ СОДЕРЖАНИЕ</TH>
        </TR>
        <c:set var="count" value="0" scope="page" />
        <c:forEach var="article" items="${articles}">
            <TR>
                <TD>${count + 1}</TD>
                <TD>${article.id}</TD>
                <TD>${article.summary}</TD>
            </TR>
        </c:forEach>
    </TABLE>
    <button topic="id" value="0">ДОБАВИТЬ СТАТЬЮ</button>
</body>
</html>