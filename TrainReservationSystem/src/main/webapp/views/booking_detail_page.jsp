<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<style>
			.mycol > div{
				display:inline-block;
				min-width:130px;
			}
			td{
				padding:5px 10px;
			}
			
			table{
				margin-left:70px;
			}
			
		</style>
		<title>REGISTER</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body >
		<div class="d-flex justify-content-center align-items-center" style="height: 100%">
			<div class="border rounded p-3" style="width:60%">
				
				<div class="mb-4">
					<h4 class="text-center">Booking Details</h4>
				</div>
				
				<hr>
				
				<div class="gap-2 mx-auto" style="width:90%">
							
					<div class="row p-1">
						<div class="col mycol">
							<div class="fw-bold text-primary" >TRAIN NUMBER </div>
							<div>${ booking.train.trainNumber }</div>
						</div>
						<div class="col mycol">
							<div class="fw-bold text-primary">TRAIN NAME </div>
							<div>${ booking.train.name }</div>
						</div>
					</div>
					
					<div class="row p-1">
						<div class="col mycol">
							<div class="fw-bold text-primary">SOURCE </div>
							<div>${  booking.train.source }</div>
						</div>
						<div class="col mycol">
							<div class="fw-bold text-primary">DESTINATION </div>
							<div>${ booking.train.destination }</div>
						</div>
					</div>
					
					<div class="row p-1">
						<div class="col mycol">
							<div class="fw-bold text-primary">DOJ </div>
							<div>${  booking.DOJ }</div>
						</div>
						<div class="col mycol">
							<div class="fw-bold text-primary">COACH </div>
							<div>${ booking.coach }</div>
						</div>
					</div>
				
				</div>
				
				<h5 class="mx-5 mt-4 text-primary text-decoration-underline">Passenger List</h5>
				
				<table>
					<c:forEach var="count" begin="0" end="${ booking.passengers.size()-1 }">		
						<tr><td>${ count+1 }.</td><td>${ booking.passengers[count].passengerName }</td></tr>
					</c:forEach>
				</table>					
				<hr>
				
				<div class="d-flex justify-content-around mt-3">
					<div><span class="h5 text-primary">Total Fare :</span> <span class="h5 text-success">Rs.${ booking.totalFare }/-</span></div>
					<div>
						<a href="/home" class="btn btn-danger">Back To Home</a>
					</div>
				</div>
								
			</div>
		</div>
		
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>