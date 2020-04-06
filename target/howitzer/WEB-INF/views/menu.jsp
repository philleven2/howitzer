<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">

<head>

<!-- The below 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS dependencies -->
<link href="static/theme/bootstrap.min.css" rel="stylesheet">
<link href="static/theme/custom.css" rel="stylesheet">

<title>Howitzer</title>

<!-- JS dependencies -->
<script src="static/js/jquery-3.4.1.min.js"></script>
<script src="static/js/popper.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/bootbox.min.js"></script>

<%
String selUserId = (String) request.getAttribute("selUserId");
%>

<script type="text/javascript">
<!--

	$.getScript("static/js/trimAll.js");
	
	$("#MsgDiv").empty();
	
-->
</script>

</head>

<body>

	<nav class="navbar navbar-light py-0 justify-content-start">
	
		<span class="navbar-brand navbar-text"><img src="static/image/smart.jpg" alt="HOwitzer" width="100" height="43">
			&nbsp;&nbsp; Howitzer v1.0.0
		</span>          

		<form class="form-inline" name="frmUsers" action="users" method="GET">
			<button class="btn btn-sm btn-default" type="submit"
				data-toggle="tooltip" title="View users.">Users</button>
		</form>

		<form class="form-inline" name="frmHistory" action="history"
			method="GET">
			<button class="btn btn-sm btn-default" type="submit"
				data-toggle="tooltip" title="View history.">History</button>
		</form>

		<form class="form-inline" name="frmRefresh" action="back" method="GET">
			<button class="btn btn-sm btn-default" type="submit"
				data-toggle="tooltip" title="Refresh dashboard.">Refresh</button>
		</form>

		<form class="form-inline" name="frmLogout" action="login" method="GET">
			<input type="hidden" name="logout" value="logout" />
			<button class="btn btn-sm btn-default" type="submit"
				data-toggle="tooltip" title="Logout.">Logout</button>
		</form>

	</nav>


	<div class="container-fluid" style="margin-top: 20px;">

		<h6>
			<span class="badge badge-primary">Fire Shot</span>
		</h6>

		<form:form class="form small" name="frmUpdate" method="POST"
			action="updateFireShot" modelAttribute="fireShot">

			<div class="form-group row">
				<label class="control-label col-sm-1">User id:</label>
				<div class="col-sm-1">
					<select class="selectpicker shadow" name="userId">
						<option value="ALL"></option>

						<c:forEach var="users" items="${users}">

							<!-- If selected user -->
							<c:if test="${users.userId == selUserId}">

								<option value=<c:out value="${users.userId}"/> selected>${users.userId}</option>

							</c:if>

							<!-- If not selected user -->
							<c:if test="${users.userId != selUserId}">

								<option value=<c:out value="${users.userId}"/>>${users.userId}</option>

							</c:if>

						</c:forEach>

					</select>
				</div>

				<label class="control-label col-sm-2" for="distanceToTraget">Distance
					to target (meters):</label>
				<div class="col-sm-2">
					<form:input path="distanceToTarget" name="distanceToTarget"
						id="distanceToTarget" type="text"
						placeholder="Enter distance to target." maxlength="11"
						class="form-control form-control-sm shadow" />
				</div>

				<label class="control-label col-sm-2" for="velocity">Velocity
					(meters/second):</label>
				<div class="col-sm-1">
					<form:input path="velocity" name="velocity" id="velocity"
						type="text" placeholder="Enter velocity." maxlength="11"
						class="form-control form-control-sm shadow" />
				</div>
			</div>

			<div class="form-group row">
				<label class="control-label col-sm-1" for="angle">Angle
					(&deg;):</label>
				<div class="col-sm-1">
					<form:input path="angle" name="angle" id="angle" type="text"
						placeholder="Enter angle." maxlength="3"
						class="form-control form-control-sm shadow" />
				</div>

				<label class="control-label col-sm-2" for="targetSize">Target
					diameter (meters):</label>
				<div class="col-sm-2">
					<form:input path="targetSize" name="targetSize" id="targetSize"
						type="text" placeholder="Enter target diameter." maxlength="11"
						class="form-control form-control-sm shadow" />
				</div>

			</div>

			<c:if test="${not empty trajectory}">

				<h6>
					<span class="badge badge-primary">Results</span>
				</h6>

				<div class="form-group row">
					<label class="control-label col-sm-1">Result:</label>
					<div class="col-sm-1">
						<input class="form-control form-control-sm shadow" type="text"
							placeholder="${result}" disabled>
					</div>

					<label class="control-label col-sm-2">Traveled:</label>
					<div class="col-sm-2">
						<input class="form-control form-control-sm shadow" type="text"
							placeholder="${distanceTraveled}" disabled>
					</div>

					<label class="control-label col-sm-2">Missed By:</label>
					<div class="col-sm-2">
						<input class="form-control form-control-sm shadow" type="text"
							placeholder="${distanceMissedBy}" disabled>
					</div>
				</div>

				<div class="form-group row">
					<label class="control-label col-sm-1">Time:</label>
					<div class="col-sm-1">
						<input class="form-control form-control-sm shadow" type="text"
							placeholder="${timeTraveled}" disabled>
					</div>

					<label class="control-label col-sm-2">Rank:</label>
					<div class="col-sm-1">
						<input class="form-control form-control-sm shadow" type="text"
							placeholder="${rank}" disabled>
					</div>

				</div>

			</c:if>

			<div class="form-group row">
				<div class="col-sm-offset-1 col-sm-10">

					<!-- Fire -->
					<button name="btnFire" class="btn btn-sm btn-primary"
						onclick="submitForm(this); return false;">Fire</button>

				</div>
			</div>

			<!-- Container for error message -->
			<div id="MsgDiv" class="msgDiv" onclick="hideDiv()">${msg}</div>

			<br>
			<h6>Assumptions:</h6>
			<h6>1. The earth is flat.</h6>
			<h6>2. There is no air resistance.</h6>
			<h6>3. The howitzer and target are at sea level.</h6>
			<h6>4. The howitzer is pointed directy at the target.</h6>

			<c:if test="${not empty trajectory}">

				<h6>
					<span class="badge badge-primary">Trajectory</span>
				</h6>

				<div class="form-group">
					<div class="col-sm-10">${trajectory}</div>
				</div>

			</c:if>

		</form:form>

	</div>

	<script type="text/javascript">
	<!--
	
	// Cursor on user id
	document.frmUpdate.userId.focus();

	// Tool tips
	$(document).ready(function() {
		
		  $('[data-toggle="tooltip"]').tooltip(); 
		  
	});
	
	// Confirm selection
	function confirmSelection (form, msg) {
		
		bootbox.confirm(msg, function(result) {
			
			if (result == true) {
				
				form.submit();
				
			}
			
	    });
		
	}
	
	// Hide MsgDiv
	function hideDiv() {
		
		document.getElementById("MsgDiv").style.display = "none";
		
	}
	
	// Show MsgDiv
	function showDiv() {
		
		document.getElementById("MsgDiv").style.display = "block";
		
	}
	
	// Show MsgDiv
	showDiv();
	
	// Submit form
	function submitForm(e) {
	
		// Edit form
		if (validate_form() == true) {
	
			// If no error
			// Submit form
			frmUpdate.submit();
	
		}
	
	}

	// Edit form
	function validate_form() {

		document.frmUpdate.userId.style.backgroundColor = "white";
		document.frmUpdate.distanceToTarget.style.backgroundColor = "white";
		document.frmUpdate.velocity.style.backgroundColor = "white";
		document.frmUpdate.angle.style.backgroundColor = "white";
		document.frmUpdate.targetSize.style.backgroundColor = "white";

		// Edit user  id
		if (trimAll(document.frmUpdate.userId.value) == "All") {
	
			// Display error message
			$("#MsgDiv").text("Error: Invalid user id.  Pleaseselect a user id.");
	
			document.frmUpdate.userId.style.backgroundColor = "#FFFFCC";
			document.frmUpdate.userId.focus();
			return false;
	
		}

		// Edit distance to target 
		if (trimAll(document.frmUpdate.distanceToTarget.value) == "") {
	
			// Highlight error
			document.frmUpdate.distanceToTarget.style.backgroundColor = "#FFFFCC";
	
			// Display error message
			$("#MsgDiv").text("Error: Invalid distance to target.");
	
			document.frmUpdate.distanceToTarget.focus();
			return false;
				
		}
		
		if (isNaN(document.frmUpdate.distanceToTarget.value)
			|| document.frmUpdate.distanceToTarget.value <= 0		
			|| document.frmUpdate.distanceToTarget.value > 999999) {		
						
			// Highlight error
			document.frmUpdate.distanceToTarget.style.backgroundColor = "#FFFFCC";
						
			// Display error message
			$("#MsgDiv").text("Error: Invalid distanceToTarget.  Must be between 0 and 999999 meters.");
	
			document.frmUpdate.distanceToTarget.focus();
			return false;
			
		}

		// Edit velocity 
		if (trimAll(document.frmUpdate.velocity.value) == "") {
	
			// Highlight error
			document.frmUpdate.velocity.style.backgroundColor = "#FFFFCC";
	
			// Display error message
			$("#MsgDiv").text("Error: Invalid velocity.");
	
			document.frmUpdate.velocity.focus();
			return false;
				
		}
		
		if (isNaN(document.frmUpdate.velocity.value)
			|| document.frmUpdate.velocity.value <= 0		
			|| document.frmUpdate.velocity.value > 999999) {		
						
			// Highlight error
			document.frmUpdate.velocity.style.backgroundColor = "#FFFFCC";
						
			// Display error message
			$("#MsgDiv").text("Error: Invalid velocity.  Must be between 0 and 999999 meters.");
	
			document.frmUpdate.velocity.focus();
			return false;
			
		}

		// Edit angle 
		if (trimAll(document.frmUpdate.angle.value) == "") {
	
			// Highlight error
			document.frmUpdate.angle.style.backgroundColor = "#FFFFCC";
	
			// Display error message
			$("#MsgDiv").text("Error: Invalid angle.");
	
			document.frmUpdate.angle.focus();
			return false;
				
		}
		
		if (isNaN(document.frmUpdate.angle.value)
			|| document.frmUpdate.angle.value <= 0		
			|| document.frmUpdate.angle.value > 89) {		
						
			// Highlight error
			document.frmUpdate.angle.style.backgroundColor = "#FFFFCC";
						
			// Display error message
			$("#MsgDiv").text("Error: Invalid angle.  Must be between 0 and 89 degrees.");
	
			document.frmUpdate.angle.focus();
			return false;
			
		}

		// Edit target diameter 
		if (trimAll(document.frmUpdate.targetSize.value) == "") {
	
			// Highlight error
			document.frmUpdate.targetSize.style.backgroundColor = "#FFFFCC";
	
			// Display error message
			$("#MsgDiv").text("Error: Invalid target diameter.");
	
			document.frmUpdate.targetSize.focus();
			return false;
				
		}
		
		if (isNaN(document.frmUpdate.targetSize.value)
			|| document.frmUpdate.targetSize.value <= 0		
			|| document.frmUpdate.targetSize.value > 100) {		
						
			// Highlight error
			document.frmUpdate.targetSize.style.backgroundColor = "#FFFFCC";
						
			// Display error message
			$("#MsgDiv").text("Error: Invalid target diameter.  Must be between 0 and 100 meters.");
	
			document.frmUpdate.targetSize.focus();
			return false;
			
		}

		return true;
	
	}

	-->	
	</script>

</body>

</html>