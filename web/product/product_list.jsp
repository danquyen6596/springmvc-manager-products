<%--
  Created by IntelliJ IDEA.
  User: danqu
  Date: 06/01/2019
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Product List View</title>
</head>
<body>
<h2>Product List View</h2>

<a href="/products?action=create"><h3>Insert New Product</h3></a>

<table border="1">
    <tr>
        <td>Name</td>
        <td>Price</td>
        <td>Made in</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>
                <a href="/products?action=view&code=${product.getCode()}">${product.getName()}</a>
            </td>
            <td>${product.getPrice()}</td>
            <td>${product.getMadein()}</td>
            <td><a href="/products?action=update&code=${product.getCode()}">Edit</a></td>
            <td><a href="/products?action=delete&code=${product.getCode()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
