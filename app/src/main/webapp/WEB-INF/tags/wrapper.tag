<%@attribute name="title" required="false" %>
<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MingleX - Find The One For You | <c:out value="${title}" /> </title>
	<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
		rel="stylesheet" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<!-- Custom styles -->
	<link href="css/style.css" rel="stylesheet">
	<link href="css/style_profile.css" rel="stylesheet">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body style="height: 100%;">

	<jsp:doBody/>
	
	<footer>Copyright &copy; KSV - mgm security partners' interns</footer>
</body>