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
			<h3>Hello! Thank you for using our system. Kindly input correct details on the fields below.</h3>
			<form:form method="POST" action="/forms/${formId}/save" modelAttribute="subject">
				<table class="table-striped">
					<tr>
						<td><form:label path="firstName">First Name:</form:label></td>
						<td><form:input path="firstName"/></td>
						<td><form:errors path="firstName" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="middleName">Middle Name:</form:label></td>
						<td><form:input path="middleName"/></td>
						<td><form:errors path="middleName" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="lastName">Last Name:</form:label></td>
						<td><form:input path="lastName"/></td>
						<td><form:errors path="lastName" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="idNumber">ID Number:</form:label></td>
						<td><form:input path="idNumber"/></td>
						<td><form:errors path="idNumber" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="contactNumber">Contact Number:</form:label></td>
						<td><form:input path="contactNumber"/></td>
						<td><form:errors path="contactNumber" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="email">Email:</form:label></td>
						<td><form:input path="email"/>${email}</td>
						<td><form:errors path="email" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="position">Position:</form:label></td>
						<td>
							<form:select path="position">
								<form:option value="${position}" label="${positionLabel}"/>
								<form:options items="${validPositions}"/>
							</form:select>
						</td>
						<td><form:errors path="position" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="department">Department:</form:label></td>
						<td>
							<form:select path="department">
								<form:option value="${department}" label="${departmentLabel}"/>
								<form:options items="${validDepartments}"/>
							</form:select>
						</td>
						<td><form:errors path="department" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:label path="agreedDataPrivacyConsent">Data Privacy Agreement:</form:label></td>
						<td><form:checkbox path="agreedDataPrivacyConsent"/></td>
						<td><form:errors path="agreedDataPrivacyConsent" cssClass="error"/></td>
					</tr>
					<tr>
						<td><form:hidden path="subjectId"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Save Details"/></td>
					</tr>
				</table>
			</form:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  
 	 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
	</body>
	</html>