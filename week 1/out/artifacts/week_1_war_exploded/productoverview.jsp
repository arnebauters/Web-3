<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css"
          href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Overview products"/>
    </jsp:include>
    <main>
        <p>De duurste fiets is: ${duurste.naam} ${duurste.merk}</p>
        <c:if test="${errors!=null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <c:if test="${message!=null}"><p class="succes">${message}</p></c:if>
        <table>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
                <th>Brand</th>
                <th>Price</th>
            </tr>
            <c:forEach var="fiets" items="${products}">
                <tr>
                    <td><c:if test="${name.role == 'ADMIN'}">
                        <a href="Controller?action=Aanpassen&productId=${fiets.productId}"></a>
                                </c:if>${fiets.productId}</td>
                    <td><c:out value="${fiets.naam}"/></td>
                    <td><c:out value="${fiets.merk}"/></td>
                    <td><c:out value="${fiets.prijs}"/></td>
                    <c:if test="${name.role == 'ADMIN'}">
                    <td><a href="Controller?action=Delete&fiets=<c:out value="${fiets.productId}"/>&person=none">Verwijder</a></td>
                    </c:if>
                    <form method="post" action="Controller?action=AddBasket&fiets=<c:out value="${fiets.productId}"/>">
                    <td><input type="number" id="aantal" name="aantal" value="1"></td>
                    <td><input type="submit" id="add" value="Add to shopping cart"></td>
                    </form>
                </tr>
            </c:forEach>
            <c:if test="${size!= null}">
                <p>Items in shopping cart: ${size}</p>
            </c:if>
            <caption>Products Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
