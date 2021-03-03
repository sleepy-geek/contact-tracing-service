<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
		<meta charset="ISO-8859-1">
		<title>Contact Tracing Form</title>
	</head>
	<body>
		<div class="container">
			<form:form method="POST" action="/login" modelAttribute="admin">
				<table class="table-striped">
					<tr>
						<td><form:label path="username">Username:</form:label></td>
						<td><form:input path="username"/></td>
						<td><form:errors path="username" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="password">Password:</form:label></td>
						<td><form:password path="password"/></td>
						<td><form:errors path="password" cssClass="error"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Login"/></td>
					</tr>
				</table>
			</form:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  
 	 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
	</body>
	</html>