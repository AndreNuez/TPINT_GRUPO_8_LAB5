<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.PerfilUsuario"%>
<%@ page import="entidad.Turno"%>
<%@ page import="entidad.EstadoTurno"%>
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
	<%@include file="Menu.jsp"%>
	<%
		if (request.getSession().getAttribute("user") != null) {
	%>

	<h2 class="title">Alta y Modificacion de Turnos</h2>

	<form id="formTurno" action="ABMTurno.do" method="post">
		<div class="formulario">
			<div>
				<c:if test="${not empty error}">
					<label class="error" id="errorLabel">${error}</label>
				</c:if>
			</div>
			<table>
				<tr>
					<c:if test="${!editar}">

						<td><label>DNI Paciente</label></td>
						<td><input type="text" name="dni" value="${paciente.dni}"
							required></td>

						<td>
							<button type="submit" formaction="buscarPacientePorDni.do"
								class="btn btn-outline-success" id="btnBuscarDNI">Buscar
								DNI</button>
						</td>
				</tr>
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
								<c:if test="${hayTurno || hayTurno == false}">
									<option value="${medico.especialidad.id}">${medico.especialidad.nombre}</option>
								</c:if>
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
								<c:if test="${hayTurno || hayTurno == false}">
									<option value="${medico.legajo}">${medico.apellido}</option>
								</c:if>
								<option value="">Seleccione un Medico</option>
								<c:forEach items="${medicos}" var="medico">
									<option value="${medico.legajo}"
										data-especialidad="${medico.especialidad.id}"
										style="display: none;">${medico.apellido}</option>
								</c:forEach>
						</select></td>
					</tr>
					<c:if test="${hayTurno}">
						<tr>
							<td><input name="legajo" type="hidden"
								value="${medico.legajo}" readonly></td>
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
						<td><label>Estado</label></td>
						<td><select name="selEstado">
								<c:forEach items="${EstadoTurno.values()}" var="estado"
									varStatus="loop">
									<option value="${estado}" ${loop.index == 0 ? 'selected' : ''}
										${loop.index != 0 ? 'disabled' : ''}>
										${estado.getEstado()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Observaciones</label></td>
						<td><textarea name="txtObservacion" id="txtObservacion"
								style="width: 233px;" maxlength="1000" disabled></textarea></td>
					</tr>
			</table>
			<div class="button-container">
				<button type="submit"
					onclick="return confirm('¿Confirma que desea guardar este turno?')"
					class="btn btn-outline-success btn-spaced" name="btnGrabar"
					value="Grabar">Grabar</button>

				<input class="btn btn-outline-primary" type="submit"
					name="btnVolver" id="btnVolver" value="Volver"
					formaction="AddTurno.do">
			</div>
		</div>
		</c:if>
		</c:if>

		<!-- Validacion para generacion de turnos que no permita grabar si no se completo todo -->
		<script>
	function validarHoraSeleccionada() {
    // Obtener el valor de la hora seleccionada
    var hora = document.getElementsByName('selHora')[0].value;

    // Verificar que se haya seleccionado una hora válida
    if (hora !== '' && hora !== 'Seleccione una hora') {
        // Hora válida seleccionada
        document.getElementsByName('btnGrabar')[0].disabled = false;
    } else {
        // Hora no seleccionada (seleccionó "Seleccione una hora" o no seleccionó nada)
        document.getElementsByName('btnGrabar')[0].disabled = true;
    }
}

// Llamar a la función de validación cuando se cambie la hora
document.addEventListener('DOMContentLoaded', function() {
    validarHoraSeleccionada(); // Validar al cargar la página por primera vez

    // Escuchar cambios en el desplegable de hora para validar dinámicamente
    var selectHora = document.getElementsByName('selHora')[0];
    selectHora.addEventListener('change', validarHoraSeleccionada);

    // Llamar a validarHoraSeleccionada después de que se cargue la página
    validarHoraSeleccionada();
});

document.addEventListener('DOMContentLoaded', function() {
    var btnVolver = document.getElementById('btnVolver');
    var fechaReserva = document.getElementsByName('txtFechaReserva')[0];

    btnVolver.addEventListener('click', function() {
        fechaReserva.removeAttribute('required');
    });
});

<c:if test="${mostrarCampos}">

document.addEventListener('DOMContentLoaded', function() {
    var dniInput = document.getElementsByName('dni')[0]; // Obtener el campo de DNI (asumiendo que es único)
    dniInput.readOnly = true; // Hacer el campo de DNI solo de lectura
    dniInput.style.backgroundColor = '#eee'; // Cambiar color de fondo a gris

    // Agregar manejo de evento al formulario para enviar el campo DNI correctamente
    var formTurno = document.getElementById('formTurno');
    formTurno.addEventListener('submit', function(event) {
        // Habilitar temporalmente el campo DNI para que su valor se envíe con el formulario
        dniInput.disabled = false;
        // Permitir que el formulario se envíe
        return true;
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var dniInput = document.getElementsByName('dni')[0]; // Obtener el campo de DNI (asumiendo que es único)
    dniInput.disabled = true;
    dniInput.style.backgroundColor = '#eee'; // Cambiar color de fondo a gris
    
    var btnBuscarDNI = document.getElementById('btnBuscarDNI');
    btnBuscarDNI.disabled = true; // Deshabilitar el botón "Buscar DNI"
});

</c:if>
</script>

		<c:if test="${editar}">
			<!-- Luego de buscar cargar nombre y apellido y el desplegable de espacialidad y medicos -->
			<c:if test="${mostrarCampos}">
				<td><label>DNI Paciente</label></td>
				<td><input type="text" name="dni" value="${turno.paciente.dni}"
					required readonly style="background-color: #f2f2f2;"></td>
				<tr>
					<td><input name="legajo" type="hidden"
						value="${turno.medico.legajo}" readonly></td>
				</tr>
				<tr>
					<td><input name="id" type="hidden" value="${turno.id}"
						readonly></td>
				</tr>

				<tr>
					<td><label>Nombre</label></td>
					<td><input type="text" name="txtNombrePaciente"
						id="nombrePaciente" value="${turno.paciente.nombre}" readonly
						style="background-color: #eee;"></td>
				</tr>
				<tr>
					<td><label>Apellido</label></td>
					<td><input type="text" name="txtApellidoPaciente"
						id="apellidoPaciente" value="${turno.paciente.apellido}" readonly
						style="background-color: #eee;"></td>
				</tr>

				<c:if test="${not hayTurno}">
					<tr>
						<td><label for="txtMedico">Médico</label></td>
						<td><input type="text" name="txtMedico" id="txtMedico"
							value="${turno.medico.apellido}" readonly
							style="width: 150px; background-color: #eee; padding: 5px;">

						</td>
					</tr>
					<tr>

						<td><label for="txtEspecialidad">Especialidad</label></td>
						<td><input type="text" name="txtEspecialidad"
							id="txtEspecialidad" value="${turno.medico.especialidad.nombre}"
							readonly
							style="width: 150px; background-color: #eee; padding: 5px;">
						</td>

					</tr>

				</c:if>
				<c:if test="${hayTurno}">
					<tr>
						<td><input name="legajo" type="hidden"
							value="${turno.medico.legajo}" readonly></td>
					</tr>
					<tr>
						<td><label>Medico</label></td>
						<td><input type="text" name="txtMedico" id="txtMedico"
							value="${turno.medico.apellido}" readonly
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
					<td><input type="text" name="txtFechaReserva"
						style="width: 233px; padding: 5px; background-color: #f2f2f2; color: #888;"
						value="${turno.fecha}" required readonly></td>
				</tr>
				<tr>
					<td><label for="txtHora">Hora</label></td>
					<td><input type="text" name="selHora" id="txtHora"
						value="${turno.hora}" required readonly
						style="width: 100px; padding: 5px; background-color: #f2f2f2; color: #888;">
					</td>
				<tr>
					<td><label>Estado</label></td>
					<td><select name="selEstado">
							<c:forEach items="${EstadoTurno.values()}" var="estado"
								varStatus="loop">
								<option value="${estado}"
									${estado == EstadoTurno.PENDIENTE ? 'disabled' : ''}
									${loop.index == 0 ? 'selected' : ''}>
									${estado.getEstado()}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
				<tr>
					<td><label>Observaciones</label></td>
					<td><textarea name="txtObservacion" style="width: 233px;"
							maxlength="1000">${turno.observacion}</textarea></td>
				</tr>
		</tr>
		</table>
		<div class="button-container">
			<input class="btn btn-outline-primary" type="submit"
				onclick="return confirm('¿Confirma que desea modificar este turno?')"
				name="btnActualizar" id="btnActualizar" value="Actualizar">
			<input class="btn btn-outline-primary" type="submit" name="btnVolver"
				id="btnVolver" value="Volver">
		</div>
		</div>
		</c:if>
		</c:if>
	</form>
	<!-- Validacion para edicion de turnos que no permita grabar si no se completo el estatys -->

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
    
    window.onload = function() {
        var selEstado = document.querySelector('select[name="selEstado"]');
        var btnActualizar = document.getElementById('btnActualizar');
        var txtObservacion = document.querySelector('textarea[name="txtObservacion"]');

        // Función para actualizar el estado del botón y la caja de texto de observación
        function actualizarEstado() {
            var estadoSeleccionado = selEstado.value;
            if (estadoSeleccionado === "<%=EstadoTurno.PENDIENTE%>" || estadoSeleccionado === "<%=EstadoTurno.AUSENTE%>") {
                txtObservacion.disabled = true;
            } 
            
            if (estadoSeleccionado === "<%=EstadoTurno.PRESENTE%>" ){
                btnActualizar.disabled = false;
                txtObservacion.disabled = false;
            }
            
            if(estadoSeleccionado === "<%=EstadoTurno.AUSENTE%>" ){
            	btnActualizar.disabled = false;
                txtObservacion.value = "";
            }
        }

        // Ejecutar la función al cargar la página
        actualizarEstado();

        // Escuchar cambios en el select de estado
        selEstado.addEventListener('change', function() {
            actualizarEstado();
        });
        
        
    }
</script>

	<%
		} else {
			response.sendRedirect("login.do");

		}
	%>
</body>

</html>
