<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<style>
			form>div{
				margin:10px;
			}
			body{
				background:url(bg.jpg);
				background-size:100% 100%;
			}
			form{
				box-shadow:0px 0px 6px 2px white;
			}
			
		</style>
		<title>REGISTER</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 			crossorigin="anonymous">
	</head>
	<body>
		<div class="d-flex justify-content-center align-items-center" style="width:100%;height:100%;">
		
		<form:form method="post" autocomplete="off" modelAttribute="user" cssClass="p-3 border rounded d-flex flex row justify-content-center align-items-center" style="width:80%;height:95%" >
			<h3 class="text-center text-light">Register</h3>
			<div class="row">
				<div class="col text-light">
					Full Name
					<form:input type="text" path="fullName" autocomplete="off" cssClass="form-control" placeholder="Enter the Full Name" />
					<form:errors path="fullName" cssClass="text-danger"></form:errors>
				</div>
				<div class="col text-light">
					Age
					<form:input type="number" path="age" cssClass="form-control" placeholder="Enter the Age" />
					<form:errors path="age" cssClass="text-danger"></form:errors>
				</div>
			</div>
					
					
			<div class="row gap-2">
				<div class="col text-light">
					Gender:
					<div class="input-form border rounded d-flex justify-content-between" style="padding:6px">
						<div><form:radiobutton path="gender" value="male" /> Male</div>
						<div><form:radiobutton path="gender" value="female" /> Female</div>
						<div><form:radiobutton path="gender" value="others" /> Others</div>
					</div>
					<form:errors path="gender" cssClass="text-danger"></form:errors>
				</div>
				<div class="col text-light">
					Phone Number
					<form:input type="text" path="phoneNumber" autocomplete="off" cssClass="form-control" placeholder="Enter the Phone Number" />
					<form:errors path="phoneNumber" cssClass="text-danger"></form:errors>
					<p class="text-danger">${ registeredPhone }</p>
				</div>
			</div>		
					
						
			<div class="col text-light">
				Address
				<form:input type="text" path="address" autocomplete="off" cssClass="form-control" placeholder="Enter the Address" />
				<form:errors path="address" cssClass="text-danger"></form:errors>
			</div>
			
			<div class="row">
				<div class="col text-light">
					User ID
					<form:input type="text" path="userId" autocomplete="off" cssClass="form-control " placeholder="Enter the User ID" />
					<form:errors path="userId" cssClass="text-danger"></form:errors>
					<p class="text-danger">${ idTaken }</p>
				</div>
				<div class="col text-light">
					Password
					<form:input type="password" path="password" cssClass="form-control" placeholder="Enter the Password" />
					<form:errors path="password" cssClass="text-danger"></form:errors>
				</div>
			</div>
						
			
			<div class="d-flex justify-content-center align-items-center">
				<input type="submit" value="Register" class="btn btn-success fw-bold" style="width:30%"/>
			</div>
			
			<div class="my-1 fw-bold h6 d-flex justify-content-center text-light">
				Already have an account? <a href="/login" class="mx-2 text-light" >Login</a>
			</div>
			
		</form:form>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>