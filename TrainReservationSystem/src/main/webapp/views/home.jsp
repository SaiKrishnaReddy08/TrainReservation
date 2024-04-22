<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<style>
			body{
				background-image: url("trains.jpg");
				background-size:100% 100%;
			}
			
			.bar{
				width:100%;
				position: relative;
   				top: 60%;
   				display:flex;
   				justify-content:space-around;
			}
			.bar > a{
				border:1.5px solid black;
				border-radius:10px;
				padding:20px 25px;
				text-decoration:none;
				background-color:white;
				font-weight:bold;
				color:black;
			}
		</style>
		<title>HOME</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body>
		<div>
			<c:if test="${ !loggedIn }">
				<a href="/register" class="text-decoration-none fw-bold btn btn-light float-end px-4 m-3">Register</a>
			</c:if>
			
			<c:if test="${ !loggedIn }">
				<a href="/login" class="text-decoration-none fw-bold btn btn-light float-end px-4 m-3">Login</a><br>
			</c:if>
			
			<c:if test="${ loggedIn }">
				<a href="/logout" class="text-decoration-none fw-bold btn btn-light float-end px-4 m-3">Logout</a><br>
			</c:if>
		</div>
		
		<div class="bar">
			<a href="/book" >Book a Train</a>
		
			<a href="/MyBookings">My Bookings</a>
			
			<a href="/cancellation">Cancel my Booking</a> 
		</div>
		
		
		
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>	
	</body>
</html>