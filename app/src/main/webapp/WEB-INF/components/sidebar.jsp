<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="cg" uri="/WEB-INF/tld/customTagLibrary" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<div class="sidebar alice-blue w3-bar-block" style="width: auto">
    <c:choose>
        <c:when test="${otherUser != null}">
            <div class="user-profile">
                <%--TODO: Change profile picture after done--%>
                <c:choose>
                    <c:when test="${otherUser.gender == 'male'}">
                        <img src="images/pp-male.jpg" class="rounded-circle profile-img">
                    </c:when>
                    <c:when test="${otherUser.gender == 'female'}">
                        <img src="images/pp-female.jpg"
                             class="rounded-circle profile-img">
                    </c:when>
                    <c:otherwise>
                        <img src="images/pp-whitehat.jpg"
                             class="rounded-circle profile-img">
                    </c:otherwise>
                </c:choose>
                <div class="detail-info">
                    <div class="info-item">
                        <h6>Username</h6>
                        <p>${cg:escapeHtml(otherUser.username)}</p>
                    </div>

                    <div class="info-item">
                        <h6>Gender</h6>
                        <p id="gender"></p>
                        <c:set var="gender" value="${otherUser.gender}"/>
                    </div>

                    <div class="info-item">
                        <h6>Looking for</h6>
                        <p id="lookingfor"></p>
                        <c:set var="lookingfor" value="${otherUser.lookingfor}"/>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="user-profile">
                <c:choose>
                    <c:when test="${curUser.gender == 'male'}">
                        <img src="images/pp-male.jpg" class="rounded-circle profile-img">
                    </c:when>
                    <c:when test="${curUser.gender == 'female'}">
                        <img src="images/pp-female.jpg"
                             class="rounded-circle profile-img">
                    </c:when>
                    <c:otherwise>
                        <img src="images/pp-whitehat.jpg"
                             class="rounded-circle profile-img">
                    </c:otherwise>
                </c:choose>
                <div class="detail-info">
                    <div class="info-item">
                        <h6>Username</h6>
                        <p>${cg:escapeHtml(curUser.username)}</p>
                    </div>

                    <div class="info-item">
                        <h6>Gender</h6>
                        <p id="gender"></p>
                        <c:set var="gender" value="${curUser.gender}"/>
                        <a class="edit-info" href="#" id="btn_edit_gender"><span
                                class="glyphicon glyphicon-edit"></span></a>
                    </div>

                    <div class="info-item">
                        <h6>Looking for</h6>
                        <p id="lookingfor"></p>
                        <c:set var="lookingfor" value="${curUser.lookingfor}"/>
                        <a class="edit-info" href="#" id="btn_edit_lookingfor"><span
                                class="glyphicon glyphicon-edit"></span></a>
                    </div>
                </div>
                <a href="#">Change your password</a>
            </div>
        </c:otherwise>
    </c:choose>
    <input id="csrfInput" type="hidden" name="_csrf" value="${_csrfToken}" />
</div>
<script>
    <%--Switch Stored XSS--%>
    <fmt:bundle basename="application">
        <fmt:message key="security.stored-xss" var="storedXss"/>
    </fmt:bundle>

    <c:choose>
        <c:when test="${storedXss == 'Protected'}">
            $("#gender").html('${cg:escapeJavaScript(gender)}');
            $("#lookingfor").html('${cg:escapeJavaScript(lookingfor)}');
        </c:when>

        <c:otherwise>
            $("#gender").html('${gender}');
            $("#lookingfor").html('${lookingfor}');
        </c:otherwise>
    </c:choose>
</script>