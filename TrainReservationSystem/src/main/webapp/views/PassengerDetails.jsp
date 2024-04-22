<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>REGISTER</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body>
		<div class="d-flex justify-content-center align-items-center" style="height:100%">
		
			<form:form action="/confirm" method="post" modelAttribute="bookingDetail">
				
				<form:input type="hidden" path="coach" value="${ coach }"/>
				<form:input type="hidden" path="DOJ" value="${ DOJ }"/>
				<form:input type="hidden" path="noOfSeats" value="${ noOfSeats }"/>
				
				
					<c:forEach var="count" begin="1" end="${ bookingDetail.noOfSeats }" >
						<div class="row p-2">
							<div class="col"><span class="m-3">Passenger ${count}</span></div>
							<div class="col"><form:input type="text" path="passengers[${count - 1}].passengerName"/></div>
						</div>
					</c:forEach>
				
				
				<div class="d-flex justify-content-center align-items-center">
					<input type="submit" value="next" class="btn btn-outline-success" style="width:90px"/>
				</div>
				
				
			</form:form>
			
		</div>

	</body>
</html>