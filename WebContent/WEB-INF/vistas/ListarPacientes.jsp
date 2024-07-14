<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entidad.Usuario" %>
<%@ page import="entidad.PerfilUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="./css/estilo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@include file="Datatable_Init.html"%>
    <title>Listado de Pacientes</title>
    <style>
        .success, .error {
            padding: 10px;
            margin: 10px 0;
            text-align: center; 
            display: flex;
            justify-content: center;
            align-items: center; 
        }
        .fade-out {
            transition: opacity 1s ease-out;
            opacity: 0;
        }
    </style>
</head>
<body>
	<%
		Usuario usuario = (Usuario) session.getAttribute("user");
	%>
	<%@include file="Menu.jsp"%>

    <h1 class="title">Listado de pacientes</h1>

    <form action="AddPaciente.do" method="post">
		<%
			if (usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
		%>
		<input class="btn btn-outline-dark" type="submit" name="btnAgregarPaciente" value="Agregar Paciente" style="margin-left: 730px;">
		<%
			}
		%>
	</form>
    <br><br><br>
    
    <c:if test="${confirmacion}">
        <div class="success" id="confirmacion">
            <h4>Paciente agregado con exito</h4>
        </div>
    </c:if>
    <c:if test="${modificacion}">
        <div class="success" id="modificacion">
            <h4>Paciente modificado con exito</h4>
        </div>
    </c:if>
    <c:if test="${eliminacion}">
        <div class="error" id="eliminacion">
            <h4>Paciente eliminado con exito</h4>
        </div>
    </c:if>

    <table border="1" id="table_id" datatable="true">
        <thead>
            <tr>
                <td><b>DNI</b></td>
                <td><b>Nombre</b></td>
                <td><b>Apellido</b></td>
                <td><b>Direccion</b></td>
                <td><b>Fecha Nacimiento</b></td>
                <td><b>Correo Electronico</b></td>
                <td><b>Provincia</b></td>
                <td><b>Localidad</b></td>
                <td><b></b></td>
                <td><b></b></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pacientes}" var="paciente">
                <tr>
                    <td>${paciente.dni}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.apellido}</td>
                    <td>${paciente.direccion}</td>
                    <td>${paciente.fechaNacimiento}</td>
                    <td>${paciente.correoElectronico}</td>
                    <td>${paciente.provincia.nombre}</td>
                    <td>${paciente.localidad.nombre}</td>
                    <td>
                        <form action="EditarPaciente.do" method="get">
                            <input type="hidden" name="dni" value="${paciente.dni}">
							<%
								if (usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
							%>
							<button type="submit"><i class="fa fa-edit"></i></button>
							<%
								}
							%>
						</form>
                    </td>
                    <td>
                        <form action="EliminarPaciente.do" method="get">
                            <input name="dni" type="hidden" value="${paciente.dni}">
                            <%
								if (usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
							%>
                            <button type="submit" onclick="return confirm('¿Confirma que desea eliminar este paciente?')">
                            <%
								}
							%>
                               <i class="fa fa-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script>
        // Function to fade out and hide the message
        function fadeOutEffect(targetId) {
            const fadeTarget = document.getElementById(targetId);
            if (fadeTarget) {
                fadeTarget.classList.add('fade-out');
                setTimeout(() => fadeTarget.style.display = 'none', 1000); // delay to match CSS transition
            }
        }

        // Set timeouts to fade out the confirmation messages after 3 seconds
        window.onload = function() {
            setTimeout(() => fadeOutEffect('confirmacion'), 3000);
            setTimeout(() => fadeOutEffect('modificacion'), 3000);
            setTimeout(() => fadeOutEffect('eliminacion'), 3000);
        }
    </script>
</body>
</html>
