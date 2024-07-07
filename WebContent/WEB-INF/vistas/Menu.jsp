<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link href="./css/estilo.css" rel="stylesheet">
<title>Navegacion</title>
</head>
<body>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>

	<nav class="navbar navbar-dark bg-mynav">
	<div class="container-fluid">
		<form action="navPages.do" method=post>
			<input class="btn btn-primary" type=submit name="inicioJsp"
				value=Inicio> <input class="btn btn-primary" type=submit
				name="medicosJsp" value=Medicos> <input
				class="btn btn-primary" type=submit name="pacientesJsp"
				value=Pacientes> <input class="btn btn-primary" type=submit
				name="turnosJsp" value=Turnos>
		</form>
		<div class="nav-item dropdown">
			<a class="navbar-brand dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Reportes </a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="">Por Especialidad</a> <a
					class="dropdown-item" href="">Por Medicos</a>
			</div>
		</div>

		<form action="navLogOut.do" method=post>
			<li class="navbar navbar-dark bg-mynav"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false">
					${NombreUsuario} </a>
				<ul class="dropdown-menu">
					<li><input class="btn btn-outline-dark" type=submit
						value="Cerrar Session"></li>
				</ul></li>
		</form>

	</div>
	</nav>

</body>
</html>