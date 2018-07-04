$(document).ready(function(){
	$("#btn_edit_gender").click(function(){
		$("#gender").html('\
			<form id="gender-update">\
				<div class="form-group">\
					<label class="radio-inline">\
						<input type="radio" name="gender" value="male" checked="checked"/>\
						Male\
					</label>\
					<label class="radio-inline">\
						<input type="radio" name="gender" value="female"/>\
						Female\
					</label>\
					<label class="radio-inline">\
						<input type="radio" name="gender" value="other"/>\
						Other\
					</label>\
				</div>\
				<div class="form-group form-actions text-right">\
					<input class="btn btn-primary" value="Done" onclick="updateProfile()">\
				</div>\
			</form:form>\
		');

	});
	$("#btn_edit_lookingfor").click(function(){
		$("#lookingfor").html('\
				<form id="gender-update">\
					<div class="form-group">\
						<label class="radio-inline">\
							<input type="radio" name="lookingfor" value="man" checked="checked"/>\
							Man\
						</label>\
						<label class="radio-inline">\
							<input type="radio" name="lookingfor" value="woman"/>\
							Woman\
						</label>\
						<label class="radio-inline">\
							<input type="radio" name="lookingfor" value="both"/>\
							Both\
						</label>\
					</div>\
					<div class="form-group form-actions text-right">\
						<input class="btn btn-primary" value="Done" onclick="updateProfile()">\
					</div>\
				</form>\
				<button onclick="updateProfile()">Update</button>\
		');

	});

});

function updateProfile() {
	var user = {
	        "gender": $("input[name='gender']:checked").val() || null,
	        "lookingfor": $("input[name='lookingfor']:checked").val() || null
	    };
	$.ajax({
		type : 'PUT',
		url : 'http://localhost:8080/profile',
		data : JSON.stringify(user),
		contentType : 'application/json',
		success : function(res) {
			console.log("success", res);
			if (user.gender != null) {
				$("#gender").html(user.gender);
			} else if (user.lookingfor != null) {
				$("#lookingfor").html(user.lookingfor);
			}
		},
		error : function(res) {
			console.log("error", res);
			alert(res.statusText);
		}
	});
}