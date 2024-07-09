<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">
<title>Turno</title>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<tr>
		<td><form action="buscarPacientePorDni.do" method="get">
				<input name="dni" type="text" value="">
				<button type="submit">
					<i class="btn btn-outline-success"></i>
				</button>
			</form></td>
	<tr>
		<form id="formTurno" action="ABMTurno.do" method="post">
			<h2 class="title">Alta y Modificacion de Turnos</h2>

			<div class="formulario">


				<div>
					<table>
						<tr>
							<td><label>DNI Paciente</label></td>
							<td><input type="text" name="dni"></td>
							<td><button type="button" id="btnBuscarDni"
									class="btn btn-outline-success">Buscar DNI</button></td>
						</tr>

						<tr>
							<td><label>Nombre</label></td>
							<td><input type="text" name="txtNombrePaciente"
								id="nombrePaciente" value="<c:out value='${paciente.nombre}'/>"
								readonly style="background-color: #eee;"></td>
						</tr>
						<tr>
							<td><label>Apellido</label></td>
							<td><input type="text" name="txtApellidoPaciente"
								id="apellidoPaciente"
								value="<c:out value='${paciente.apellido}'/>" readonly
								style="background-color: #eee;"></td>
						</tr>
						<tr>
							<td><label>Especialidad</label></td>
							<td><select name="selEspecialidad" id="selEspecialidad"
								style="width: 233px;"
								${not empty turno.paciente.dni ? 'disabled' : ''}>
									<option value="">Seleccione una Especialidad</option>

							</select></td>
						</tr>

						<tr>
							<td><label>Medico</label></td>
							<td><select name="selMedico" id="selMedico"
								style="width: 233px;">
									<option value="">Seleccione un Medico</option>

							</select></td>
						</tr>
						<tr>
							<td colspan="2"><label style="font-weight: bold;"
								id="lblJornada"></label></td>
						</tr>

						<tr>
							<td><label>Fecha de Reserva</label></td>
							<td><input type="date" name="txtFechaReserva"
								style="width: 233px"></td>
							<td><button type="button" id="btnBuscarFecha"
									class="btn btn-outline-primary" style="display:">Buscar
									Fecha</button></td>
						</tr>


						<tr>
							<td><label>Hora</label></td>

							<td><select name="selHora">

							</select></td>

						</tr>


						<tr>
							<td><label>Observaciones</label></td>
							<td><textarea name="txtObservacion" style="width: 233px;"
									maxlength="1000"></textarea></td>
						</tr>

					</table>
				</div>

				<div class="pt-4 w-25 d-flex justify-content-around">

					<button type="submit" class="btn btn-outline-success"
						name="btnGrabar">Grabar</button>

					<button type="submit" class="btn btn-outline-primary"
						name="btnActualizar">Actualizar</button>
					<button type="submit" class="btn btn-outline-danger"
						name="btnEliminar">Eliminar</button>

				</div>


			</div>
		</form>

		<!-- <script type="text/javascript">
		document.getElementById('selEspecialidad').addEventListener('change',
				filtrarDesplegableB);
		document.getElementById('selMedico').addEventListener('change',
				mostrarJornada);

		function filtrarDesplegableB() {
			const valorSeleccionado = document
					.getElementById('selEspecialidad').value;
			const opcionesMedico = document.getElementById('selMedico').options;

			for (let i = 0; i < opcionesMedico.length; i++) {
				const option = opcionesMedico[i];
				if (option.getAttribute('data-especialidad') === valorSeleccionado) {
					option.style.display = 'block';
				} else {
					option.style.display = 'none';
				}
			}

			mostrarJornada();
		}

		function mostrarJornada() {
			const medicoSeleccionado = document.getElementById('selMedico').selectedOptions[0];
			const descripcionJornada = medicoSeleccionado
					.getAttribute("data-jornada");
			document.getElementById("lblJornada").innerText = descripcionJornada;
		}

		filtrarDesplegableB();
	</script> -->
		<!-- <script type="text/javascript">
		document
				.getElementById('btnBuscarDni')
				.addEventListener(
						'click',
						function() {
							const dni = document.getElementById('dni').value
									.trim(); // Utiliza trim() para eliminar espacios en blanco al inicio y al final

							// Muestra el valor capturado en la consola para depuraci�n
							console.log("Valor de DNI capturado:", dni);

							if (dni) {
								window.location.href = `buscarPacientePorDni.do?dni=${dni}`;
							} else {
								console.log("DNI est� vac�o");
							}
						});
	</script> -->
</body>
</html>
