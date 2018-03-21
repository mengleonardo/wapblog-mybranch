$(function(){
  console.log('hi');

  $("#submit").click(function() {
    var title = $("#title").val();
    var content = $("#content").val();
    $.post("NewBlogServlet", {"title": title, "content": content}).
    done(function() {
    	alert();
    })
    .fail(function() {

    });
  });
});
