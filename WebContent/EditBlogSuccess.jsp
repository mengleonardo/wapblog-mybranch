<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Blog Success</title>
 <link rel="stylesheet" href="https://unpkg.com/spectre.css/dist/spectre.min.css">
	<link rel="stylesheet" href="https://unpkg.com/spectre.css/dist/spectre-exp.min.css">
	<link rel="stylesheet" href="https://unpkg.com/spectre.css/dist/spectre-icons.min.css">
	<link rel="stylesheet" href="css/index.css">
	<style>
	h5 {
	display: inline;
	}
	</style>
	
  </head>
<body>
  <div class="container">
  		<div class="columns">
  		<div class="column col-6 col-gapless col-mx-auto col-ml-auto">
  		
	<h5>Success!</h5></br></br>
	<h5>You can check this blog by clicking this link:</h5>
	<a href="view?blogid=${ blog.id }">${ blog.title }</a></br></br>
	<h5>Or you can go back to the blog list:</h5>
	<a href="list">View the blog list</a>
	</div></div></div>
</body>
</html>