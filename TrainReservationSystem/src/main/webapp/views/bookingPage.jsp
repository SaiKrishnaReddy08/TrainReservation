<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<style>
			form div{
				margin:15px 5px;
			}
			body{
				background:url("bg2.png");
				background-size:100% 100%;
			}
		</style>
		<title>Booking</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body class="d-flex justify-content-center align-items-center">
	
		<div class="container border border-secondary bg-secondary rounded mx-auto" style="width:50%;box-shadow:0 6px 8px 0 black,-0 -6px 8px 0 black;">
			<form action="trains" method="post">
				<div>
					<p class="fw-bold h5 text-light mb-1 mx-2">From</p>
					<input class="form-control" name="source" list="stations" />
					<datalist id="stations">
						<c:forEach items="${ stations }" var="station">
							<option value="${ station }">${ station }</option>
						</c:forEach>
					</datalist>
					
				</div>
				
				<div>
					<p class="fw-bold h5 text-light mb-1 mx-2">To</p>
					<input class="form-control" name="destination" list="stations" />
					
				</div> 
				
				<div>
					 <p class="fw-bold h5 text-light mb-1 mx-2">Date</p>
					<input class="form-control" type="date" name="doj" required/>  
				</div>
				
				<div class="d-flex justify-content-center">
					<input type="submit" value="Show Trains" class="btn btn-info text-light fw-bold">
				</div>
			</form>
		</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>