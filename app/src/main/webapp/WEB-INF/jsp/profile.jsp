<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="${curUser.username}">

	<div class="flex-container" style="padding-top: 53px;">

		<!-- Navbar -->
		<jsp:include page="/WEB-INF/components/navbar.jsp" >
			<jsp:param name="active" value="Profile"/>
		</jsp:include>

		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />
	</div>

	<!-- Page Content -->
	<div class="container story with-sidebar">
		<c:if test="${otherUser == null}">
		
			<!-- Set status -->
			<jsp:include page="/WEB-INF/components/set-status-form.jsp" />
		</c:if>

		<!-- User story -->
		<jsp:include page="/WEB-INF/components/user-stories.jsp" />

	</div>
	<script src="js/jquery-profile.js"></script>
</t:wrapper>
