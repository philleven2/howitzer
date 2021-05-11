<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<!-- The below 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS dependencies -->
<link href="static/theme/bootstrap.min.css" rel="stylesheet">
<link href="static/theme/custom.css" rel="stylesheet">

<title>View Users</title>

<!-- JS dependencies -->
<script src="static/js/jquery-3.6.0.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<%
// Page size
String pagSize = (String) request.getAttribute("pagSize");
int pagSiz = Integer.parseInt(pagSize);

// Number of rows
String nbrRows = (String) request.getAttribute("nbrRows");
int nbrRws = Integer.parseInt(nbrRows);

// From row
String fromRow = (String) request.getAttribute("fromRow");
int fromRw = Integer.parseInt(fromRow);

// To row
String toRow = (String) request.getAttribute("toRow");
int toRw = Integer.parseInt(toRow);
%>

</head>

<body>

	<div class="container-fluid">

		<form class="form small" name="frmView">

			<table class="table table-striped custom"
				style="margin-top: 20px; font-size: 10px; width: 60% !important;">

				<tbody>

					<tr style="background: menu">
						<td colspan="6" class="font-weight-bold"><img
							src="static/image/smart.jpg" alt="Howitzer" width="100"
							height="43"> &nbsp;&nbsp;View Users</td>
					</tr>

					<tr>
						<td>User id</td>
						<td align="right">Shots</td>
						<td align="right">Hits</td>
						<td align="right">Misses</td>
						<td align="right">Average hits</td>
						<td align="right">Rank</td>
					</tr>

					<c:if test="${not empty users}">

						<c:forEach var="user" items="${users}">

							<tr>
								<td valign="top">${user.userId}</td>
								<td valign="top" align="right">${user.shots}</td>
								<td valign="top" align="right">${user.hits}</td>
								<td valign="top" align="right">${user.misses}</td>
								<td valign="top" align="right">${user.avgHits}</td>
								<td valign="top" align="right">${user.rank}</td>
							</tr>

						</c:forEach>

					</c:if>

					<c:if test="${empty users}">

						<tr>
							<td colspan="6">No records found.</td>
						<tr>
					</c:if>

					<tr style="background: menu">
						<td colspan="6">
							<!-- Back -->
							<button class="btn btn-sm btn-primary"
								onclick="location.href='back'; return false;">Back</button> 
								
							<!-- Add -->
							<button class="btn btn-sm btn-primary"
								onclick="location.href='user?firstRow=<%= fromRow %>'; return false;">Add</button>
								
							<!-- Page up -->
							<button class="btn btn-sm btn-primary" name="btnPageUp"
								onclick="pageUp(); return false;">Page Up</button> 
								
							<!-- Page down -->
							<button class="btn btn-sm btn-primary" name="btnPageDown"
								onclick="pageDown(); return false;">Page Down</button>
						</td>
					</tr>

				</tbody>

			</table>

		</form>

		<div id="MsgDiv" class="msgDiv">${msg}</div>

	</div>

	<script>

	// Submit form
	function submitForm(e) {
	
		// Submit form
		frmSearch.submit();
	
	}
	
	// Enable/disable page up
	if (<%= fromRw %> > <%= pagSiz - 1 %>) {
	
		document.frmView.btnPageUp.disabled = false;
		
	} else {
	
		document.frmView.btnPageUp.disabled = true;
	
	}
	
	// Enable/disable page down
	if (<%= toRw %> < <%= nbrRws %>) {
	
		document.frmView.btnPageDown.disabled = false;
		
	} else {
	
	
		document.frmView.btnPageDown.disabled = true;
	
	}
	
	// Page up
	function pageUp() {
	
		window.location = "/howitzer/users?mv=PageUp&fromRow=<%= fromRow %>&toRow=<%= toRow %>";
		
	}
	
	// Page down
	function pageDown() {
	
		window.location = "/howitzer/users?mv=PageDown&fromRow=<%= fromRow %>&toRow=<%= toRow %>";
	
	}

</script>

</body>

</html>