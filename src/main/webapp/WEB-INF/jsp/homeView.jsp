<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Contact Tracing Form</title>
	</head>
	<body>
		<h3>Hello! Thank you for using our system. Kindly input correct details on the fields below.</h3>
		<form:form method="POST" action="/forms/create" modelAttribute="formUrl">
			<table>
				<tr>
					<td><form:label path="email">Email:</form:label></td>
					<td><form:input path="email"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create Form"/></td>
				</tr>
			</table>
		</form:form>
	</body>
	</html>