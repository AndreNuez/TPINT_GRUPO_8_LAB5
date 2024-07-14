 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporte Especialidades </title>
</head>
<body>
	<%@ include file="Menu.jsp"%>

	<h1 class="title">Reporte Especialidades</h1>

	<form id="formTurno" action="reporteEspecialidades.do" method="post">
		<div class="formulario">
			<table>
				<tr>
					<td><label>Fecha Inicio</label></td>
					<td><input type="date" name="txtFechaInicio" style="width: 233px" value="${fechaInicioFormatted}" required></td>
				</tr>
				<tr>
					<td><label>Fecha Fin</label></td>
					<td><input type="date" name="txtFechaFin" style="width: 233px" value="${fechaFinFormatted}" required></td>
					<td><button type="submit" formaction="reporteEspecialidades.do" id="btnBuscarTurnos" class="btn btn-outline-primary">Buscar </button></td>
				</tr>
			</table>
		</div>
	</form>
	<br>	
	    <c:if test="${exito}">
        <div class="div_center">
        	<label style="display: block; text-align: center;"><b>Cantidad total de turnos en el periodo seleccionado: ${total}</b></label>
        	<br>
        	<br>
            <c:forEach var="item" items="${turnosPorEspecialidad}">
                <label>${item.especialidadNombre}: ${item.cantidadTurnos}</label>
                <div class="progress" role="progressbar" aria-label="Porcentaje de Turnos por Especialidad"
                    aria-valuenow="${item.porcentaje}" aria-valuemin="0" aria-valuemax="100" style="height: 20px">
                    <div class="progress-bar bg-success" style="width: ${item.porcentaje}%">
                        ${item.porcentaje}%
                    </div>
                </div>
                <br>
            </c:forEach>
        </div>
    	</c:if>
		<c:if test="${exito == false}">
        <div class="div_center">
            <label style="display: block; text-align: center;"><b>No hay turnos en las fechas seleccionadas</b></label>
        </div>
   	 </c:if>
</body>
</html>