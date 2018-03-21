$(function () {
	console.log("hi 6");
	$("#edit_btn").click(function () {
		console.log('edit btn 2');
		var blogId = $("#blog_id").val();
//		$.get("edit", {"blogId": blogId})
//		.done(function() { })
//		.fail(function() { })
//		.always(function() { });
//		window.open('edit?blogid=' + blogId, '_blank');
		location.href = 'edit?blogid=' + blogId;
	});
	$("#delete_btn").click(function () { 
		console.log('delete btn');
		var blogId = $("#blog_id").val();
		$.get("delete", {"blogId": blogId})
		.done(function() {
			alert("delete blog success!");
			location.href = 'list';
		})
		.fail(function() {
			alert("delete blog failed!");
		})
		.always(function() { });
	});
});