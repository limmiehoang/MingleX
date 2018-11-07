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
					<input class="btn btn-primary" value="Done" onclick="updateProfile(\'gender\')">\
				</div>\
			</form>\
		');

	});
	$("#btn_edit_lookingfor").click(function(){
		$("#lookingfor").html('\
				<form id="lookingfor-update">\
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
						<input class="btn btn-primary" value="Done" onclick="updateProfile(\'lookingfor\')">\
					</div>\
				</form>\
		');
	});

    $("#btn_edit_crushingOn").click(function(){
        $("#crushingOn").html('\
				<form id="crushing-on-update">\
					<div class="form-group">\
                        <input type="text" name="crushingOn" value=""/>\
					</div>\
					<div class="form-group form-actions text-right">\
						<input class="btn btn-primary" value="Done" onclick="updateProfile(\'crushingOn\')">\
					</div>\
				</form>\
		');
    });

    $("#exportProfileBtn").click(function (e) {
        var xmlString = '<?xml%20version="1.0"%20encoding="UTF-8"?>\n' +
            '<user>\n' +
            '<gender>' + $("#gender").html() + '</gender>\n' +
            '<lookingfor>' + $("#lookingfor").html() + '</lookingfor>\n' +
            '<crushingOn>' + $("#crushingOn").html() + '</crushingOn>\n' +
            '</user>';
        var result = "data:application/octet-stream," + xmlString;
        this.href = result;
        this.download = "profile.xml";
        return true;
    });

    $("#importProfileInput").change(function() {
        $("#importProfileForm").submit();
    });
});

function updateProfile(type) {
	var user = null;
	console.log(type);
	if (type === "gender") {
		user = {"gender": $("input[name='gender']:checked").val() || null};
	} else if (type === "lookingfor") {
		user = {"lookingfor": $("input[name='lookingfor']:checked").val() || null};
	} else {
        user = {"crushingOn": $("input[name='crushingOn']").val() || null};
    }

	$.ajax({
		type : 'PUT',
		url : '/profile',
		data : JSON.stringify(user),
		contentType : 'application/json',
		headers: {
	        'X-CSRF-Token': $("#csrfInput").val()
	    },
		success : function(res) {
			console.log("success", res);
			if (user.gender) {
				$("#gender").html(user.gender);
			}
			if (user.lookingfor) {
				$("#lookingfor").html(user.lookingfor);
			}
			if (user.crushingOn) {
                $("#crushingOn").html(user.crushingOn);
            }
		},
		error : function(res) {
			console.log("error", res);
			alert(res.statusText);
		}
	});
}

// TODO: Change profile picture after done
