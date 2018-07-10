<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Match">
    <div class="flex-container" style="padding-top: 53px;">
        
		<!-- Navbar -->
		<jsp:include page="/WEB-INF/components/navbar.jsp" >
			<jsp:param name="active" value="Match"/>
		</jsp:include>

        <!-- Page Content -->
        <div class="container">

			<!-- matched users -->
			<jsp:include page="/WEB-INF/components/users.jsp" />
		</div>

    </div>
</t:wrapper>