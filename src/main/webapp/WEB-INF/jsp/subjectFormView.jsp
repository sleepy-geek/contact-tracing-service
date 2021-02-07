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
		<form:form method="POST" action="/form/submit" modelAttribute="subject" enctype="multipart/form-data">
			<table>
				<tr>
					<td><form:label path="firstName">First Name:</form:label></td>
					<td><form:input path="firstName"/></td>
				</tr>
				<tr>
					<td><form:label path="middleName">Middle Name:</form:label></td>
					<td><form:input path="middleName"/></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Last Name:</form:label></td>
					<td><form:input path="lastName"/></td>
				</tr>
				<tr>
					<td><form:label path="idNumber">ID Number:</form:label></td>
					<td><form:input path="idNumber"/></td>
				</tr>
				<tr>
					<td><form:label path="contactNumber">Contact Number:</form:label></td>
					<td><form:input path="contactNumber"/></td>
				</tr>
				<tr>
					<td><form:label path="email">Email:</form:label></td>
					<td><form:input path="email"/></td>
				</tr>
				<tr>
					<td><form:label path="position">Position:</form:label></td>
					<td><form:input path="position"/></td>
				</tr>
				<tr>
					<td><form:label path="department">Department:</form:label></td>
					<td><form:input path="department"/></td>
				</tr>
				<tr>
					<td><form:label path="agreedDataPrivacyConsent">Data Privacy Agreement:</form:label></td>
					<td><form:checkbox path="agreedDataPrivacyConsent"/></td>
				</tr>
				<tr>
					<td><form:label path="image">Image:</form:label></td>
					<td><input type="file" name="image"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"/></td>
				</tr>
			</table>
		</form:form>
	</body>
	</html>