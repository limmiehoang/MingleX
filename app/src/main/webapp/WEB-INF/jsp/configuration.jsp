<%@ page import="com.ksv.minglex.setting.SecuritySetting" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Security Configuration">
    <div class="container">
        <table class="table table-hover table-bordered">
            <thead>
            <tr>
                <th>Protection</th>
                <th>Status</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="settings" value="${securitySetting}"/>
            <c:if test="${not empty settings['class'].declaredFields}">
                <c:forEach var="attr" items="${settings['class'].declaredFields}">
                    <tr class="${(settings[attr.name] != "false" && (settings[attr.name] == "true" || settings[attr.name] == "Protected" || settings[attr.name] == "BCrypt")) ? "success" : "warning"}">
                        <td>${attr.name}</td>
                        <td>${settings[attr.name]}</td>
                        <td>Lorem ipsum dolor sit amet</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</t:wrapper>