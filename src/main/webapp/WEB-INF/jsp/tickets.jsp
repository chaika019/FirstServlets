<%@ page import="service.TicketService" %>
<%@ page import="dto.TicketDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Ticket jsp</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Купленные билеты:</h1>
<ul>
    <c:if test="${not empty requestScope.tickets}">
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>${fn:toLowerCase(ticket.seat_number)}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>
