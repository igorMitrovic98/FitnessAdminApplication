<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-In</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
<style>
	.form-signin {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: auto;
  }
  .patterns:focus::placeholder {
    color: #6c757d;
  }
  body{
  	display: flex;
  	flex-direction: column;
  	height: 100vh;
  }
  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }
   .con{
    width: 100%;
    height: 100%;
    justify-content: center;
    align-items: center;
    display: flex;
    
  }
</style>
</head>
<body class="container-fluid">
		<div class="contaiter-fluid con">
		<div class="form-signin">
		<h2 style="text-align:center">Sign into your admin account</h2>
		<form action="?action=login" method="post">
		 <input type="text" class="form-control" name="username"  autocomplete="off" placeholder="Username" >
          <br>
           <input style="margin-bottom: 10px;" type="password" name="password" class="form-control" placeholder="Password">
          <br>
          <button class="w-100 btn btn-lg btn-primary" name="submit" type="submit">Sign in</button>
          <%=session.getAttribute("notify").toString()%>
         </form>
         </div>
         </div>

</body>
</html>