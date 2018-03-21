"use strict";

$(function() {
	console.log('Register page');
	$("#username").blur(function() {
		var result;
		var username = $("#username").val();
		$.ajax({
			url : "registerVerified",
			type : 'post',
			data : {
				"username" : username
			},
			cache : false,
			success : function(data) {
				result = $.parseJSON(data).check;
				if (result == 1) {
					alert("username existed,plz retype")
					var hid = document.getElementById("hide");
					hid.value = 1;
				} else {
					var hid = document.getElementById("hide");
					hid.value = 0;
				}
			},
			error : function(error) {
				console.log('ajax error' + error);
			}
		});
		// do someing
	})

	$("#Register")
			.click(
					function() {
						if ($("#hide").val() == 0) {
							var username = $("#username").val();
							if (username.length == 0|| username.indexOf(" ") >= 0) {
								alert("You must type in username or there is space in your username")
								window.location.href = "Register.html";
							}
							var email = $("#email").val();
							var password = $("#password").val();
							console.log(username + email + password);
							$.ajax({
								url : "register",
								type : 'post',
								data : {
									"username" : username,
									"email" : email,
									"password" : password
								},
								cache : false,
								success : function(data) {
									alert(data);
									window.location.href = "login.html";

								},
								error : function(error) {
									console.log('ajax error' + error);
								}
							});
						}
					});
});

/*
 * function checkConfirm(){ var result; var username = $("#username").val(); var
 * Url = "RegisteVerifiedServlet"; $.ajax({ url : "registerVerified", type :
 * 'post', data:{"username":username}, success : function(data) { result =
 * $.parseJSON(data).check; if( result == 1) { alert("username existed,plz
 * retype") var hid = document.getElementById("hide"); hid.value=1; } }, error :
 * function(error) { console.log('ajax error' + error); } }); };
 */

 