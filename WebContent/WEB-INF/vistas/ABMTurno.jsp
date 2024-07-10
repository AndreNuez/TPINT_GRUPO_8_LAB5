<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turno</title>

</head>
<body>
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
					<td><input type="text" name="dni" value="${paciente.dni}">
					</td>
					<td>
						<button type="submit" formaction="buscarPacientePorDni.do"
							class="btn btn-outline-success">Buscar DNI</button>
					</td>
				</tr>

				<!-- Luego de buscar cargar nombre y apellido y el desplegable de espacialidad y medicos -->
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
						style="width: 233px;">
							<option value="">Seleccione una Especialidad</option>
							<c:forEach items="${especialidades}" var="especialidad">
								<option value="${especialidad.id}">${especialidad.nombre}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>Medico</label></td>
					<td><select name="selMedico" id="selMedico"
						style="width: 233px;">
							<option value="">Seleccione un Medico</option>
							<c:forEach items="${medicos}" var="especialidad">
								<option value="${medico.legajo}">${medico.nombre}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>Fecha de Reserva</label></td>
					<td><input type="date" name="txtFechaReserva"
						style="width: 233px"></td>
					<td><button type="button" id="btnBuscarFecha"
							class="btn btn-outline-primary">Buscar Fecha</button></td>
				</tr>
				<tr>
					<td><label>Hora</label></td>
					<td><select name="selHora">
							<!-- Opciones -->
					</select></td>
				</tr>
				<tr>
					<td><label>Observaciones</label></td>
					<td><textarea name="txtObservacion" style="width: 233px;"
							maxlength="1000"></textarea></td>
				</tr>

			</table>
			<div class="button-container">
				<button type="submit" class="btn btn-outline-success btn-spaced"
					name="btnGrabar">Grabar</button>
				<button type="submit" class="btn btn-outline-primary btn-spaced"
					name="btnActualizar">Actualizar</button>
				<button type="submit" class="btn btn-outline-danger btn-spaced"
					name="btnEliminar">Eliminar</button>
			</div>
		</div>
	</form>

	<script>
        function hideErrorMessage() {
            const errorLabel = document.getElementById('errorLabel');
            if (errorLabel) {
                setTimeout(() => {
                    errorLabel.style.display = 'none';
                }, 6000); //6 segundos
            }
        }

        window.onload = hideErrorMessage;
    </script>
</body>
</html>
