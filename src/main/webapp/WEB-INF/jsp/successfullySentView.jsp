<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subject Details</title>
</head>
<body>
	<h2>Congratulations! You've successfully created contact tracing subject record. Please refer to details below:</h2>
	<table>
		<tr>
			<td>First Name: </td>
			<td>${firstName}</td>
		</tr>
		<tr>
			<td>Middle Name: </td>
			<td>${middleName}</td>
		</tr>
		<tr>
			<td>Last Name: </td>
			<td>${lastName}</td>
		</tr>
		<tr>
			<td>ID Number: </td>
			<td>${idNumber}</td>
		</tr>
		<tr>
			<td>Contact Number: </td>
			<td>${contactNumber}</td>
		</tr>
		<tr>
			<td>Email: </td>
			<td>${email}</td>
		</tr>
		<tr>
			<td>Position: </td>
			<td>${position}</td>
		</tr>
		<tr>
			<td>Department: </td>
			<td>${department}</td>
		</tr>
		<tr>
			<td>Data Privacy Agreement: </td>
			<td>${agreedDataPrivacyConsent}</td>
		</tr>
		<tr>
			<td>Record ID: </td>
			<td>${subjectId}</td>
		</tr>
		<tr>
			<td>Image: </td>
			<td>${image}</td>
		</tr>
	</table>
</body>
</html>