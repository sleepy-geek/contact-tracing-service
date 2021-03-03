<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
		<meta charset="ISO-8859-1">
		<title>Admin Home</title>
	</head>
	<body>
		<div class="container">
			<h3>Welcome to your administrator dashboard. Please select option below.</h3>
			<table>
				<tr>
					<td>
						<form:form method="GET" action="/admin/update-subject-details">
							<input type="submit" value="Subject Editor"/>
						</form:form>
					</td>
					<td>
						<form:form method="GET" action="/admin/update-system-details">
							<input type="submit" value="System Editor"/>
						</form:form>
					</td>
					<td>
						<form:form method="POST" action="/logout">
							<input type="submit" value="Logout"/>
						</form:form>
					</td>
				</tr>
			</table>
			<div id="targetView">
			</div>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  
 	 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
	</body>
	</html>