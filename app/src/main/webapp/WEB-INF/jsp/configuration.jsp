<%@ page import="com.ksv.minglex.setting.SecuritySetting"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper title="Security Configuration">
    <div class="container security-setting">
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th>Protection</th>
                    <th>Status</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="settings" value="${securitySetting}" />
                <c:if
                    test="${not empty settings['class'].declaredFields}">
                    <c:forEach var="attr"
                        items="${settings['class'].declaredFields}">
                        <tr
                            class='${(settings[attr.name] !=
							"false" && (settings[attr.name] ==
							"true" || settings[attr.name] ==
							"Protected" || settings[attr.name] =="BCrypt")) ? "success" : "warning"}'>
                            <td>${attr.name}</td>
                            <td>${settings[attr.name]}</td>
                            <td>Lorem ipsum dolor sit amet</td>
                            <td><c:if
                                    test="${attr.name != 'storedXSS' && attr.name != 'storePasswordSolution'}">
                                    <label class="switch"> <input
                                        type="checkbox"
                                        name="${attr.name}"
                                        ${(settings[attr.name] !=
                                                "false" && (settings[attr.name] ==
                                                "true" || settings[attr.name] ==
                                                "Protected" || settings[attr.name] =="BCrypt")) ? "checked" : ""}
                                        onchange="updateSettings(this)">
                                        <span class="slider round"></span>
                                    </label>
                                </c:if> <c:if
                                    test="${attr.name == 'storePasswordSolution'}">
                                    <div class="form-group">
                                        <select class="form-control"
                                            id="storePasswordSolution"
                                            name="storePasswordSolution"
                                            value="${settings[attr.name]}"
                                            onchange="updateSettings(this)">
                                            <option value="Plain"
                                                ${settings[attr.name] == "Plain"? "selected" : ""}>Plain</option>
                                            <option value="Hash"
                                                ${settings[attr.name] == "Hash"? "selected" : ""}>Hash</option>
                                            <option value="SaltHash"
                                                ${settings[attr.name] == "SaltHash"? "selected" : ""}>SaltHash</option>
                                            <option value="BCrypt"
                                                ${settings[attr.name] == "BCrypt"? "selected" : ""}>BCrypt</option>
                                        </select>
                                    </div>
                                </c:if> <c:if
                                    test="${attr.name == 'storedXSS'}">
                                    <div class="form-group">
                                        <select class="form-control"
                                            id="storedXSS"
                                            name="storedXSS"
                                            value="${settings[attr.name]}"
                                            onchange="updateSettings(this)">
                                            <option value="None"
                                                ${settings[attr.name] == "None"? "selected" : ""}>None</option>
                                            <option
                                                value="HTMLEscapeOnly"
                                                ${settings[attr.name] == "HTMLEscapeOnly"? "selected" : ""}>HTMLEscapeOnly</option>
                                            <option value="Protected"
                                                ${settings[attr.name] == "Protected"? "selected" : ""}>Protected</option>
                                        </select>
                                    </div>
                                </c:if></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
        <script>
		var settings = {
                <c:forEach var="attr" items="${settings['class'].declaredFields}">
                    <c:if
                        test="${attr.name != 'storedXSS' && attr.name != 'storePasswordSolution'}">
                        "${attr.name}": ${settings[attr.name]},
                    </c:if>
                    <c:if
                        test="${attr.name == 'storedXSS' || attr.name == 'storePasswordSolution'}">
                        "${attr.name}": "${settings[attr.name]}",
                    </c:if>
                </c:forEach>
        }
		console.log(settings);
		function updateSettings(e) {
            if (e.checked != undefined) settings[e.name] = e.checked;
            else settings[e.name] = e.value;
            console.log(settings);
            
            $.ajax({
                type : 'PUT',
                url : '/configuration/update',
                data : JSON.stringify(settings),
                contentType : 'application/json',
                success : function(res) {
                    console.log("success", res);
                    location.reload();
                },
                error : function(res) {
                    console.log("error", res);
                    alert(res.statusText);
                }
            });
        }
		</script>
    </div>
</t:wrapper>
