<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
		<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.min.js" ></script>
		<script type="text/javascript" >
		function showFunc ( )
		{
			$.ajax(
			{
				url: document.location.href + "/" + $( "#sel :selected" ).val( ),
				type: "GET",
				contentType:"application/json; charset=utf-8",
				dataType:"json",
				success: function( data )
				{
					if ( data.status == 'ok' )
					{
						$( "#rstr" ).html( data.status );
						$( "#name" ).html( data.smproduct.name );
						$( "#category" ).html( data.smproduct.category );
						$( "#description" ).html( data.smproduct.description );
						$( "#quantity" ).html( data.smproduct.quantity );
						$( "#price" ).html( data.smproduct.price );
						$( "#discount" ).html( data.smproduct.discount );
					}
					else
					{
						$( "#rstr" ).html( "&lt;Server Error&gt;" );
					}
				}
			} );
		}
		</script>
	</head>
	<body text = "#000000" >
		<h1 align="center" >${greeting}</h1>
		<hr />
		<h2>${select}</h2>
		<p>
	  		<select id="sel"  style="width:180px; height:24px; font-size: 12pt;">
	   	 		<c:forEach items="${productslist}" var="p" varStatus="item"> <option value="${p.id}">${p.name} / ${p.description}</option> </c:forEach>
		  	</select>
		  	<input id="subm" type="button" value="OK" onClick="showFunc()"></input>
		</p>
		<br />
		<p id="rstr" ></p><br />
		<table id="table" border="1" width="100%">
	      <thead>
	        <tr>
	          <th align="center">Name</th>
	          <th align="center">Category</th>
	          <th align="center">Description</th>
	          <th align="center">Quantity</th>
	          <th align="center">Price</th>
	          <th align="center">Discount</th>
	        </tr>
	      </thead>
	      <tbody>
	          <tr>
	            <td id = "name" width="10%" align="center"></td>
	            <td id = "category" width="10%" align="center"></td>
	            <td id = "description" width="10%" align="center"></td>
	            <td id = "quantity" width="10%" align="center"></td>
	            <td id = "price" width="10%" align="center"></td>
	            <td id = "discount" width="10%" align="center"></td>
	          </tr>
	      </tbody>
	    </table>
	   	<br />
		<input type="button" onclick="history.back();" value="Back"/>
	</body>		
</html>