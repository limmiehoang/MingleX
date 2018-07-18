<%@taglib prefix="cg" uri="/WEB-INF/tld/customTagLibrary" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="/profile" class="navbar-brand" style="padding: 0;"> <img
                    class="logo" src="images/logo-minglex-bg.png">
            </a>
        </div>

        <ul class="nav navbar-nav">
            <li class="
                    <c:if test="${param.active == 'Profile'}">
                        <c:out value="active"/>
                    </c:if>
                    "
            >
                <a href="/profile">Profile</a></li>
            <li class="
                    <c:if test="${param.active == 'Chat'}">
                        <c:out value="active"/>
                    </c:if>
                    "
            >
                <a href="/chat">Chat</a></li>
            <li class="
                    <c:if test="${param.active == 'Explore'}">
                        <c:out value="active"/>
                    </c:if>
                    "
            >
                <a href="/explore">Explore</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="/profile">Hello, <strong class="nav-username">${cg:escapeHtml(curUser.username)}</strong></a>
            </li>
            <li><a href="/logout"><span
                    class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
        <form class="navbar-form navbar-right" action="/search">
            <div class="input-group" style="display: flex;">
                <input type="text" class="form-control" placeholder="Search" name="keywords">
                <select name="gender" style="width: 120px">
                    <option value="all" selected> All</option>
                    <option value="other"> Other</option>
                    <option value="male"> Male</option>
                    <option value="female"> Female</option>
                </select>
                <div class="input-group-btn" style="display: flex;">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>

    </div>
</nav>