<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Contact Tracing Home</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
	</head>
	<body>
		<div class="container">
			<h3>Hello! Thank you for using our system. Kindly input correct details on the fields below.</h3>
			<form method="POST" action="/forms/create">
				<table class="table-striped">
					<tr>
						<td><label path="email">Email:</label></td>
						<td><input type="text" name="email"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Create Form"/></td>
					</tr>
				</table>
			</form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
	</body>
	</html>