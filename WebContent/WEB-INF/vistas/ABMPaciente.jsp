<%@ page import="entidad.Paciente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.time.LocalDate" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">

<title>Paciente</title>
</head>
<body>
	<%@include file="Menu.jsp"%>

	<form id="formPpal" action="ABMPaciente.do" method="post">

		<h2 class="title">Alta y Modificacion de Pacientes</h2>
		<br>

		<div class="formulario">

			<div>
				<table>
					
					<tr>
						<c:if test="${editar}">
							<td><input name="id" type="hidden" value="${paciente.id}"
								readonly></td>
						</c:if>
					</tr>
					<tr>
						<td><label>DNI</label></td>
						<c:if test="${not editar}">
							<td><input type="text" name="dni" value="${paciente.dni}"
								required></td>
						</c:if>
						<c:if test="${editar}">
							<td><input type="text" name="dni" value="${paciente.dni}"
								required readonly></td>
						</c:if>
					</tr>
					<tr>
						<td><label>Nombre</label></td>
						<td><input type="text" name="nombre" pattern="[a-z A-Z]+"
							value="${paciente.nombre}" required></td>
					</tr>
					<tr>
						<td><label>Apellido</label></td>
						<td><input type="text" name="apellido" pattern="[a-z A-Z]+"
							value="${paciente.apellido}" required></td>
					</tr>
					<tr>
						<td><label>Fecha Nacimiento</label></td>
						<td><input type="date" name="fechaNacimiento"
							max="<%= LocalDate.now().toString() %>"
							value="${paciente.fechaNacimiento}" required
							style="width: 233px;"></td>
					</tr>
					<tr>
						<td><label>Direccion</label></td>
						<td><input type="text" name="direccion"
							value="${paciente.direccion}" required></td>
					</tr>
					<tr>
						<td><label>Provincia</label></td>
						<td><select name="provincia.id" id="provincia"
							style="width: 233px;">
							<c:if test="${editar}">
								<option value="${paciente.provincia.id}">${paciente.provincia.nombre}</option>
							</c:if>
								<option value="">Seleccione una Provincia</option>
								<c:forEach items="${provincias}" var="provincia">
									<option value="${provincia.id}">${provincia.nombre}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Localidad</label></td>
						<td><select name="localidad.id" id="localidad"
							style="width: 233px;">
							<c:if test="${editar}">
								<option value="${paciente.localidad.id}">${paciente.localidad.nombre}</option>
							</c:if>
								<option value="">Seleccione una Localidad</option>
								<c:forEach items="${localidades}" var="localidad">
									<option value="${localidad.id}">${localidad.nombre}</option>
								</c:forEach>
						</select></td>

					</tr>
					<tr>
						<td><label>Correo Electronico</label></td>
						<td><input type="email" name="correoElectronico"
							pattern=".{1,}@.{1,}\.com.{0,}$"
							value="${paciente.correoElectronico}" required></td>
					</tr>
					<tr>
						<td><label>Telefono</label></td>
						<td><input type="number" min=10000000 name="telefono"
							value="${paciente.telefono}" required></td>
					</tr>
				</table>
			</div>

			<div class="pt-4 w-25 d-flex justify-content-around">
				<c:if test="${not editar}">
					<input class="btn btn-outline-success" type="submit"
						name="btnGrabar" value="Grabar">
				</c:if>
				<c:if test="${editar}">
					<input class="btn btn-outline-primary" type="submit"
						name="btnActualizar" value="Actualizar">
				</c:if>
			</div>

			<div class="success"></div>
			<%
				
			%>
			<div class="error"></div>
			<%
				
			%>
		</div>
	</form>

</body>

</html>