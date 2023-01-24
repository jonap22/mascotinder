<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">

<!-- Font Awesome -->
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>

<!-- CSS style sheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>
</head>
<body>
	<nav class="navbar navbar-dark banner-config rad-border">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1  app-title"> MascoTinder <i
				class='fas fa-dog' style='font-size: 34px'></i>
			</span>
		</div>
	</nav>
	<div class="wrapper">
		<div class="form-config">
			<form method="post" action="../LoginController">
				<fieldset>
					<legend id="login-title">Login</legend>
					<div class="underline-title"></div>
					<br>

					<p>Email:</p>
					<input type="email" name="txtEmail" placeholder="Enter your email"
						class="form-content" required>
					<div class="form-border"></div>
					<br> <br>

					<p>Password:</p>
					<input type="password" name="txtPassword"
						placeholder="Enter your password" class="form-content" required>
					<div class="form-border"></div>

					<input type="submit" value="Log In" id="submit-btn">
				</fieldset>
			</form>
		</div>
	</div>

</body>
</html>