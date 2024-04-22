<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<Style>
			body{
				background:url(bg.jpg);
				background-size:100% 100%;
			}
			
			form{
				
				border : 2px solid gray;
				border-radius: 15px;
				box-shadow: 0px 0px 6px 3px white;
				position:relative;
			}
			
			
			
		</Style>
		<title>LOGIN</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body class="d-flex justify-content-center align-items-center">
		<div style="width:40%">
			<h1 class="text-center text-primary">LOGIN</h1>
			
			<form action="" method="post" autocomplete="off" class="mx-3 px-3">
				<div class="my-2">
					<p class="h6 mb-1 ml-2 text-light">User ID </p> 
					<input class="form-control" type="text" name="userId" />  
					<p class="text-light fw-bold">${ invalidId }</p>
				</div>
				
				<div class="my-2">
					<p class="h6 mb-1 ml-2 text-light">Password</p> 
					<input class="form-control" type="password" name="password" />
					<p class="text-light fw-bold">${ invalidPwd }</p>
				</div>
				
				<div class="my-3 d-flex justify-content-center align-items-center">
					<input type="submit" value="Login" class="btn btn-success fw-bold" />
				</div>
				
				<div class="my-3 d-flex justify-content-center text-light fw-bold">
					Don't have an account? <a href="/register" class="mx-2 text-light">Register</a>
				</div>
			</form>
			
			<c:if test="${wrongDetails}">
				<p class="text-center text-light fw-bold">Wrong User ID or Password.</p>
			</c:if>
		</div>
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>