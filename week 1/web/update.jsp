<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Update a bike"/>
    </jsp:include>
    <main>
        <c:if test="${errors!=null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <p>Vul volgende velden in om ${fiets.naam} aan te passen.</p>
        <form method="post" action="Controller?action=UpdateConfirmed&productId=${fiets.productId}" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p class=${Name}><label for="Name">Name</label><input type="text" id="Name" name="Name"
                                                                  required value="<c:out value="${fiets.naam}"/>"></p>
            <p class=${Brand}><label for="Brand">Brand</label><input type="text" id="Brand" name="Brand"
                                                                     required value="<c:out value="${fiets.merk}"/>"></p>
            <p class=${Price}><label for="Price">Price</label><input type="number" id="Price" name="Price"
                                                                     required value="<c:out value="${fiets.prijs}"/>"></p>
            <p><input type="submit" id="updatebike" value="Update bike"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>


