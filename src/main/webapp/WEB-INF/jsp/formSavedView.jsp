<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
		<title>Subject Details Saved</title>
	</head>
<body>
	<div class="container">
		<h2>Congratulations! You've successfully saved your data. Please refer to details below:</h2>
		<table class="table-striped">
			<form:form method="POST" action="/forms/${formId}/submit" modelAttribute="subject">
				<tr>
					<td><form:label path="firstName">First Name:</form:label></td>
					<td>${firstName}</td>
				</tr>
				<tr>
					<td><form:label path="middleName">Middle Name:</form:label></td>
					<td>${middleName}</td>
				</tr>
				<tr>
					<td><form:label path="lastName">Last Name:</form:label></td>
					<td>${lastName}</td>
				</tr>
				<tr>
					<td><form:label path="idNumber">ID Number:</form:label></td>
					<td>${idNumber}</td>
				</tr>
				<tr>
					<td><form:label path="contactNumber">Contact Number:</form:label></td>
					<td>${contactNumber}</td>
				</tr>
				<tr>
					<td><form:label path="email">Email:</form:label></td>
					<td>${email}</td>
				</tr>
				<tr>
					<td><form:label path="position">Position:</form:label></td>
					<td>${position}</td>
				</tr>
				<tr>
					<td><form:label path="department">Department:</form:label></td>
					<td>${department}</td>
				</tr>
				<tr>
					<td><form:label path="agreedDataPrivacyConsent">Data Privacy Agreement:</form:label></td>
					<td>${agreedDataPrivacyConsent}</td>
				</tr>
				<tr>
					<td><form:label path="subjectId">Subject Id</form:label></td>
					<td>${subjectId}</td>
				</tr>
				<tr>
					<td><input type="button" value="Back" onclick="history.back()"/></td>
					<td><input type="submit" value="Submit"/></td>
				</tr>
			</form:form>
		</table>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
</body>
</html>