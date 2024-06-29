<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Principal</title>
</head>

<body>
	
		<%@ include file="Nav.html"%>

	<div class="inicio">
		<h1 class="inicio-content ">Portal de inicio</h1>
	</div>
	
	<%
		Usuario user = (Usuario) request.getAttribute("userLogin");
	%>
	
	El Usuario:
	<input type="text" value=<%=user.getNombre()%> name="id">
	<br> Su pass es:
	<input type="text" value=<%=user.getPassword()%> name="id">
	
</body>
</html>