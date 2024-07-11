<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/estilo.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>LOGIN</title>
</head>
<body>

<h1 class="title">Iniciar sesion</h1>

	<div class="inicio">
		
		<form action="Access.do" method=post>
		
			<table>
				<tr><td>Usuario: </td><td><input type=text name= nombre></td></tr>
			    <tr><td>Password: </td><td><input type=password name= password></td></tr>
			</table>
			<%  %>
			<div class="pt-4 d-flex justify-content-between" >
				 <input class="btn btn-outline-dark" type=submit value=Login>
				 <input class="btn btn-outline-dark"type=reset>
			</div>
		</form>
	</div>
<% 
    if(request.getSession().getAttribute("user") != null) {
%>
        ${cartelError}
<% 
    }
%>


</body>
</html>
