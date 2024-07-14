<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.PerfilUsuario"%>
<%@ page import="entidad.Turno"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turno</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.1/css/bootstrap.min.css">


</head>

<body>
	<%
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (request.getSession().getAttribute("user") != null
				&& usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
	%>
	<%@include file="Menu.jsp"%>

	<h2 class="title">Alta y Modificacion de Turnos</h2>

	<form id="formTurno" action="ABMTurno.do" method="post">
		<div class="formulario">
			<div>
				<c:if test="${not empty error}">
					<label class="error" id="errorLabel">${error}</label>
				</c:if>
			</div>
			<table>
				<!-- Buscar paciente por DNI -->
				<tr>
					<td><label>DNI Paciente</label></td>
					<td><input type="text" name="dni" value="${paciente.dni}" required></td>
					<td>
						<button type="submit" formaction="buscarPacientePorDni.do"
							class="btn btn-outline-success">Buscar DNI</button>
					</td>
				</tr>

				<!-- Luego de buscar cargar nombre y apellido y el desplegable de espacialidad y medicos -->
				<c:if test="${mostrarCampos}">
				<tr>
					<td><label>Nombre</label></td>
					<td><input type="text" name="txtNombrePaciente"
						id="nombrePaciente" value="${paciente.nombre}" readonly
						style="background-color: #eee;"></td>
				</tr>
				<tr>
					<td><label>Apellido</label></td>
					<td><input type="text" name="txtApellidoPaciente"
						id="apellidoPaciente" value="${paciente.apellido}" readonly
						style="background-color: #eee;"></td>
				</tr>
				<tr>
					<td><label>Especialidad</label></td>
					<td><select name="selEspecialidad" id="selEspecialidad"
						style="width: 233px;" onchange="filtrarMedicos()">
							<option value="">Seleccione una Especialidad</option>
							<c:forEach items="${especialidades}" var="especialidad">
								<option value="${especialidad.id}">${especialidad.nombre}</option>
							</c:forEach>
					</select></td>
				</tr>

				<c:if test="${not hayTurno}">
					<tr>
						<td><label>Medico</label></td>
						<td><select name="selMedico" id="selMedico"
							style="width: 233px;">
								<option value="">Seleccione un Medico</option>
								<c:forEach items="${medicos}" var="medico">
									<option value="${medico.legajo}"
										data-especialidad="${medico.especialidad.id}"
										style="display: none;">${medico.apellido}</option>
								</c:forEach>
						</select></td>
					</tr>
				</c:if>
				<c:if test="${hayTurno}">
					<tr>
						<td><input name="legajo" type="hidden"
							value="${medico.legajo}" readonly></td>
					</tr>
					<tr>
						<td><label>Medico</label></td>
						<td><input type="text" name="txtMedico" id="txtMedico"
							value="${medico.apellido}" readonly
							style="background-color: #eee;"></td>
					</tr>
				</c:if>
				<c:if test="${not empty cantTurnos}">
					<tr>
						<td colspan="3"><label class="error" id="errorLabel">${cantTurnos}</label></td>
					</tr>
				</c:if>
				<tr>
					<td><label>Fecha de Reserva</label></td>
					<td><input type="date" name="txtFechaReserva"
						style="width: 233px" value="${fechaReserva}"
						min="<%=LocalDate.now().plusDays(1) %>" required></td>
					<td><button type="submit" formaction="buscarFecha.do"
							id="btnBuscarFecha" class="btn btn-outline-primary">Buscar
							Fecha</button></td>
				</tr>
				<tr>
					<td><label>Hora</label></td>
					<td><select name="selHora">
							<option value="">Seleccione una hora</option>
							<c:forEach items="${horasTurnos}" var="hora">
								<option value="${hora}">${hora}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>Observaciones</label></td>
					<td><textarea name="txtObservacion" style="width: 233px;"
							maxlength="1000"></textarea></td>
				</tr>
				
			</table>
			<div class="button-container">
				<button type="submit"
					onclick="return confirm('¿Confirma que desea guardar este turno?')"
					class="btn btn-outline-success btn-spaced" name="btnGrabar">Grabar</button>
				<button type="submit"
					onclick="return confirm('¿Confirma que desea guardar este turno?')"
					class="btn btn-outline-primary btn-spaced" name="btnActualizar">Actualizar</button>
			</div>
		</div>
		</c:if>
	</form>


	<script>
    function filtrarMedicos() {
        var especialidadId = document.getElementById('selEspecialidad').value;
        var medicos = document.getElementById('selMedico').getElementsByTagName('option');

        for (var i = 0; i < medicos.length; i++) {
            medicos[i].style.display = (medicos[i].getAttribute('data-especialidad') === especialidadId || especialidadId === '') ? '' : 'none';
        }
    }

    function ocultarMensajeError() {
        const errorLabel = document.getElementById('errorLabel');
        if (errorLabel) {
            setTimeout(() => errorLabel.style.display = 'none', 6000);
        }
    }

    window.onload = () => {
        ocultarMensajeError();
        filtrarMedicos();
    };
</script>

	<%
		} else {
			response.sendRedirect("Access.do");
		}
	%>
</body>

</html>
