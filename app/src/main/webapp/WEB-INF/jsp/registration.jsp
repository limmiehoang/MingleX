<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default panel-login">
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
								<form:radiobutton path="gender" value="male" label="Male"
									checked="checked" />
								<br>
								<form:radiobutton path="gender" value="female" label="female" />
								<br>
								<form:radiobutton path="gender" value="other" label="other" />
								<form:errors path="gender" cssClass="error" />
							</div>
							<div class="form-group form-acions text-right">
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
</body>
</html>