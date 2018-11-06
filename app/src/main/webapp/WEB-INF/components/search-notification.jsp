<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cg" uri="/WEB-INF/tld/customTagLibrary" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--Switch Reflected XSS--%>
<%-- <fmt:bundle basename="application">
    <fmt:message key="security.reflected-xss" var="reflectedXss"/>
</fmt:bundle> --%>

<c:if test="${reflectedXss == 'True' }">
    <c:set var="keywords" value="${cg:escapeHtml(keywords)}" />
    <c:set var="gender" value="${cg:escapeHtml(gender)}" />
</c:if>

<div class="alert alert-success">
    <strong>Success!</strong> You are searching for "${keywords}", "${gender}"
</div>