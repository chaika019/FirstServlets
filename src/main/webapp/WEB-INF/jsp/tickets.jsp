<%@ page import="service.TicketService" %>
<%@ page import="dto.TicketDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Ticket jsp</title>
</head>
<body>
<h1>Купленные билеты:</h1>
<ul>
    <%
        TicketService ticketService = TicketService.getInstance();
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        for (TicketDto ticketDto : ticketService.findAllByFlightId(flightId)) {
            out.write(String.format("<li>%s</li>", ticketDto.seat_number()));
        }
    %>
</ul>
</body>
</html>
