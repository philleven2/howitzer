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

<title>View History</title>

<!-- JS dependencies -->
<script src="static/js/jquery-3.4.1.min.js"></script>
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
				style="margin-top: 20px; font-size: 10px; width: 100% !important;">

				<tbody>

					<tr style="background: menu">
						<td colspan="10" class="font-weight-bold"><img
							src="static/image/smart.jpg" alt="Howitzer" width="100"
							height="43"> &nbsp;&nbsp;View History</td>
					</tr>

					<tr>
						<td>User id</td>
						<td align="right">Distance to target (meters)</td>
						<td align="right">Angle (radians)</td>
						<td align="right">Velocity (meters/sec)</td>
						<td align="right">Target diameter (meters)</td>
						<td>Result</td>
						<td align="right">Distance traveled (meters)</td>
						<td align="right">Distance missed by (meters)</td>
						<td align="right">Time (sec)</td>
						<td align="right">Time Stamp</td>
					</tr>

					<c:if test="${not empty history}">

						<c:forEach var="hist" items="${history}">

							<tr>
								<td valign="top">${hist.userId}</td>
								<td valign="top" align="right">${hist.distanceToTarget}</td>
								<td valign="top" align="right">${hist.angle}</td>
								<td valign="top" align="right">${hist.velocity}</td>
								<td valign="top" align="right">${hist.targetSize}</td>
								<td valign="top">${hist.result}</td>
								<td valign="top" align="right">${hist.distanceTraveled}</td>
								<td valign="top" align="right">${hist.distanceMissedBy}</td>
								<td valign="top" align="right">${hist.timeTraveled}</td>
								<td valign="top" align="right" width="15%">${hist.timeStamp}</td>
							</tr>

						</c:forEach>

					</c:if>

					<c:if test="${empty history}">

						<tr>
							<td colspan="10">No records found.</td>
						<tr>
					</c:if>

					<tr style="background: menu">
						<td colspan="10">
							<!-- Back -->
							<button class="btn btn-sm btn-primary"
								onclick="location.href='back'; return false;">Back</button> <!-- Page up -->
							<button class="btn btn-sm btn-primary" name="btnPageUp"
								onclick="pageUp(); return false;">Page Up</button> <!-- Page down -->
							<button class="btn btn-sm btn-primary" name="btnPageDown"
								onclick="pageDown(); return false;">Page Down</button>
						</td>
					</tr>

				</tbody>

			</table>

		</form>

		<div id="MsgDiv" class="msgDiv">${msg}</div>

	</div>

	<script type="text/javascript">
<!--

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

	window.location = "/howitzer/history?mv=PageUp&fromRow=<%= fromRow %>&toRow=<%= toRow %>";
	
}

// Page down
function pageDown() {

	window.location = "/howitzer/history?mv=PageDown&fromRow=<%= fromRow %>&toRow=<%= toRow %>";

}

-->	
</script>

</body>

</html>