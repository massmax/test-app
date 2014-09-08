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
						var arrayJson = data.orderItemList;
						var table = '<table border="1" width="100%">';
						for ( var i = arrayJson.length, l = arrayJson.length; i>0; i-- )
						{
				            var order = arrayJson[l-i];
				            var row = '<thead><tr><th align="center">ID</th><th align="center">Name Product</th><th align="center">Name warehouse</th><th align="center">Quantity</th>'
				            var row = '<tr id = "name" width="10%" align="center">';
				            row +='<td width="10%" align="center">'+order.idItem +'</td>';
				            row +='<td width="10%" align="center">'+order.nameProduct +'</td>';
				            row +='<td width="10%" align="center">'+order.nameWarehouse +'</td>';
				            row +='<td width="10%" align="center">'+order.quantity +'</td>';
				            row +='</tr>';
				            table += row;
				        }
				        table += '</table>';
				        $('#myDiv').html(table);
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
	   	 		<c:forEach items="${orderslist}" var="p" varStatus="item"> <option value="${p.id}">${p.id} / ${p.customer.name }</option> </c:forEach>
		  	</select>
		  	<input id="subm" type="button" value="OK" onClick="showFunc()"></input>
		</p>
		<br />
		<p id="rstr" ></p><br />
		<div id = "myDiv"></div>
	   	<br />
		<input type="button" onclick="history.back();" value="Back"/>
	</body>		
</html>