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
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=overview">Overview</a></li>
                <li><a href="Controller?action=productoverview">Products</a></li>
                <li><a href="Controller?action=formproduct">Add product</a></li>
                <li><a href="Controller?action=signUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>Bevestiging te verwijderen gegevens</h2>

    </header>
    <c:choose>
        <c:when test="${fiets.naam!=null}">
            <p>Naam: ${fiets.naam}</p>
        </c:when>
        <c:otherwise>
            <p>Persoon: ${person.userid}</p>
        </c:otherwise>
    </c:choose>
    <p>Wil je hiermee doorgaan?</p>
    <form method="post" action="Controller?action=deleteconfirmed&fiets=${fiets.naam}&person=${person.userid}"
          novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->

        <p><input type="submit" name="confirm" value="Ja">
            <input type="submit" name="confirm" value="Nee"></p>

    </form>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
