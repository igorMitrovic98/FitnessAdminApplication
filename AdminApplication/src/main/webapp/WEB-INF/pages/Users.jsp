<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="models.dtos.UserDTO"%>
<%@page import="models.dtos.SupportDTO"%>
<%@page import="models.beans.UserBean"%>
<%@page import="models.daos.UserDAO"%>
<%@page import="models.dtos.AdminDTO"%>
<jsp:useBean id="userBean" class="models.beans.UserBean" scope="session"/>
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
<title>Users</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
	<%
session.setAttribute("msg", "");
if (request.getParameter("submit") != null) {
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	String file = request.getParameter("file");
	System.out.println("file: " + file);
	int result = UserDAO.insertSupport(new SupportDTO(username, password, firstName, lastName));
	if (result == -1) {
		session.setAttribute("msg", "Support acount already exists.");
	} else {
		session.setAttribute("msg", "Created successfully.");
	}
}

%>
		<div class="container-fluid justify-content-center">
			<div class="row align-items-center containter-fluid justify-content-center">
			<div class="col-md-3" style="padding: 25px;">
			<h3 style="text-align:center;">Create support account</h3>
			<form action="?action=users" method="POST">
			<input style="margin-bottom:5px" required class="form-control" autocomplete="off" name="firstName" placeholder="First name" type="text" />
			
			<input style="margin-bottom:5px" required class="form-control" autocomplete="off" name="lastName" placeholder="Last name" type="text" />
			
			<input style="margin-bottom:5px" required class="form-control" autocomplete="off" name="username" placeholder="Username" type="text" />
			
			<input style="margin-bottom:5px" required class="form-control" autocomplete="off" name="password" placeholder="Password" type="password" />
			
			<button class="w-100 btn btn-lg btn-primary" name="submit" type="submit">Create</button>
			<%=session.getAttribute("msg").toString() %>
			</form>
			</div>
			<div class="row-md-9 align-items-center">
		<h3>All users</h3>
		<table class="table">
		<thead>
		<tr>
			<th scope="col">First name</th>
			<th scope="col">Last name</th>
			<th scope="col">Username</th>
			<th scope="col">City</th>
			<th scope="col">Email</th>
			<th scope="col">Action</th>
		</tr>
		
		</thead>
		<tbody>
		<%
			for(UserDTO u : userBean.getAllUsers()) {
				out.println("<tr>" +
				"<td>"+ u.getFirstName()+"</td>"+
				"<td>"+ u.getLastName()+"</td>"+
				"<td>"+ u.getUsername()+"</td>"+
				"<td>"+ u.getCity() +"</td>"+
				"<td>"+ u.getEmail()+"</td>"+
				"<td><a href='?action=deleteUser&username="+u.getUsername()+"'>Delete</a></td>"+
				"</tr>");
			}
		
		%>
		</tbody>
		</table>
		</div>
		<div class="row-md-9 align-items-center">
		<h3>All support accounts</h3>
		<table class="table">
		<thead>
		<tr>
			<th scope="col">First name</th>
			<th scope="col">Last name</th>
			<th scope="col">Username</th>
			<th scope="col">Action</th>
		</tr>
		
		</thead>
		<tbody>
		<%
			for(SupportDTO u : userBean.getAllSupports()) {
				out.println("<tr>" +
				"<td>"+ u.getFirstName()+"</td>"+
				"<td>"+ u.getLastName()+"</td>"+
				"<td>"+ u.getUsername()+"</td>"+
				"<td><a href='?action=deleteSupport&username="+u.getUsername()+"'>Delete</a></td>"+
				"</tr>");
			}
		
		%>
		</tbody>
		</table>
		</div>
	
	</div>
	</div>
</body>
</html>