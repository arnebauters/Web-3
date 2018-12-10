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
        <jsp:param name="title" value="My shopping Cart"/>
    </jsp:include>
    <main>
        <c:choose>
            <c:when test="${sessionScope.shoppingcart == null || sessionScope.shoppingcart.isEmpty()}">
                <p>Your Shopping Cart is empty.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>QTY</th>
                </tr>
                <c:forEach var="set" items="${sessionScope.cart}">
                    <tr>
                        <td>${set.key.naam}</td>
                        <td>${set.key.merk}</td>
                        <td>${set.key.prijs}</td>
                        <td>${set.value}</td>
                        <td><a href="Controller?action=DeleteFromBasket&fiets=${set.key.productId}">Verwijder</a></td>
                    </tr>
                </c:forEach>
                <caption>Cart oveview</caption>
            </table>
            <br>
            <p>Total price: ${TotalPrice}</p>
            <br>
            <p>Items: ${size}</p>
            <form class="pay" method="POST" action="Controller?action=Checkout">
                <input type="hidden" name="cart" value="${sessionScope.cart}">
                <p><input type="submit" id="checkout" value=PAY></p>
            </form>
        </c:otherwise>
        </c:choose>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
