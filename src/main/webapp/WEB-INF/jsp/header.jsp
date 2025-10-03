<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty sessionScope.user}">
  <form action="${pageContext.request.contextPath}/login" method="post">
    <button type="submit">Logout</button>
  </form>
</c:if>