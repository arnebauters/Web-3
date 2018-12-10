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
        <jsp:param name="title" value="Person overview"/>
    </jsp:include>
    <main>
        <form method="POST" action="Controller?action=SortOverview">
            <select name="sort">
               <option value="firstname" <c:if test="${sorteer == 'firstname'}"> selected </c:if> >FirstName</option>
                <option value="lastname" <c:if test="${sorteer == 'lastname'}"> selected </c:if> >LastName</option>
                <option value="email" <c:if test="${sorteer == 'email'}"> selected </c:if> >Email</option>
            </select>
            <input class="option" type="submit" value="Query verzenden">
        </form>
        <table>
            <tr>
                <th>UserName</th>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach var="person" items="${lijst}">
                <tr>
                    <td><c:out value="${person.userid}"/></td>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                    <c:if test="${name.role == 'ADMIN'}">
                    <td>
                        <a href="Controller?action=Delete&fiets=none&person=<c:out value="${person.userid}"/>">Verwijder</a>
                    </td>
                    </c:if>
                    <td><a href="Controller?action=Password&person=<c:out value="${person.userid}"/>">Check Password</a>
                    </td>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>