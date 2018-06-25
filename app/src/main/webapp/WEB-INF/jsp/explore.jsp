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
        
        <!-- Navbar -->
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="/profile" class="navbar-brand" style="padding: 0;">
                        <img class="logo" src="images/logo-minglex-bg.png">
                    </a>
                </div>
                <ul class="nav navbar-nav">
                <li><a href="/profile">Profile</a></li>
                <li><a href="#">Match</a></li>
                <li class="active"><a href="/explore">Explore</a></li>
                </ul>
                
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>

            </div>
        </nav>

        <!-- Page Content -->
        <div class="container">
            <!-- Set status -->
            <form method="post" action="/profile">
                <div class="form-group row">
                    <div class="col-md-12 field">
                        <textarea name="status" cols="30" rows="5" class="form-control" placeholder="Hello spiderman, share your message here..."></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-12 field" style="text-align: right;">
                        <input type="submit" id="submit" class="btn btn-primary" value="Post">
                    </div>
                </div>
            </form>
            
            <!-- All user story -->
            <div class="row first-row">
                <div class="col-sm-2 status-user-img">
                    <a href="#">
                        <img src="images/pp-female.jpg" class="rounded-circle">
                    </a>
                </div>
                <div class="col-sm-10">
                    <h3 class="title"><a href="#">wonderwoman</a></h3>
                    <p class="text-muted"><span class="glyphicon glyphicon-time"></span> 1 hour ago</p>
                    <p>I'm single and ready to MINGLEEEEE.</p>
                
                    
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col-sm-2 status-user-img">
                    <a href="#">
                        <img src="images/pp-male.jpg" class="rounded-circle">
                    </a>
                </div>
                <div class="col-sm-10">
                    <h3 class="title"><a href="#">batman</a></h3>
                    <p class="text-muted"><span class="glyphicon glyphicon-time"></span> 1 hour ago</p>
                    <p>I'm single and ready to MINGLEEEEE.</p>
                
                    
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col-sm-2 status-user-img">
                    <a href="#">
                        <img src="images/pp-whitehat.jpg" class="rounded-circle">
                    </a>
                </div>
                <div class="col-sm-10">
                    <h3 class="title"><a href="#">spiderman</a></h3>
                    <p class="text-muted"><span class="glyphicon glyphicon-time"></span> 1 hour ago</p>
                    <p>I'm single and ready to MINGLEEEEE.</p>
                
                    
                </div>
            </div>
            <hr>

        <footer>Copyright &copy; KSV - mgm security partners' interns</footer>
    </div>

</body>