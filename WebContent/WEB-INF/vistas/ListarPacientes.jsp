<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@include file="estilo.css" %>
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@include file="Datatable_Init.html" %>

<title>Listado de Pacientes</title>
</head>
<body>
	
	<%@include file="Menu.jsp"%>

	<h1  class="title">Listado de pacientes</h1>
 
 	<%
		
	%>	
	
	<a href=""class="p-2 bd-highlight">
		<input class="btn btn-outline-dark" type="submit" name="btnAgregarPaciente" value="Agregar Paciente" style="margin-left: 730px;">
	</a>
	
	<%
		
	%>
	<br><br><br>

	<table border="1" id="table_id" datatable="true">
	
	<thead>	
		<tr>
			<td><b>DNI</b></td>
			<td><b>Nombre</b></td>
			<td><b>Apellido</b></td>
			<td><b>Sexo</b></td>
			<td><b>Fecha Nacimiento	</b></td>
			<td><b>Correo Electronico</b></td>
			<td><b>Direccion</b></td>
			<td><b></b></td>
		</tr>
	</thead>	
	<tbody>
		<%  %>
	         <tr>
	            <td>42589741</td>
                <td>Tomas</td>
                <td>Mate</td>
              	<td>Masculino</td>
                <td>25/07/1998</td>
                <td>tomasmate@gmail.com</td>
                <td>Direccion Tomas</td>
	<%
	
			
	%>	
                <td><a href=""><i class="fa fa-edit"></i></a></td>            
            </tr>
    <%
		
	%>
          <% %>
	</tbody>

	</table>
	
			<% %>
			<div class="success"></div>
		<%  %>
		<% %>
			<div class="error"></div>
		<%  %>


</body>
</html>