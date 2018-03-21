"use strict";

$(function() {
	console.log('login page');

	$("#Login").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		console.log("Loginservlet");
		$.post("LoginVerifiedServlet", {
			"username" : username,
			"password" : password
		}).done(function(data) {

			var result = $.parseJSON(data).check;
			if (result == 1) {

				alert("You successfully logged in ");
				window.location.href = "list";
			} else {
				alert("wrong combination");
			}
			// to-do: jump to the bloglist related to this user
			// location.href = "edit-blog-success?blogid=" + json['newBlogId'];
		})
		/*
		 * .fail(function() { alert("Your login failed"); })
		 */;
	});

	$("#Register").click(function() {
		window.location.href = "Register.html";

	});

});
