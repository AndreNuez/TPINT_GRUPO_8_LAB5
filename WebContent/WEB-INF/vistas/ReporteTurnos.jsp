<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						style="width: 233px" value=""></td>
					<td><label>Fecha Fin</label></td>
					<td><input type="date" name="txtFechaFin" style="width: 233px"
						value=""></td>
					<td><button type="submit" formaction="buscarTurnos.do"
							id="btnBuscarTurnos" class="btn btn-outline-primary">Buscar
							Fecha</button></td>
				</tr>
				<c:if test="${exito}">
					<tr>
						<td><label>Porcentaje Presentes: ${porcPresentes} </label></td>
					</tr>
					<tr>
						<td><label>Porcentaje Ausentes: ${porcAusentes} </label></td>
					</tr>
				</c:if>
			</table>
		</div>
	</form>
</body>
</html>