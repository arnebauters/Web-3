<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Bevestiging te verwijderen gegevens"/>
    </jsp:include>
    <c:choose>
        <c:when test="${fiets.productId!=null}">
            <p>Naam: <c:out value="${fiets.naam}"/></p>
            <p>ProductId: <c:out value="${fiets.productId}"/></p>
        </c:when>
        <c:otherwise>
            <p>Persoon: <c:out value="${person.userid}"/></p>
        </c:otherwise>
    </c:choose>
    <p>Wil je hiermee doorgaan?</p>
    <form method="post" action="Controller?action=DeleteConfirmed&productId=<c:out value="${fiets.productId}"/>&person=<c:out value="${person.userid}"/>"
          novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->

        <p><input type="submit" name="confirm" value="Ja">
            <input type="submit" name="confirm" value="Nee"></p>

    </form>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
