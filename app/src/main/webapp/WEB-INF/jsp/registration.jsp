<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MingleX - Registration</title>
<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
	<!-- Custom styles -->
	<link href="css/style.css" rel="stylesheet">
    <link href="css/style_login.css" rel="stylesheet">
	<link href="css/util.css" rel="stylesheet">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-50">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default panel-login" style="margin-bottom: 0;">
							<div class="panel-heading">
								<h3 class="panel-title">Register</h3>
							</div>
							<div class="panel-body">
								<form:form action="/registration" method="POST"
									modelAttribute="user">
									<div class="form-group">
										<label>Username</label>
										<form:input path="username" cssClass="form-control" />
										<form:errors path="username" cssClass="error" />
									</div>
									<div class="form-group">
										<label>Password</label>
										<form:password path="password" cssClass="form-control" />
										<form:errors path="password" cssClass="error" />
									</div>
									<div class="form-group">
										<label>Gender</label><br>
										<label class="radio-inline">
											<form:radiobutton path="gender" value="male" checked="checked"/>
											Male
										</label>
										
										<label class="radio-inline">
											<form:radiobutton path="gender" value="female"/>
											Female
										</label>
										
										<label class="radio-inline">
											<form:radiobutton path="gender" value="other"/>
											Other
										</label>
										<form:errors path="gender" cssClass="error" />
									</div>
									<div class="form-group">
										<label>Looking for</label><br>
										<label class="radio-inline">
											<form:radiobutton path="lookingfor" value="men"/>
											Men
										</label>
										
										<label class="radio-inline">
											<form:radiobutton path="lookingfor" value="women"/>
											Women
										</label>
		
										<label class="radio-inline">
											<form:radiobutton path="lookingfor" value="both" checked="checked"/>
											Both
										</label>
										<form:errors path="lookingfor" cssClass="error" />
									</div>
									<input type="hidden" name="_csrf" value="${_csrfToken}" />
									<div class="form-group form-actions text-right">
										<input type="reset" class="btn btn-default" value="Reset">
										<input type="submit" class="btn btn-primary" value="Submit">
									</div>
									<p>
										Already have an account? <a href="/login">Login here</a>.
									</p>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>Copyright &copy; KSV - mgm security partners' interns</footer>
	
</body>
</html>