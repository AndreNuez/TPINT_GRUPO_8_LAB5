<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@include file="estilo.css" %>
</style>
<title>Paciente</title>
</head>
<body>
	<%@include file="Menu.jsp"%>

	<form id="formPpal" action="servletPaciente" method="post">

		<h2 class="title">Alta y Modificacion de Pacientes</h2>
		<br>
	
		<div class="formulario">
		
		<div>
		<table>
			<tr>
                <td><label>DNI</label></td>
                <td><input type="number" min=1000000 name="txtDni" value="" required></td>
            </tr>			
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="txtNombre" pattern="[a-z A-Z]+" value="" required></td>
			</tr>
			<tr>
				<td><label>Apellido</label></td>
				<td><input type="text" name="txtApellido" pattern="[a-z A-Z]+" value="" required></td>
			</tr>
			<tr>
				<td><label>Nacionalidad</label></td>
				<td><select name="selNacionalidad" style="width: 233px;">
				  <option value="">Seleccione una nacionalidad</option>
							<c:forEach items="${nacionalidades}" var="nacionalidad">
                                    <option value="${nacionalidad.idNacionalidad}">${nacionalidad.nacionalidad}</option>
                                </c:forEach>
						
				</select></td>
			</tr>
			<tr>
				<td><label>Sexo</label></td>
				<td><select name="selSexo"  style="width: 233px;">
						<option value="No indica">No indica</option>
						<option value="Femenino" >Femenino</option>
						<option value="Masculino" >Masculino</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Fecha Nacimiento</label></td>
				<td><input type="date" name="txtFecNacimiento" value=""
					max="" required  style="width: 233px;"></td>
			</tr>

			<tr>
<!-- 
				<td><label>Provincia</label></td>
				<td><select name="selProvincia"  style="width: 233px;" Id="selProvincia">
						<%
						
						%>
							<option value="" >
								
							</option>
						<%
						
						%>
				</select></td>
			</tr>
-->	
<!--  		<tr>
                <td><label>Localidad</label></td>
                <td><select name="selLocalidad"  style="width: 233px;" Id="selLocalidad">
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
				<td><input type="email" name="txtEmail" pattern=".{1,}@.{1,}\.com.{0,}$" value="" required></td>
			</tr>
			<tr>
				<td><label>Telefono</label></td>
				<td><input type="number" min=10000000 name="txtTelefono" value="" required></td>
			</tr>
		</table>
		</div>
		
		<div  class="pt-4 w-25 d-flex justify-content-around" >

				<% %>
					<input class="btn btn-outline-success" type="submit" name="btnGrabar" value="Grabar">
				<%  %>
					<input class="btn btn-outline-primary" type="submit" name="btnActualizar" value="Grabar">
					<input class="btn btn-outline-danger" type="submit" name="btnEliminar" value="Eliminar">
				<%  %>

		</div>

		<%
		
		%>
		<div class="success"> </div>
		<%

		%>
		<%
		
		%>
		<div class="error"></div>
		<%

		%>
		</div>
	</form>
	
	
</body>
</html>