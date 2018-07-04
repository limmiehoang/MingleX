<%@taglib prefix="cg" uri="/WEB-INF/tld/customTagLibrary"%>
	<!-- Navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="/profile" class="navbar-brand" style="padding: 0;"> <img
				class="logo" src="images/logo-minglex-bg.png">
			</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/profile">Profile</a></li>
			<li><a href="#">Match</a></li>
			<li><a href="/explore">Explore</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="/profile">Hello, <strong class="nav-username">${cg:escapeHtml(curUser.username)}</strong></a></li>
			<li><a href="/logout"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>

	</div>
	</nav>