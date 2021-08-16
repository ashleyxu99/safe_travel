<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find an Offense</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
	<form action="findoffenses" method="post">
	    <div class="jumbotron">
		<h1>Search for an Offense</h1>
		</div>
		<p>
			<h2><label for="offenseid">OffenseId</label></h2>
			<input id="offenseid" name="offenseid" value="${fn:escapeXml(param.offenseid)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<div id="offenseCreate"><h3><a href="offensecreate">Create Offense</a></h3></div>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching Offenses</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>OffenseId</th>
                <th>ReportDateTime</th>
                <th>OffenseCode</th>
                <th>Offense</th>
                <th>OffenseParentGroup</th>
                <th>NeighborhoodId</th>
                <th>MCPP</th>
                <th>Beat</th>
                <th>Precinct</th>
                <th>HundredBlockAddress</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Delete Offense</th>
                <th>Update Offense</th>
            </tr></thead>
            <c:forEach items="${offenses}" var="offense" >
                <tbody><tr>
                    <td><c:out value="${offense.getOffenseId()}" /></td>
                    <td><c:out value="${offense.getReportDateTime()}" /></td>
                    <td><c:out value="${offense.getOffenseType().getOffenseCode()}" /></td>
                    <td><c:out value="${offense.getOffenseType().getOffense()}" /></td>
                    <td><c:out value="${offense.getOffenseType().getOffenseParentGroup()}" /></td>
                    <td><c:out value="${offense.getNeighborhood().getNeighborhoodId()}" /></td>
                    <td><c:out value="${offense.getNeighborhood().getMCPP()}" /></td>
                    <td><c:out value="${offense.getNeighborhood().getBeat().getBeat()}" /></td>
                    <td><c:out value="${offense.getNeighborhood().getBeat().getPrecinct()}" /></td>
                    <td><c:out value="${offense.getHundredBlockAddress().getHundredBlockAddress()}" /></td>
                    <td><c:out value="${offense.getLocation().getLatitude()}" /></td>
                    <td><c:out value="${offense.getLocation().getLongitude()}" /></td>
                    <td><a href="offensedelete?offenseid=<c:out value="${offense.getOffenseId()}"/>">Delete</a></td>
                    <td><a href="offenseupdate?offenseid=<c:out value="${offense.getOffenseId()}"/>">Update</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
       
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>