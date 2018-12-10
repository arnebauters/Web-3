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
        <jsp:param name="title" value="Home"/>
    </jsp:include>
    <c:if test="${sessionScope.name != null}">
        <h2>Welcome <c:out value="${name.userid}"/>!</h2>
    </c:if>

    <main> Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
        ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
        aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
        qui ratione voluptatem sequi nesciunt.
        <c:if test="${notAuthorized!=null}">
            <p class="has-error">${notAuthorized}</p>
        </c:if>



        <form method="post" action="Controller?action=ShowQuote"
              novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p>Wil je een quote zien?</p>
            <p><label for="Yes">Yes</label><input type="radio" id="Yes" name="confirm" value="Ja"
            <c:if test="${checked == 'Ja'}"> checked </c:if>
                <label for="No">No</label> <input type="radio" id="No" name="confirm" value="Nee" <c:if
                        test="${checked == 'Nee'}"> checked </c:if></p>
            <input type="submit" id="chooseQuote" value="Submit">
        </form>
        <c:if test="${showQuote != null}">
            <p>Cyclists live with pain. If you can’t handle it you will win nothing</p>
        </c:if>
        <c:if test="${error!=null}">
            <div class="alert-danger">
                <ul>
                        <li>${error}</li>
                </ul>
            </div>
        </c:if>
        <c:choose>
        <c:when test="${sessionScope.name == null}">

        <form method="post" action="Controller?action=Login" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userid">User id</label><input type="text" id="userid" name="userid" required
            <c:if test="${waardeuserid!=null}"> value="<c:out value="${waardeuserid}"/>"
            </c:if>></p>
            <p><label for="password">Password</label><input type="password" id="password" name="password" required></p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>
        </form>
            </c:when>
            <c:otherwise>
                <form method="post" action="Controller?action=Logout">
                    <input type="hidden" name="name" value="<c:out value="${sessionScope.name}"/>">
                <input type="submit" id="logout" value="Logout">
                </form>
            </c:otherwise>
        </c:choose>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>