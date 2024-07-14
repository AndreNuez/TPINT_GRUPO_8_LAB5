<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="entidad.Usuario" %>
<%@ page import="entidad.PerfilUsuario" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="./css/estilo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="Datatable_Init.html" %>
    <title>Listado de Medicos</title>
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
	<%@ include file="Menu.jsp" %>

    <h1 class="title">Listado de medicos</h1>

    <form action="AddMedico.do" method="post">
		<%
			if (usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
		%>
		<input class="btn btn-outline-dark" type="submit"
			name="btnAgregarMedico" value="Agregar Medico"
			style="margin-left: 730px;">
		<%
			}
		%>
	</form>
    <br><br><br>
    
    <c:if test="${confirmacion}">
        <div class="success" id="confirmacion">
            <h4>Medico agregado con exito</h4>
        </div>
    </c:if>
    <c:if test="${modificacion}">
        <div class="success" id="modificacion">
            <h4>Medico modificado con exito</h4>
        </div>
    </c:if>
    <c:if test="${eliminacion}">
        <div class="error" id="eliminacion">
            <h4>Medico eliminado con exito</h4>
        </div>
    </c:if>
    
    <table border="1" id="table_id" datatable="true">
        <thead>
            <tr>
                <td><b>Legajo</b></td>
                <td><b>Nombre</b></td>
                <td><b>Apellido</b></td>
                <td><b>Sexo</b></td>
                <td><b>Especialidad</b></td>
                <td><b>Correo Electronico</b></td>
                <td><b>Direccion</b></td>
                <td><b>Provincia</b></td>
                <td><b>Localidad</b></td>
                <td><b></b></td>
                <td><b></b></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${medicos}" var="medico">
                <tr>
                    <td>${medico.legajo}</td>
                    <td>${medico.nombre}</td>
                    <td>${medico.apellido}</td>
                    <td>${medico.sexo}</td>
                    <td>${medico.especialidad.nombre}</td>
                    <td>${medico.correo}</td>
                    <td>${medico.direccion}</td>
                    <td>${medico.provincia.nombre}</td>
                    <td>${medico.localidad.nombre}</td>
                    <td>
                        <form action="EditarMedico.do" method="get">
                            <input type="hidden" name="legajo" value="${medico.legajo}">
							<%
								if (usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
							%>
							<button type="submit">
								<i class="fa fa-edit"></i>
							</button>
							<%
								}
							%>
						</form>
                    </td>
                    <td>
                        <form action="EliminarMedico.do" method="get">
                            <input name="legajo" type="hidden" value="${medico.legajo}">
							<%
								if (usuario.getPerfil() != PerfilUsuario.MEDICO.getPerfilUsuario()) {
							%>
							<button type="submit"
								onclick="return confirm('¿Confirma que desea eliminar este medico?')">

								<i class="fa fa-trash"></i>
							</button>
							<%
								}
							%>
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
