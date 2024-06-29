<%@page import="entidad.Usuario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Principal</title>
</head>
<body>
	<%
		Usuario user = (Usuario) request.getAttribute("userLogin");
	%>
	El Usuaraio:
	<input type="text" value=<%=user.getNombre()%> name="id">
	<br> Su pass es:
	<input type="text" value=<%=user.getPassword()%> name="id">
</body>
</html>