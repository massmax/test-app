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
			var getallproduct = "/warp6/warehouse/" + $( "#sel :selected" ).val( ) +"/getallproduct";
			var getproduct = "/warp6/warehouse/" + $( "#sel :selected" ).val( ) +"/getproduct";
			var getorder = "/warp6/warehouse/" + $( "#sel :selected" ).val( ) + "/getorder";		
			
			document.getElementById('getallproduct').href=getallproduct;
			document.getElementById('getproduct').href=getproduct;
			document.getElementById('getorder').href=getorder;
			
			document.getElementById('getallproduct').innerHTML="View page catalog of warehouse";
			document.getElementById('getproduct').innerHTML="Page views a particular product";
			document.getElementById('getorder').innerHTML="View page order";
		}
		</script>
	</head>
	<body text = "#000000" >
		<h1 align="center" >${greeting}</h1>
		<hr />
		<h2>${select}</h2>
		<p>
	  		<select id="sel"  style="width:180px; height:24px; font-size: 12pt;">
	   	 		<c:forEach items="${listwarehouses}" var="p" varStatus="item"> <option value="${p.id}">${p.name}</option> </c:forEach>
		  	</select>
		  	<input id="subm" type="button" value="OK" onClick="showFunc()"></input>
		</p>
		<br />
	  	<h4><a id="getallproduct" href="#"></a></h4><br />
		<h4><a id="getproduct" href="#"> </a></h4><br />
		<h4><a id="getorder" href="#"> </a></h4><br />
	</body>
</html>