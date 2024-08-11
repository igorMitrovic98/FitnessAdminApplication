<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="categoryBean" class="models.beans.CategoryBean" scope="session"/>
<%@page import="models.dtos.CategoryDTO"%>
<%@page import="models.dtos.AdminDTO"%>
<%@page import="models.beans.CategoryBean"%>
<%@page import="models.daos.CategoryDAO"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>

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
<title>Statistics</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
	<div style="flex-direction:column; align-items:center;" class="container-fluid d-flex justify-content-center">
<h1 class="text-center">Logs file for Fitness World</h1>
<hr>
	<div style="display: grid; place-items: center;">

<%
	BufferedReader reader = new BufferedReader(new FileReader("C:/Users/PC/IdeaProjects/FitnessAppBackEnd/logs/spring.log"));
    StringBuilder sb = new StringBuilder();
    String line;
    while((line = reader.readLine())!= null){
    	sb.append(line+"</br>");
	}
    out.println(sb.toString());
    reader.close();
%>
	</div>
	</div>
</body>
</html>