<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
		<title>Subject Details Submitted</title>
	</head>
<body>
	<div class="container">
		<h2>Congratulations! You're request was successfully submitted. Please check your email for your personal identification QR code.</h2>
		<table class="table-striped">
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
		</table>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
</body>
</html>