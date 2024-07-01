<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<td><label>Nombre</label></td>
						<td><input type="text" name="nombre" pattern="[a-z A-Z]+"
							value="" required></td>
					</tr>
					<tr>
						<td><label>Apellido</label></td>
						<td><input type="text" name="apellido" pattern="[a-z A-Z]+"
							value="" required></td>
					</tr>
					<tr>
			        <td><label>Especialidad</label></td>
						<td><select name="especialidad.id" style="width: 233px;">
								<option value="">Seleccione una Especialidad</option>
								<c:forEach items="${especialidades}" var="especialidad">
									<option value="${especialidad.id}">${especialidad.nombre}</option>
								</c:forEach>

						</select></td>
					</tr>
 
					<tr>
						<td><label>Sexo</label></td>
						<td><select name="sexo" style="width: 233px;">
								<option value="X">X</option>
								<option value="F">Femenino</option>
								<option value="M">Masculino</option>
						</select></td>
					</tr>

					<tr>
						<td><label>Fecha Nacimiento</label></td>
						<td><input type="date" name="fNac" value="" max="" required
							style="width: 233px;"></td>
					</tr>

					<tr>
						<!-- 
					<tr>

						
				<td><label>Provincia</label></td>
				<td><select name="selProvincia"  style="width: 233px;" Id="selProvincia">
						<%%>
							<option value="" >
								
							</option>
						<%%>
				</select></td>
			</tr>

						<!--  		<tr>
                <td><label>Localidad</label></td>
                <td><select name="selLocalidad"  style="width: 233px;" Id="selLocalidad">
                        <%%>
                        <option value="" provincias="" >
                        	
                        </option>
						
                        <%%>
                </select></td>

					</tr>
-->
					<tr>
						<td><label>Direccion</label></td>
						<td><input type="text" name="direccion" value="" required></td>
					</tr>
					<tr>
						<td><label>Localidad</label></td>
						<td><input type="text" name="localidad" value="" required></td>
					</tr>

					<tr>
						<td><label>Correo Electronico</label></td>
						<td><input type="email" name="correo"
							pattern=".{1,}@.{1,}\.com.{0,}$" value="" required></td>
					</tr>
					<tr>
						<td><label>Telefono</label></td>
						<td><input type="number" min=10000000 name="telefono"
							value="" required></td>
					</tr>
				</table>
			</div>

			<div class="pt-4 w-25 d-flex justify-content-around">

				<%
					
				%>
				<input class="btn btn-outline-success" type="submit"
					name="btnGrabar" value="Grabar">
				<%
					
				%>
				<input class="btn btn-outline-primary" type="submit"
					name="btnActualizar" value="Actualizar"> <input
					class="btn btn-outline-danger" type="submit" name="btnEliminar"
					value="Eliminar">
				<%
					
				%>

			</div>


			<div class="success"></div>

			<%
				
			%>
			<div class="error"></div>
			<%
				
			%>
		</div>
	</form>


</body>
</html>