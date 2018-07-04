<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="cg" uri="/WEB-INF/tld/customTagLibrary"%>
<div class="w3-sidebar alice-blue w3-bar-block" style="width: auto">
	<c:choose>
		<c:when test="${otherUser != null}">
			<div class="user-profile">
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
						<p>${otherUser.gender}</p>
					</div>

					<div class="info-item">
						<h6>Looking for</h6>
						<p>${otherUser.lookingfor}</p>
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
						<a class="edit-info" href="#" id="btn_edit_gender"><span
							class="glyphicon glyphicon-edit"></span></a>
					</div>

					<div class="info-item">
						<h6>Looking for</h6>
						<p id="lookingfor"></p>
						<a class="edit-info" href="#" id="btn_edit_lookingfor"><span class="glyphicon glyphicon-edit"></span></a>
					</div>
				</div>
				<a href="#">Change your password</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<script>
	$("#gender").html('${curUser.gender}');
	$("#lookingfor").html('${curUser.lookingfor}');
</script>