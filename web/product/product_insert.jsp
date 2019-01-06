<%--
  Created by IntelliJ IDEA.
  User: danqu
  Date: 06/01/2019
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Insert New Product</title>
</head>
<body>
<a href="/products"><h4>Back to Product List</h4></a>
<h2>Insert New Product</h2>

<p>
    <c:if test="${message != null}">
        <span style="color: blue">${message}</span>
    </c:if>
</p>

<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" id="price"></td>
            </tr>
            <tr>
                <td>Madein: </td>
                <td><input type="text" name="madein" id="madein"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Insert Product"></td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>
