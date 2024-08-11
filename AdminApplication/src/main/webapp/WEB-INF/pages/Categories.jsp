<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="categoryBean" class="models.beans.CategoryBean" scope="session"/>
<%@page import="models.dtos.CategoryDTO"%>
<%@page import="models.dtos.AdminDTO"%>
<%@page import="models.beans.CategoryBean"%>
<%@page import="models.daos.CategoryDAO"%>

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
<title>Categories</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<%
session.setAttribute("msg", "");
if (request.getParameter("submit") != null) {
    String name = request.getParameter("name");
    CategoryDAO.insert(new CategoryDTO(name));
    session.setAttribute("msg", "Created successfully.");
}
%>
	<div class="container-fluid justify-content-center">
		<div class="row align-items-center">
    	<div class="col-md-3" style="padding: 25px;">
        	<h3>Create a new category</h3>
        	<form action="?action=categories" method="POST">
            	<input required class="form-control" name="name" placeholder="Name" autocomplete="off" type="text" />
            	<br>
            	<button class="w-100 btn btn-lg btn-primary" name="submit" type="submit">Create</button>
        <%=session.getAttribute("msg").toString() %>
        </form>
    	</div>
		<div class="col-md-9 align-items-center">
        <h2>All categories</h2>
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Actions</th>
        </tr>
        
        </thead>
        <tbody>
        <%
            for(CategoryDTO category : categoryBean.getAllCategories()) {
                out.println("<tr>" +
                "<td>"+ category.getName()+"</td>"+
                "<td><a target='_blank' href='?a=true&name="+category.getName()+"'>Open Attributes</a> | <a href='?action=deleteCaterogy&name="+category.getName()+"'>Delete</a></td>"+
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