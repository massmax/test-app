<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
		<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.min.js" ></script>
	</head>
	<body text = "#000000" >
		<h1 align="center" >${greeting}</h1>
		<hr />
		<h2>${select}</h2>
		<br />
		<table border="1">
	      <thead>
	        <tr>
	          <th align="center">Name</th>
	          <th align="center">Category</th>
	          <th align="center">Quantity</th>
	        </tr>
	      </thead>
	      <tbody>
	        <c:forEach items = "${smproductlist}" var="product">
	          <tr>
	            <td width="10%" align="center"><c:out value="${product.name}"/></td>
	            <td width="10%" align="center"><c:out value="${product.category}"/></td>
	            <td width="10%" align="center"><c:out value="${product.quantity}"/></td>
	          </tr>
	        </c:forEach>
	      </tbody>
	    </table>
	   	<br />
	   	<input type="button" onclick="history.back();" value="Back"/>
	</body>
</html>