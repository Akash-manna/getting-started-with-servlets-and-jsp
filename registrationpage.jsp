<html>
	<head>
		<title>Registration Page</title>
	</head>
	<body>
		<form name="registration" action="registerEmployee" method="post"> <!--Posting to RegisterDBEmployee.class servlet-->
			<label for="first_name">First Name: </label>
			<input type="text" name="first_name" id="first_name" placeholder="First Name"><br>
			<label for="last_name">Last Name: </label>
			<input type="text" name="last_name" id="last_name" placeholder="Last Name"><br>
			<label for="city">City Name: </label>
			<input type="text" name="city" id="city" placeholder="City Name"><br>
			<label for="email">Email: </label>
			<input type="text" name="email" id="email" placeholder="Email"><br>
			<label for="age">Age: </label>
			<input type="text" name="age" id="age" placeholder="age"><br>
			<input type="submit" value="submit">
		</form>
	</body>
</html>