<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<style>
			body{
				background:url("bg2.png");
				background-size:100% 100%;
			}
			
			.full-height{
				height:100%;
			}
		</style>
		<title>REGISTER</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/56e2779d6d.js" crossorigin="anonymous"></script>
	</head>
	<body class="px-5">
		<c:if test="${ myBookings.size()==0 }">
			<div class="d-flex justify-content-center align-items-center full-height">
				<h3 class="text-danger bg-light p-3 border border-secondary rounded">You have no Bookings available.</h3>
			</div>
		</c:if>
		<c:forEach items="${ myBookings }" var="booking">
			<div class="border rounded m-3 p-3 px-5 bg-light shadow" onclick="location.href = '/MyBookings/${booking.bookingId}'">
				<div class="d-inline-block mx-2" style="position:relative;top:-40px">
					 <i class="fa-solid fa-train-subway" style="font-size:70px;"></i>
				</div>
				<div class="float-left d-inline-block m-3" style="width:70%">
					<div class="d-flex justify-content-between">
						<div>
							<span class="h3">${ booking.train.name }<span>
							<span class=text-secondary>(${ booking.train.trainNumber })</span>
						</div>
						<div>
							<span class="h4">PNR : </span>
							<span class="text-danger h3">${ booking.bookingId }</span>
						</div>
					</div>
					
					<div class="my-2" style="font-size:22px">
						<span class="text-uppercase">${ booking.train.source } </span>
						<i class="fa-solid fa-arrow-right-long mx-4"  style="font-size:30px;position:relative;top:5px"></i>
					
						<span class="text-uppercase">${ booking.train.destination }</span>
					</div>
					
					
					<div class="my-2">
						<span class="h5">Date of Journey :<span>
						<span class=text-secondary>${ booking.DOJ }</span>
					</div>
					
					<div class="my-2">
						<span class="h5 ">Coach :<span>
						<span class="text-secondary text-uppercase">${ booking.coach }</span>
					</div>
					
				</div>
				
			</div>
		</c:forEach>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>