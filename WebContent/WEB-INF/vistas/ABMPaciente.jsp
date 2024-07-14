<%@ page import="entidad.Paciente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.PerfilUsuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">
<title>Paciente</title>
</head>
<body>

	<%
		Usuario usuario = (Usuario) session.getAttribute("user");

		if (request.getSession().getAttribute("user") != null
				&& usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
	%>
	<%@ include file="Menu.jsp"%>

	<form id="formPpal" action="ABMPaciente.do" method="post"
		onsubmit="return validarFormulario()">
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
								pattern="^[0-9]{8}$" autofocus
								title="Este campo solo admite un numero de 8 digitos." required></td>
						</c:if>
						<c:if test="${editar}">
							<td><input type="text" name="dni" value="${paciente.dni}"
								style="background-color: #f2f2f2" required readonly></td>
						</c:if>
					</tr>
					<tr>
						<td><label>Nombre</label></td>
						<td><input type="text" name="nombre" id="nombre"
							pattern="[a-z A-Z]+" value="${paciente.nombre}" required></td>
					</tr>
					<tr>
						<td><label>Apellido</label></td>
						<td><input type="text" name="apellido" id="apellido"
							pattern="[a-z A-Z]+" value="${paciente.apellido}" required></td>
					</tr>
					<c:if test="${editar}">
						<tr>
							<td><label>Fecha Nacimiento</label></td>
							<td><input type="date" name="fechaNacimiento"
								max="<%= LocalDate.now().toString() %>"
								value="${fecNacPac}" required
								style="width: 233px;"></td>
						</tr>
					</c:if>
					<c:if test="${not editar}">
						<tr>
							<td><label>Fecha Nacimiento</label></td>
							<td><input type="date" name="fechaNacimiento"
								max="<%= LocalDate.now().toString() %>"
								value="${paciente.fechaNacimiento}" required
								style="width: 233px;"></td>
						</tr>
					</c:if>
					<tr>
						<td><label>Direccion</label></td>
						<td><input type="text" name="direccion" id="direccion"
							value="${paciente.direccion}" required></td>
					</tr>
					<tr>
						<td><label>Provincia</label></td>
						<td><select name="selProvincia" id="selProvincia"
							style="width: 233px;" onchange="filtrarLocalidades()">
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
						<td><select name="selLocalidad" id="selLocalidad"
							style="width: 233px;">

								<c:if test="${editar}">
									<option value="${paciente.localidad.id}">${paciente.localidad.nombre}</option>
								</c:if>
								<option value="">Seleccione una Localidad</option>
								<c:forEach items="${localidades}" var="localidad">
									<option value="${localidad.id}"
										data-provincia="${localidad.provincia.id}"
										style="display: none;">${localidad.nombre}</option>
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
						onclick="return confirm('¿Confirma que desea guardar este paciente?')"
						name="btnGrabar" value="Grabar">
				</c:if>
				<c:if test="${editar}">
					<input class="btn btn-outline-primary" type="submit"
						onclick="return confirm('¿Confirma que desea modificar este paciente?')"
						name="btnActualizar" value="Actualizar">
				</c:if>
			</div>
		</div>
	</form>
	<script>
	  function validarFormulario() {
	    var Nombre = document.getElementById("nombre").value.trim();
	    var Apellido = document.getElementById("apellido").value.trim();
	    var Direccion = document.getElementById("direccion").value.trim();
	
	    if (Nombre === "" || Apellido === "" ||  Direccion === "" ) {
	      alert("No se pueden guardar espacios. Debe ingresar un valor en todos los campos.");
	      return false;
	    }
	    return true;
	  }
	  
	  function filtrarLocalidades() {
	        var provinciaId = document.getElementById('selProvincia').value;
	        var localidades = document.getElementById('selLocalidad').getElementsByTagName('option');

	        for (var i = 0; i < localidades.length; i++) {
	        	localidades[i].style.display = (localidades[i].getAttribute('data-provincia') === provinciaId || provinciaId === '') ? '' : 'none';
	        }
	    }
	  window.onload = () => {
	        
	        filtrarLocalidades();
	    };
	</script>
	<%
		} else {
			response.sendRedirect("Access.do");

		}
	%>
</body>
</html>
