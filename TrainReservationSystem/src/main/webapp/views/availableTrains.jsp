<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Available Trains</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<style>
			button{
				width:150px;
			}
			body{
				background:url("bg2.png");
				background-size:100% 100%;
				background-attachment:fixed;
			}
			
		</style>
	</head>
	<body class="px-5">
		<c:if test="${ availableTrains.isEmpty() }">
			<div class="d-flex flex-column justify-content-center align-items-center" style="height:100%">
				<h3 class="text-danger">No Trains Found !</h3>
				<a class="btn btn-outline-success" href="/book">Back</a>
			</div>
		</c:if>
		<c:forEach items="${availableTrains}" var="availableTrain">
			
			<div class="border rounded shadow m-3 bg-white">
				<h3 class="text-center pt-2"> ${availableTrain.availabilityKey.train.name} (${availableTrain.availabilityKey.train.trainNumber}) </h3>
				<hr />
				<div class="d-flex justify-content-around">
					<div class="h4">${availableTrain.availabilityKey.train.source}</div>
					<div class="h4">${availableTrain.availabilityKey.train.destination}</div>
				</div>
				<div class="d-flex p-2">
		
					<c:if test="${ availableTrain.slSeats>=0 }">
						<button class="d-flex border rounded flex-column border p-2 m-2"  onclick="location.href='/passenger_details?train=${availableTrain.availabilityKey.train.trainNumber}&coach=sl&doj=${availableTrain.availabilityKey.DOJ}'">
							<div class="fw-bold">SL</div>
							<div class="text-primary">AVL. ${availableTrain.slSeats}</div>
							<div class="text-success">Rs. ${availableTrain.availabilityKey.train.slPrice}</div>
						</button>
					</c:if>
					
					<c:if test="${ availableTrain.ac1Seats>=0 }">
						<button class="d-flex border rounded flex-column border p-2 m-2"  onclick="location.href='/passenger_details?train=${availableTrain.availabilityKey.train.trainNumber}&coach=ac1&doj=${availableTrain.availabilityKey.DOJ}'">
							<div class="fw-bold">1A</div>
							<div class="text-primary">AVL. ${availableTrain.ac1Seats}</div>
							<div class="text-success">Rs. ${availableTrain.availabilityKey.train.ac1Price}</div>
						</button>
					</c:if>
					
					<c:if test="${ availableTrain.ac2Seats>=0 }">
						<button class="d-flex border rounded flex-column border p-2 m-2"  onclick="location.href='/passenger_details?train=${availableTrain.availabilityKey.train.trainNumber}&coach=ac2&doj=${availableTrain.availabilityKey.DOJ}'">
							<div class="fw-bold">2A</div>
							<div class="text-primary">AVL. ${availableTrain.ac2Seats}</div>
							<div class="text-success">Rs. ${availableTrain.availabilityKey.train.ac2Price}</div>
						</button>
					</c:if>
					
					<c:if test="${ availableTrain.ac3Seats>=0 }">
						<button class="d-flex border rounded flex-column border p-2 m-2" onclick="location.href='/passenger_details?train=${availableTrain.availabilityKey.train.trainNumber}&coach=ac3&doj=${availableTrain.availabilityKey.DOJ}'">
							<div class="fw-bold">3A</div>
							<div class="text-primary">AVL. ${availableTrain.ac3Seats}</div>
							<div class="text-success">Rs. ${availableTrain.availabilityKey.train.ac3Price}</div>
						</button>
					</c:if>
					
				</div>
			</div>
		</c:forEach>
		
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>