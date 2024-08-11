<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.dtos.*" %>   
<jsp:useBean id="adminBean" class="models.beans.AdminBean" scope="session"></jsp:useBean>
<%
    Object admin = session.getAttribute("admin");
    if (admin != null)
    	System.out.println("Menu admin: " + ((AdminDTO)admin).isLoggedIn());
    else 
    	response.sendRedirect("Error.jsp");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Menu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
<style>
	li{
		font-size:20px;
	}
</style>
</head>
<body>
	<header class="text-center">
	<h1 style="font-size:40px">Welcome to the Admin Application</h1>
	<ul class="nav justify-content-center">
	<li class="nav-item"><a class="nav-link" href="?action=home">Home</a></li>
	<li class="nav-item"><a class="nav-link" href="?action=categories">Categories</a></li>
	<li class="nav-item"><a class="nav-link" href="?action=users">Users</a></li>
	<li class="nav-item"><a class="nav-link" href="?action=stats" target="_blank">Statistics</a></li>
	
	<li class="nav-item"><a class="nav-link" href="?action=signout">Logout</a></li>
</ul>
	</header>
	<div>
	<%
	String router = request.getParameter("action");
	%>
	
	<% if("categories".equals(router)) { %>
		<jsp:include page="Categories.jsp"></jsp:include>
	<%} else if ("users".equals(router)) { %>
		<jsp:include page="Users.jsp"></jsp:include>
	<%} else { %>
	<div class="container-fluid text-center">
			<br />
			<h4>Your data:</h4>
			<h5><b>First name:</b> <i>${admin.getFirstName()}</i></h5>
			<h5><b>Last name:</b> <i> ${admin.getLastName()}</i></h5>
			<h5><b>Username:</b> <i> ${admin.getUsername()}</i></h5>
			<h5></h5>
		</div>
	<% } %>

</div>
</body>
</html>