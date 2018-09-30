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
    <header>
        <h1><span>Web shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li ><a href="Controller?action=overview">Overview</a></li>
                <li id="actual"><a href="Controller?action=productoverview">Products</a></li>
                <li><a href="Controller?action=formproduct">Add product</a></li>
                <li><a href="Controller?action=signUp">Sign up</a></li>

            </ul>
        </nav>
        <h2>
            Product Overview
        </h2>

    </header>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Brand</th>
                <th>Price</th>
            </tr>
            <c:forEach var="fiets" items="${products}">
                <tr>
                    <td><a href="Controller?action=aanpassen&naam=${fiets.naam}">${fiets.naam}</a></td>
                    <td>${fiets.merk}</td>
                    <td>${fiets.prijs}</td>
                    <td><a href="Controller?action=delete&fiets=${fiets.naam}&person=none">Verwijder</a></td>
                </tr>
            </c:forEach>
            <caption>Products Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
