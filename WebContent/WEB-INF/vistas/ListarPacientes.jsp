<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@include file="Datatable_Init.html"%>

<title>Listado de Pacientes</title>
</head>
<body>

	<%@include file="Menu.jsp"%>

	<h1 class="title">Listado de pacientes</h1>

	<%
		
	%>
	<form action="AddPaciente.do" method=post>
		<a href="" class="p-2 bd-highlight"> <input
			class="btn btn-outline-dark" type="submit" name="btnAgregarPaciente"
			value="Agregar Paciente" style="margin-left: 730px;">
		</a>
	</form>
	<%
		
	%>
	<br>
	<br>
	<br>

	<table border="1" id="table_id" datatable="true">

		<thead>
			<tr>
				<td><b>DNI</b></td>
				<td><b>Nombre</b></td>
				<td><b>Apellido</b></td>
				<td><b>Direccion</b></td>
				<td><b>Fecha Nacimiento </b></td>
				<td><b>Correo Electronico</b></td>
				<td><b>Localidad</b></td>
				<td><b></b></td>
				<td><b></b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pacientes}" var="paciente">
				<tr>
					<td>${paciente.dni}</td>
					<td>${paciente.nombre}</td>
					<td>${paciente.apellido}</td>
					<td>${paciente.direccion}</td>
					<td>${paciente.fechaNacimiento}</td>
					<td>${paciente.correoElectronico}</td>
					<td>${paciente.localidad}</td>
					<td><form action="editarPaciente.do" method="get">
                        <input type="hidden" name="id" value="${paciente.dni}">
                        <button type="submit"><i class="fa fa-edit"></i></button>
                    </form></td>
                    <td><form action="EliminarPaciente.do" method="get">
                        <input name="dni" type="hidden" value="${paciente.dni}">
                        <button type="submit"><i class="fa fa-delete"></i></button>
                    </form></td>
				</tr>
			</c:forEach>
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