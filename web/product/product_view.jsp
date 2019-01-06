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
    <title>View Product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${product.getName()}</td>
    </tr>
    <tr>
        <td>Email: </td>
        <td>${product.getPrice()}</td>
    </tr>
    <tr>
        <td>Madein: </td>
        <td>${product.getMadein()}</td>
    </tr>
</table>
</body>
</html>