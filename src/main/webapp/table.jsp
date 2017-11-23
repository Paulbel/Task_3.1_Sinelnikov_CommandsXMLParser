<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>

<c:set var="perPage" scope="session" value="${param.perPage}"/>
<c:set var="start" scope="session" value="${param.start}"/>
<c:set var="count" value="${requestScope.count}"/>


<c:if test="${start-perPage >= 0}">
    <a href="FrontController?command=changeEntries&start=${start - perPage}&perPage=${perPage}"><<</a>
</c:if>
<c:choose>
    <c:when test="${perPage < count}">
        <c:set var="size" value="${count-2}"/>
        <c:out value="${start + 1} - ${start + size+1}"/>
        <a href="FrontController?command=changeEntries&start=${start + perPage}&perPage=${perPage}">>></a>
    </c:when>
    <c:otherwise>
        <c:set var="size" value="${count-1}"/>
        <c:out value="${start + 1} - ${start + size+1}"/>
    </c:otherwise>
</c:choose>


<c:if test="${not empty requestScope.commands}">
    <table border="1">
        <c:forEach var="entry" items="${requestScope.commands}"
                   begin="0" end="${size}">
            <tr>
                <td><c:out value="${entry.key}"/></td>
                <td><c:out value="${entry.value}"/></td>
            </tr>
        </c:forEach>
    </table>
    <form action="FrontController" method="get">
        <input type="hidden" name="start" value="${start}">
        <input type="hidden" name="command" value="changeEntries">
        <input type="text" name="perPage" value="${perPage}" style="width: 20px"/>

    </form>
</c:if>