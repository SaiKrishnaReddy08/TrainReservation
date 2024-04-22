<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>REGISTER</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body>
		<div class="d-flex justify-content-center align-items-center" style="height:100%">
			<form:form action="" method="post" modelAttribute="bookingDetail" class="border rounded border-secondary p-3">
				<h3 class="mb-3 fst-italic text-primary">${bookingDetail.train.name}(${bookingDetail.train.trainNumber})</h3>
				<hr>
				<div class="row">
					<p class="col text-center fw-bold">COACH</p>
					<p class="col">${ bookingDetail.coach }</p>
				</div>
				<div class="row">
					<p class="col text-center fw-bold"> DOJ  </p> 
					<p class="col">${ bookingDetail.DOJ }</p>
				</div>
				<div class="row">
					<p class="col text-center fw-bold"> No. of Passengers   </p> 
					<p class="col"><form:input type="number" path="noOfSeats"/></p>
				</div>
				
				
   				<form:input type="hidden" path="DOJ" value="${ doj }" />
				<from:input type="hidden" path="coach" value="${ coach }" />
				
				<div class="d-flex justify-content-center">
					<input type="submit" value="OK" style="width:120px"/>
				</div>
				
			</form:form>
			<h6 class="text-danger text-center mt-3">${ invalid }</h6>
		</div>
		
		
		
	</body>
</html>