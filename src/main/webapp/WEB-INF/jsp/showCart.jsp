<%--
  Created by IntelliJ IDEA.
  User: egorc
  Date: 21.07.2025
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show cart</title>
</head>
<body>
    <%@ page import="logic_DontOpen_Trash.Cart.Cart" %>

    <% Cart cart = (Cart) session.getAttribute("cart"); %>

    <p> Наименование: <%= cart.getName() %> </p>
    <p> Количество: <%= cart.getQuantity() %> </p>
</body>
</html>
