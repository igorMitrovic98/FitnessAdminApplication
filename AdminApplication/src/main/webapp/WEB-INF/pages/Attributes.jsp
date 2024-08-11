<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="models.dtos.AttributeDTO"%>
<%@page import="models.beans.AttributeBean"%>
<%@page import="models.daos.AttributeDAO"%>
<%@page import="java.util.*"%>
<jsp:useBean id="attributeBean" class="models.beans.AttributeBean" scope="session"/>
    <%
    Object admin = session.getAttribute("admin");
    if (admin == null)
    	response.sendRedirect("Error.jsp");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-">
<title>Attributes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
	<%
List<AttributeDTO> attributes = new ArrayList<>();
String categoryName = request.getParameter("name");
if (categoryName != null) {
	session.setAttribute("categoryName", categoryName);
	attributes = attributeBean.getAllAttributes(categoryName);
}
session.setAttribute("msg", "");
if (request.getParameter("submit") != null) {
    String name = request.getParameter("aName");
    
    AttributeDAO.insert(new AttributeDTO(name, session.getAttribute("categoryName").toString()));
    session.setAttribute("msg", "Created successfully.");
}

%>

	<div class="container-fluid justify-content-center">
		<div class="row align-items-center">
    	<div class="col-md-3" style="padding: 25px;">
        	<h3>Create a new attribute</h3>
        	<form action="?a=true&name=${categoryName}" method="POST">
            	<input required class="form-control" name="aName" placeholder="Name" autocomplete="off" type="text" />
            	<br>
            	<button class="w-100 btn btn-lg btn-primary" name="submit" type="submit">Create</button>
        <%=session.getAttribute("msg").toString() %>
        </form>
    	</div>
		<div class="col-md-9 align-items-center">
        <h2>All attributes</h2>
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Actions</th>
        </tr>
        
        </thead>
        <tbody>
                <%
            for(AttributeDTO atr : attributeBean.getAllAttributes(categoryName)) {
                out.println("<tr>" +
                "<td>"+ atr.getName()+"</td>"+
                "<td><a href='?action=deleteAttribute&name="+categoryName+"&atrName="+atr.getName()+"'>Delete</a></td>"+
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