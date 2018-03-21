"use strict";

$(function(){
  console.log('hi4');

  $("#submit").click(function() {
    var title = $("#title").val();
    var content = $("#content").val();
    console.log("NewServlet");
    $.post("NewServlet", {"title": title, "content": content}).
    done(function(json) {
    	location.href = "view?blogid=" + json['newBlogId'];
    })
    .fail(function() {
    	alert("Add nwe blog failed!")
    });
  });
});
