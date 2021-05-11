<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<%
// First row
String firstRow = (String) request.getAttribute("firstRow");

String msg = "";
%>

<html lang="en">

<head>

<!-- The below 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS dependencies -->
<link href="static/theme/bootstrap.min.css" rel="stylesheet">
<link href="static/theme/custom.css" rel="stylesheet">
    
<title>Add User</title>

<!-- JS dependencies -->
<script src="static/js/jquery-3.6.0.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<script>

	$.getScript("static/js/trimAll.js");
	
</script>

</head>

<body>

<div class="container-fluid">

	<h5 class="col-md-offset-1">
		<img src="static/image/savvas.jpg" alt="Howitzer" width="100" height="43">
		<small class="text-muted">Add User</small>			
	</h5>

	<br>

	<form:form class="form small" name="frmAdd" method="POST" action="addUser" modelAttribute="user">

		<div class="form-group row">
			<label class="control-label col-sm-1" for="userId">User Id:</label>
			<div class="col-sm-4">
				<form:input path="userId" name="userId" id="userId" type="text" placeholder="Enter user id" maxlength="50"
					 class="form-control form-control-sm shadow"/>
			</div>
		</div>
		
		
		<div class="form-group row"> 
    		<div class="col-sm-offset-1 col-sm-10">
    			
				<!-- Save -->
				<button name="btnSave" class="btn btn-sm btn-primary" onclick="submitForm(this); return false;">
				Save
				</button>
				
				<!-- Back -->
				<button class="btn btn-sm btn-primary" onclick="location.href='/howitzer/users?firstRow=<%= firstRow %>'; return false;">Back</button>

			</div>
		</div>

		<div class="form-group row"> 
    		<div class="col-sm-offset-2 col-sm-10">

				<!-- Container for error message -->
				<div id="MsgDiv" class="msgDiv">
				</div>

			</div>
		
		</div>
		
		<input name="firstRow" type="hidden" value="<%= firstRow %>">
		
	</form:form>

	<div id="MsgDiv" class="msgDiv">
		${errorMsg}
	</div>
	
</div>

<script>

// Cursor on user id
document.frmAdd.userId.focus();

// Submit form
function submitForm(e) {

	// Edit form
	if (validate_form() == true) {

		// If no error
		// Submit form
		frmAdd.submit();

	}

}

// Edit form
function validate_form() {

	document.frmAdd.userId.style.backgroundColor = "white";

	// Edit user id
	var userId = trimAll(document.frmAdd.userId.value);
	
	if (userId == "") {

		// Display error message
		$("#MsgDiv").text("Error: Invalid user id.");

		document.frmAdd.userId.style.backgroundColor = "#FFFFCC";
		document.frmAdd.userId.focus();
		return false;

	}

	if (/^[a-zA-Z0-9]*$/.test(userId) == false) {
		
		// Display error message
		$("#MsgDiv").text("Error: Invalid user id.  No special characters allowed.");

		document.frmAdd.userId.style.backgroundColor = "#FFFFCC";
		document.frmAdd.userId.focus();
		return false;

	}

	return true;

}

// Display error message
$("#MsgDiv").text("<%= msg %>");
	
</script>

</body>

</html>