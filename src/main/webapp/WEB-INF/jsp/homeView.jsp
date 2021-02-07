<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Contact Tracing Home</title>
	</head>
	<body>
		<h3>Hello! Thank you for using our system. Kindly input correct details on the fields below.</h3>
		<form method="POST" action="/forms/create">
			<table>
				<tr>
					<td><label path="email">Email:</label></td>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create Form"/></td>
				</tr>
			</table>
		</form>
	</body>
	</html>