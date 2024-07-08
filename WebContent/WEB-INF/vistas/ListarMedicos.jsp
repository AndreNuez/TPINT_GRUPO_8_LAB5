<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@include file="Datatable_Init.html"%>

<title>Listado de Medicos</title>
</head>
<body>

	<%@include file="Menu.jsp"%>

	<h1 class="title">Listado de medicos</h1>

	<form action="AddMedico.do" method=post>
		<input class="btn btn-outline-dark" type="submit"
			name="btnAgregarMedico" value="Agregar Medico"
			style="margin-left: 730px;">
	</form>
	<br>
	<br>
	<br>

	<table border="1" id="table_id" datatable="true">

		<thead>
			<tr>
				<td><b>Legajo</b></td>
				<td><b>Nombre</b></td>
				<td><b>Apellido</b></td>
				<td><b>Sexo</b></td>
				<td><b>Especialidad </b></td>
				<td><b>Correo Electronico</b></td>
				<td><b>Direccion</b></td>
				<td><b></b></td>
				<td><b></b></td>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${medicos}" var="medico">
				<tr>
					<td>${medico.legajo}</td>
					<td>${medico.nombre}</td>
					<td>${medico.apellido}</td>
					<td>${medico.sexo}</td>
					<td>${medico.especialidad.nombre}</td>
					<td>${medico.correo}</td>
					<td>${medico.direccion}</td>

					<td><form action="EditarMedico.do" method="get">
							<input type="hidden" name="legajo" value="${medico.legajo}">
							<button type="submit">
								<i class="fa fa-edit"></i>
							</button>
						</form></td>

					<td><form action="EliminarMedico.do" method="get">
							<input name="legajo" type="hidden" value="${medico.legajo}">
							<button type="submit">
								<i class="fa fa-delete"></i>
							</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

	<%
		
	%>
	<div class="success"></div>
	<%
		
	%>
	<%
		
	%>
	<div class="error"></div>
	<%
		
	%>

</body>
</html>
