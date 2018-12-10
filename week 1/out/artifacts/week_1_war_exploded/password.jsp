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
        <jsp:param name="title" value="Check Password"/>
    </jsp:include>
    <main>
        <c:if test="${error!=null}">
            <div class="alert-danger">
                <p>${error}</p>
            </div>

        </c:if>

        <c:if test="${correct!= null}">
            <p>${correct}</p>

        </c:if>
        <p>Fill out your password:</p>
        <form method="post" action="Controller?action=CheckPassword&person=${person}" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="password">Password</label>
                <input type="password" id="password" name="password" required></p>

            <p><input type="submit" id="check Password" value="check Password"></p>

        </form>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
