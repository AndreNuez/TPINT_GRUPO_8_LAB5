<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporte Turnos</title>
</head>
<body>
	<%@ include file="Menu.jsp"%>

	<h1 class="title">Reporte Turnos</h1>

	<form id="formTurno" action="ABMTurno.do" method="post">
		<div class="formulario">
			<table>
				<tr>
					<td><label>Fecha Inicio</label></td>
					<td><input type="date" name="txtFechaInicio"
						style="width: 233px" value="${fechaInicioFormatted}"
						max="<%= LocalDate.now().toString() %>" required></td>
				</tr>
				<tr>
					<td><label>Fecha Fin</label></td>
					<td><input type="date" name="txtFechaFin" style="width: 233px"
						value="${fechaFinFormatted}"
						max="<%= LocalDate.now().toString() %>" required></td>

					<td><button type="submit" formaction="buscarTurnos.do"
							id="btnBuscarTurnos" class="btn btn-outline-primary">Buscar
						</button></td>
				</tr>
			</table>
		</div>
	</form>
	<br>
	<c:if test="${exito}">
		<div class="div_center">

			<label>Porcentaje Presentes:</label>
			<div class="progress" role="progressbar" aria-label="Basic example"
				aria-valuenow="${porcPresentes}" aria-valuemin="0"
				aria-valuemax="${total}" style="height: 20px">
				<div class="progress-bar bg-success"
					style="width: ${porcPresentes}%">${porcPresentes}</div>
			</div>
			<br> <label>Porcentaje Ausentes:</label>
			<div class="progress" role="progressbar" aria-label="Basic example"
				aria-valuenow="${porcAusentes}" aria-valuemin="0"
				aria-valuemax="${total}" style="height: 20px">
				<div class="progress-bar bg-success" style="width: ${porcAusentes}%">${porcAusentes}
				</div>
			</div>

		</div>
	</c:if>
	<c:if test="${exito == false}">
		<div class="div_center">
			<label><b>No hay registros para mostrar</b></label>
		</div>
	</c:if>
</body>
</html>