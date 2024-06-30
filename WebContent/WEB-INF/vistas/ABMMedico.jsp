<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="entidad.Medico"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">
<title>Medico</title>
</head>
<body>
	<%@include file="Menu.jsp"%>

	<form id="formPpal" action="ABMMedico.do" method="post">

		<h2 class="title">Alta y Modificacion de Medicos</h2>
		<br>

		<div class="formulario">

			<div>
				<table>
					<tr>
						<td><label>DNI</label></td>
						<td><input type="number" min="1000000" min="0" name="txtDni"
							value=""></td>
					</tr>
					<tr>
						<td><label>Nombre</label></td>
						<td><input type="text" name="txtNombre" pattern="[a-z A-Z]+"
							value=""></td>
					</tr>
					<tr>
						<td><label>Apellido</label></td>
						<td><input type="text" name="txtApellido"
							pattern="[a-z A-Z]+" value=""></td>
					</tr>
					<tr>
						<td><label>Especialidad</label></td>
						<td><select name="selEspecialidad" style="width: 233px;">
								<option value="">Seleccione una especialidad</option>
								<!-- Iterar sobre la lista de especialidades desde el controlador -->
								<c:forEach items="${especialidades}" var="especialidad">
									<option value="${especialidad.id}">${especialidad.nombre}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Nacionalidad</label></td>
						<td><select name="selNacionalidad" style="width: 233px;">
								<option value="">Seleccione una nacionalidad</option>
								<!-- Iterar sobre la lista de nacionalidades desde el controlador -->
								<c:forEach items="${nacionalidades}" var="nacionalidad">
									<option value="${nacionalidad.idNacionalidad}">${nacionalidad.nacionalidad}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Sexo</label></td>
						<td><select name="selSexo" style="width: 233px;">
								<option value="No indica">No indica</option>
								<option value="Femenino">Femenino</option>
								<option value="Masculino">Masculino</option>
						</select></td>
					</tr>
					<tr>
						<!-- 			<td><label>Jornada</label></td>
				<td><select name="selJornada">
						<%
						
						%>
							<option value="">
								
							</option>
						<%
						%>
				</select></td>
			</tr>
 -->
					<tr>
						<td><label>Fecha Nacimiento</label></td>
						<td><input type="date" name="txtFecNacimiento" value=""
							max="" required style="width: 233px;"></td>
					</tr>

					<tr>
						<!--  
				<td><label>Provincia</label></td>
				<td><select name="selProvincia" Id="selProvincia" style="width: 233px;">
						<%
						
						%>
							<option value="">

							</option>
						<%

						%>
				</select></td>

			</tr>

			<tr>
                <td><label>Localidad</label></td>
                <td><select name="selLocalidad" Id="selLocalidad" style="width: 233px;">
                        <%
                       
                        %>
                        <option value="" provincias="" >
                        	
                        </option>
						
                        <%

                        %>
                </select></td>
-->
					</tr>
					<tr>
						<td><label>Direccion</label></td>
						<td><input type="text" name="txtDireccion" value="" required></td>
					</tr>
					<tr>
						<td><label>Correo Electronico</label></td>
						<td><input type="email" name="txtEmail"
							pattern=".{1,}@.{1,}\.com.{0,}$" value="" required></td>
					</tr>
					<tr>
						<td><label>Telefono</label></td>
						<td><input type="number" name="txtTelefono" min=10000000
							value="" required></td>
					</tr>
					<tr>
						<td><label>UsuarioLogin</label></td>
						<td><input type="text" name="txtUserLogin" value="" required></td>
					</tr>
					<tr>
						<td><label>Password</label></td>
						<td><input type="password" name="txtPassword"
							id="txtPassword" value="" required></td>
					</tr>
					<tr>
						<td><label>Confirmar Password</label></td>
						<td><input type="password" name="txtConfPassword"
							id="txtConfPassword" placeholder="Confirmar Password" value=""
							required></td>
					</tr>
				</table>
			</div>

			<div class="pt-4 w-25 d-flex justify-content-around">

				<% if(equals("add")) {%>
				<input class="btn btn-outline-success" type="submit"
					name="btnGrabar" value="Grabar">
				<% } else { %>
				<input class="btn btn-outline-primary" type="submit"
					name="btnActualizar" value="Grabar"> <input
					class="btn btn-outline-danger" type="submit" name="btnEliminar"
					value="Eliminar">
				<% } %>

			</div>

			<%
		if (request.getAttribute("success") != null) 
		%>
			<div class="success"></div>
			<%

		%>
			<%
		if (request.getAttribute("error") != null) 
		%>
			<div class="error"></div>
			<%

		%>
		</div>
	</form>

</body>
</html>