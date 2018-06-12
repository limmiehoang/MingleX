<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MingleX - Find The One For You</title>
	<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<!-- Custom styles -->
	<link href="css/style.css" rel="stylesheet">
    <link href="css/style_profile.css" rel="stylesheet">
</head>

<body>
    <div class="flex-container" style="padding-top: 53px;">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="/profile" class="navbar-brand" style="padding: 0;">
                        <img class="logo" src="images/logo-minglex-bg.png">
                    </a>
                </div>
                <ul class="nav navbar-nav">
                <li class="active"><a href="#">Profile</a></li>
                <li><a href="#">Match</a></li>
                <li><a href="#">Explore</a></li>
                </ul>
                
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>

            </div>
        </nav>

        <!-- Sidebar -->
        <div class="w3-sidebar alice-blue w3-bar-block" style="width:20%">
            <div class="user-profile">
                <img class="rounded-circle" src="images/test/dating.jpg">
                <h4>${curUser.username}</h4>
            </div>
        </div>

        <!-- Page Content -->
        <div class="container-fluid" style="margin-left:20%; padding: 25px 50px;">
			<h5>Username</h5>
			<p class="profile-info" id="username">${curUser.username}</p>
            <%-- <h5>Age</h5>
            <p class="profile-info" id="age">${curUser.age}</p> --%>
            <h5>Gender</h5>
            <p class="profile-info" id="Gender">${curUser.gender}</p>
            </div>
        <footer>Copyright &copy; KSV - mgm security partners' interns</footer>
    </div>

</body>