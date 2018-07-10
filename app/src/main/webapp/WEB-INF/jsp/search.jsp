<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Explore">
    <div class="flex-container" style="padding-top: 53px;">

        <!-- Navbar -->
        <jsp:include page="/WEB-INF/components/navbar.jsp" />

        <!-- Page Content -->
        <div class="container">

            <!-- search notification -->
            <jsp:include page="/WEB-INF/components/search-notification.jsp" />

            <!-- matched users -->
            <jsp:include page="/WEB-INF/components/users.jsp" />
        </div>

    </div>
</t:wrapper>