<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MingleX - Find The One For You</title>
	<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
	<!-- Custom styles -->
	<link href="css/style.css" rel="stylesheet">
    <link href="css/style_login.css" rel="stylesheet">
	<link href="css/util.css" rel="stylesheet">
	
</head>

<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
				<form action="/login" method="POST" class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-51">
						<a href="/">
							<img class="logo" src="images/logo-minglex-bg.png" style="height: 135px; width: auto;">
						</a>
					</span>
					
					<p style="color: red"><%=(request.getAttribute("errorMessage") == null) ? "" : request.getAttribute("errorMessage")%></p>
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
						<input class="input100" type="text" name="username" placeholder="Username">
						<span class="focus-input100"></span>
					</div>
					
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
					</div>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
		<footer>Copyright &copy; KSV - mgm security partners' interns</footer>
	</div>
</body>
