<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.Medico"%>
<%@ page import="entidad.PerfilUsuario"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">
<title>Medico</title>
</head>
<body>
	<%
		Usuario usuario = (Usuario) session.getAttribute("user");

		if (request.getSession().getAttribute("user") != null
				&& usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
	%>
	<%@ include file="Menu.jsp"%>

	<form id="formPpal" action="ABMMedico.do" method="post"
		onsubmit="return validarFormulario()">
		<h2 class="title">Alta y Modificacion de Medicos</h2>
		<br>

		<div class="formulario">
			<div>
			<div>
				<c:if test="${error}">
					<label class="error" id="errorLabel"> El Dni ingresado ya existe en la base datos.</label>
				</c:if>
			</div>
				<table>
					<c:if test="${editar}">
						<tr>
							<td><input name="legajo" type="hidden"
								value="${medico.legajo}" readonly></td>
						</tr>
					</c:if>
					
					<tr>
						<td><label>DNI</label></td>
						<c:if test="${not editar}">
							<td><input type="text" name="dni" value="${medico.dni}"
								pattern="^[0-9]{8}$" autofocus
								title="Este campo solo admite un numero de 8 digitos." required></td>
						</c:if>
						<c:if test="${editar}">
							<td><input type="text" name="dni" value="${medico.dni}"
								style="background-color: #f2f2f2" required readonly></td>
						</c:if>
					</tr>
					
					<tr>
						<td><label>Nombre</label></td>
						<td><input type="text" name="nombre" id="nombre"
							pattern="[a-z A-Z]+" value="${medico.nombre}" required></td>
					</tr>
					<tr>
						<td><label>Apellido</label></td>
						<td><input type="text" name="apellido" id="apellido"
							pattern="[a-z A-Z]+" value="${medico.apellido}" required></td>
					</tr>
					<tr>
						<td><label>Especialidad</label></td>
						<td><select name="especialidad.id" style="width: 233px;">
								<c:if test="${editar}">
									<option value="${medico.especialidad.id}">${medico.especialidad.nombre}</option>
								</c:if>
								<c:if test="${error}">
									<option value="${medico.especialidad.id}">${medico.especialidad.nombre}</option>
								</c:if>
								<option value="">Seleccione una Especialidad</option>
								<c:forEach items="${especialidades}" var="especialidad">
									<option value="${especialidad.id}">${especialidad.nombre}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Jornada</label></td>
						<td><select name="jornada.id" style="width: 233px;">
								<c:if test="${editar}">
									<option value="${medico.jornada.id}">${medico.jornada.descripcion}</option>
								</c:if>
								<c:if test="${error}">
									<option value="${medico.jornada.id}">${medico.jornada.descripcion}</option>
								</c:if>
								<option value="">Seleccione una Jornada</option>
								<c:forEach items="${jornadas}" var="jornada">
									<option value="${jornada.id}">${jornada.descripcion}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Sexo</label></td>
						<td><select name="sexo" style="width: 233px;">
								<c:if test="${editar}">
									<option value="${medico.sexo}">${medico.sexo}</option>
								</c:if>
								<option value="X">X</option>
								<option value="F">Femenino</option>
								<option value="M">Masculino</option>
						</select></td>
					</tr>

					<c:if test="${editar}">
						<tr>
							<td><label>Fecha Nacimiento ${formattedDateString}</label></td>
							<td><input type="date" name="fNac" value="${fecNacMed}"
								max="<%= LocalDate.now().toString() %>" style="width: 233px;"
								required></td>
						</tr>
					</c:if>
					<c:if test="${not editar}">
						<tr>
							<td><label>Fecha Nacimiento</label></td>
							<td><input type="date" name="fNac" value="${fecNacMed}"
								max="<%= LocalDate.now().toString() %>" style="width: 233px;"
								required></td>
						</tr>
					</c:if>
					<tr>
						<td><label>Direccion</label></td>
						<td><input type="text" name="direccion" id="direccion"
							value="${medico.direccion}" required></td>
					</tr>
					<tr>
						<td><label>Provincia</label></td>
						<td><select name="selProvincia" id="selProvincia"
							style="width: 233px;" onchange="filtrarLocalidades()">
								<c:if test="${editar}">
									<option value="${medico.provincia.id}">${medico.provincia.nombre}</option>
								</c:if>
								<c:if test="${error}">
									<option value="${medico.provincia.id}">${medico.provincia.nombre}</option>
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
									<option value="${medico.localidad.id}">${medico.localidad.nombre}</option>
								</c:if>
								<c:if test="${error}">
									<option value="${medico.localidad.id}">${medico.localidad.nombre}</option>
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
						<td><input type="email" name="correo"
							pattern=".{1,}@.{1,}\.com.{0,}$" value="${medico.correo}"
							required></td>
					</tr>
					<tr>
						<td><label>Telefono</label></td>
						<td><input type="number" min=10000000 name="telefono"
							value="${medico.telefono}" required></td>
					</tr>
					<c:if test="${editar}">
						<tr>
							<td><input type="hidden" name="usuario.id"
								value="${medico.usuario.id}"></td>
						</tr>
						<tr>
							<td><input type="hidden" name="usuario.perfil"
								value="${medico.usuario.perfil}"></td>
						</tr>
					</c:if>
					<tr>
						<td><label>Usuario</label></td>
						<td><input type="text" name="usuario.nombre" id="usuario"
							value="${medico.usuario.nombre}" required></td>
					</tr>
					<tr>
						<td><label>Password</label></td>
						<c:if test="${not editar}">
							<td><input type="password" name="usuario.password" id="pass"
								value="${medico.usuario.password}" required></td>
						</c:if>
						<c:if test="${editar}">
							<td><input type="text" name="usuario.password" id="pass"
								value="${medico.usuario.password}" required></td>
						</c:if>
					</tr>
					<tr>
						<td><label>Confirmar Password</label></td>
						<c:if test="${not editar}">
							<td><input type="password" name="confirm_pass"
								id="confirm_pass" value="${medico.usuario.password}" required
								oninput="check(this)"></td>
						</c:if>
						<c:if test="${editar}">
							<td><input type="text" name="confirm_pass" id="confirm_pass"
								value="${medico.usuario.password}" required
								oninput="check(this)"></td>
						</c:if>
					</tr>
				</table>
			</div>

			<div class="pt-4 w-25 d-flex justify-content-around">
				<c:if test="${not editar}">
					<input class="btn btn-outline-success" type="submit"
						onclick="return confirm('¿Confirma que desea guardar este medico?')"
						name="btnGrabar" value="Grabar">
				</c:if>
				<c:if test="${editar}">
					<input class="btn btn-outline-primary" type="submit"
						onclick="return confirm('¿Confirma que desea modificar este medico?')"
						name="btnActualizar" value="Actualizar">
				</c:if>
			</div>
		</div>
	</form>
	<script>
        function check(input) {
            if (input.value !== document.getElementById('pass').value) {
                input.setCustomValidity('Las contraseñas no coinciden.');
            } else {
                input.setCustomValidity('');
            }
        };
        
  	   function validarFormulario() {
  	     var Nombre = document.getElementById("nombre").value.trim();
  	     var Apellido = document.getElementById("apellido").value.trim();
  	     var Direccion = document.getElementById("direccion").value.trim();
  	     var Usuario = document.getElementById("usuario").value.trim();
  	     var Password = document.getElementById("pass").value.trim();
  	
  	     if (Nombre === "" || Apellido === "" ||  Direccion === "" ||  Usuario === "" ||  Password === "" ) {
  	       alert("No se pueden guardar espacios. Debe ingresar un valor en todos los campos.");
  	       return false;
  	     }
  	     return true;
  	   };
  	   

 	  
 	  function filtrarLocalidades() {
 	        var provinciaId = document.getElementById('selProvincia').value;
 	        var localidades = document.getElementById('selLocalidad').getElementsByTagName('option');

 	        for (var i = 0; i < localidades.length; i++) {
 	        	localidades[i].style.display = (localidades[i].getAttribute('data-provincia') === provinciaId || provinciaId === '') ? '' : 'none';
 	        }
 	    };
 	    
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
