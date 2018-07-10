<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="/status/new" method="POST" modelAttribute="status">
	<div class="form-group row">
		<div class="col-md-12 field">
			<form:textarea path="description" cols="30" rows="5"
				placeholder="Share your message here..." cssClass="form-control" />
			<input type="hidden" name="_csrf" value="${_csrfToken}" />
		</div>
	</div>
	<div class="form-group row text-right">
		<div class="col-md-12 field">
			<input type="submit" id="submit" class="btn btn-primary" value="Post">
		</div>
	</div>
</form:form>