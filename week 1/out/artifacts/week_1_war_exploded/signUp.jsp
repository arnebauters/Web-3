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
        <jsp:param name="title" value="Sign up"/>
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

        <form method="post" action="Controller?action=AddUser" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p class=${userid}><label for="userid">User id</label>
                <input type="text" id="userid" name="userid" required <c:if test="${waardeuserid!=null}"> value="<c:out value="${waardeuserid}"/>" </c:if>></p>

            <p class=${firstName}><label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" required <c:if test="${waardeuserid!=null}"> value ="<c:out value='${waardefirstName}'/>"</c:if>></p>

            <p class=${lastName}><label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" required <c:if test="${waardelastName!=null}"> value ="<c:out value='${waardelastName}'/>"</c:if>></p>

            <p class=${email}><label for="email">Email</label>
                <input type="email" id="email" name="email" required <c:if test="${waardeemail!=null}"> value="<c:out value='${waardeemail}'/>"</c:if>></p>

            <p><label for="password">Password</label><input type="password" id="password" name="password" required ></p>
            <c:if test="${name.role == 'ADMIN'}">
                <p><input type="radio" name="role" value="ADMIN"> Admin</p>
                <p><input type="radio" name="role" value="CUSTOMER"> Customer</p>
            </c:if>
            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
