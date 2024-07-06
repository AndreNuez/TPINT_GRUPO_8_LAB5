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

<form id="formTurno" action="ABMTurno.do" method="post">
    <h2 class="title">Alta y Modificacion de Turnos</h2>

    <div class="formulario">
        <input type="hidden" name="id" value="${turno.id}">

        <div>
            <table>
                <tr>
                    <td><label>Dni Paciente</label></td>
                    <td><input type="text" name="txtDniPaciente" value="${turno.paciente.dni}" required ${not empty turno.paciente.dni ? 'disabled' : ''}></td>
                    <c:if test="${empty turno.paciente.dni}">
                        <td><button type="button" id="btnBuscarDni" class="btn btn-outline-success">Buscar Dni</button></td>
                    </c:if>
                </tr>

                <c:if test="${not empty turno.paciente.dni}">
                    <tr>
                        <td><label>Nombre</label></td>
                        <td><input type="text" name="txtNombrePaciente" value="${turno.paciente.nombre}" readonly style="background-color: #eee;"></td>
                    </tr>

                    <tr>
                        <td><label>Apellido</label></td>
                        <td><input type="text" name="txtApellidoPaciente" value="${turno.paciente.apellido}" readonly style="background-color: #eee;"></td>
                    </tr>

                    <tr>
                        <td><label>Especialidad</label></td>
                        <td><select name="selEspecialidad" id="selEspecialidad" style="width: 233px;" ${not empty turno.paciente.dni ? 'disabled' : ''}>
                            <option value="">Seleccione una Especialidad</option>
                            <c:forEach items="${especialidades}" var="especialidad">
                                <option value="${especialidad.id}" ${especialidad.id == turno.medico.especialidad.id ? 'selected' : ''}>${especialidad.nombre}</option>
                            </c:forEach>
                        </select></td>
                    </tr>

                    <tr>
                        <td><label>Medico</label></td>
                        <td><select name="selMedico" id="selMedico" style="width: 233px;" ${not empty turno.paciente.dni ? 'disabled' : ''}>
                            <option value="">Seleccione un Medico</option>
                            <c:forEach items="${medicos}" var="medico">
                                <option value="${medico.dni}" data-especialidad="${medico.especialidad.id}" data-jornada="${medico.jornada.descripcion}" ${medico.dni == turno.medico.dni ? 'selected' : ''}>
                                    ${medico.apellido} ${medico.nombre}
                                </option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <td colspan="2"><label style="font-weight: bold;" id="lblJornada">${turno.medico.jornada.descripcion}</label></td>
                    </tr>

                    <tr>
                        <td><label>Fecha de Reserva</label></td>
                        <td><input type="date" name="txtFechaReserva" value="${turno.fecha}" required style="width: 233px;" ${not empty turno.paciente.dni ? 'readonly disabled' : ''}></td>
                        <td><button type="button" id="btnBuscarFecha" class="btn btn-outline-primary" ${not empty turno.paciente.dni ? 'style="display: none;"' : ''}>Buscar Fecha</button></td>
                    </tr>

                    <c:if test="${not empty horas}">
                        <tr>
                            <td><label>Hora</label></td>
                            <c:choose>
                                <c:when test="${horas.size() == 0}">
                                    <td><label>No hay turnos disponibles para la fecha seleccionada</label></td>
                                </c:when>
                                <c:otherwise>
                                    <td><select name="selHora">
                                        <c:forEach items="${horas}" var="hora">
                                            <option value="${hora}" ${hora == turno.hora ? 'selected' : ''}>${hora}</option>
                                        </c:forEach>
                                    </select></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:if>

                    <tr>
                        <td><label>Observaciones</label></td>
                        <td><textarea name="txtObservacion" style="width: 233px;" required maxlength="1000">${turno.observacion}</textarea></td>
                    </tr>
                </c:if>
            </table>
        </div>

        <div class="pt-4 w-25 d-flex justify-content-around">
            <c:choose>
                <c:when test="${empty turno.id}">
                    <button type="submit" class="btn btn-outline-success" name="btnGrabar">Grabar</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-outline-primary" name="btnActualizar">Actualizar</button>
                    <button type="submit" class="btn btn-outline-danger" name="btnEliminar">Eliminar</button>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${not empty success}">
            <div class="success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </div>
</form>

<script type="text/javascript">
    document.getElementById('selEspecialidad').addEventListener('change', filtrarDesplegableB);
    document.getElementById('selMedico').addEventListener('change', mostrarJornada);

    function filtrarDesplegableB() {
        const valorSeleccionado = document.getElementById('selEspecialidad').value;
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
        const descripcionJornada = medicoSeleccionado.getAttribute("data-jornada");
        document.getElementById("lblJornada").innerText = descripcionJornada;
    }

    filtrarDesplegableB();
</script>
</body>
</html>
