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

<title>Listado de Turnos</title>
</head>
<body>

	<%@include file="Menu.jsp"%>

	<h1 class="title">Listado de turnos</h1>

	<form action="AddTurno.do" method=post>
		<a href="" class="p-2 bd-highlight"> <input
			class="btn btn-outline-dark" type="submit" name="btnAgregarTurno"
			value="Agregar Turno" style="margin-left: 730px;">
		</a>
	</form>
	<br>
	<br>
	<br>

	<table border="1" id="table_id" datatable="true">

		<thead>
			<tr>
				<td><b>Id</b></td>
				<td><b>Paciente</b></td>
				<td><b>Médico</b></td>
				<td><b>Especialidad</b></td>
				<td><b>Fecha</b></td>
				<td><b>Hora</b></td>
				<td><b>Estado</b></td>
				<td><b></b></td>
				<td><b></b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${turnos}" var="turno">
				<tr>
					<td>${turno.idTurno}</td>
					<td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
					<td>${turno.medico.nombre} ${turno.medico.apellido}</td>
					<td>${turno.medico.especialidad.especialidad}</td>
					<td>${turno.fechaReserva}</td>
					<td>${turno.hora}hs</td>
					<td>${turno.estadoTurno.descripcion}</td>
					<td>
						<form action="editarTurno.do" method="get">
							<input type="hidden" name="id" value="${turno.idTurno}">
							<button type="submit"><i class="fa fa-edit"></i></button>
						</form>
					</td>
					<td>
						<form action="EliminarTurno.do" method="get">
							<input name="id" type="hidden" value="${turno.idTurno}">
							<button type="submit"><i class="fa fa-trash"></i></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

	<c:if test="${not empty success}">
		<div class="success">${success}</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>

</body>
</html>
