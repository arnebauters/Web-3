<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1><span>Web shop</span></h1>
    <nav>
        <ul>
            <li><a href="Controller?action=Home">Home</a></li>
            <li><a href="Controller?action=PersonOverview">Overview</a></li>
            <li><a href="Controller?action=ProductOverview">Products</a></li>
            <c:if test="${name.role == 'ADMIN'}">
            <li><a href="Controller?action=FormProduct">Add product</a></li>
            </c:if>
            <li><a href="Controller?action=SignUp">Sign up</a></li>

        </ul>
    </nav>
    <a href="Controller?action=BasketOverview">
        <img src="images/shopping_cart.png" alt="shopping Cart" align="right"></a>
    <h2>
        ${param.title}
    </h2>
</header>
