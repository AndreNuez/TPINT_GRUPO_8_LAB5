<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
<%@include file="estilo.css" %>
</style>
<title>Listado de Medicos</title>
</head>
<body>

<%@include file="Menu.jsp"%>

<link rel="stylesheet" type="text/css"
href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
$(document).ready(function() {
$('#table_id').DataTable();
});
</script>

</head>
<body>

<h1 class="title">Listado de medicos</h1>
<form action="AddMedico.do" method=post>
<input class="btn btn-outline-dark" type="submit" name="btnAgregarMedico" value="Agregar Medico" style="margin-left: 730px;">
</form>
<br>
<br>
<br>

<table border="1" id="table_id">

<thead>
<tr>
<td><b>DNI</b></td>
<td><b>Nombre</b></td>
<td><b>Apellido</b></td>
<td><b>Sexo</b></td>
<td><b>Especialidad </b></td>
<td><b>Correo Electronico</b></td>
<td><b>Direccion</b></td>
<td><b></b></td>
</tr>
</thead>
<tbody>
<%

%>
<tr>
<td>35698742</td>
<td>Juan%></td>
<td>Perez</td>
<td>Masculino</td>
<td>Cardiologia</td>
<td>juanperez@intramed.com</td>
<td>Direccion</td>
<td><a href=""><i
class="fa fa-edit"></i></a></td>
</tr>
<%

%>
</tbody>

</table>

<%

%>
<div class="success"></div>
<%

%>
<%

%>
<div class="error"></div>
<%

%>

</body>
</html>
