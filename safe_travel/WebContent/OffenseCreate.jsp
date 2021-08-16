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
<title>Create a Offense</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron">
	<h1>Create Offense</h1>
	</div>
	<form action="offensecreate" method="post">
		<p>
			<h2><label for="offenseid">OffenseId</label></h2>
			<input id="offenseid" name="offenseid" value="">
		</p>
		<p>
			<h2><label for="offensecode">OffenseCode</label></h2>
			<input id="offensecode" name="offensecode" value="">
		</p>
		<p>
			<h2><label for="offense">Offense</label></h2>
			<input id="offense" name="offense" value="">
		</p>
		<p>
			<h2><label for="offenseparentgroup">OffenseParentGroup</label></h2>
			<input id="offenseparentgroup" name="offenseparentgroup" value="">
		</p>
		<p>
			<h2><label for="crimeagainstcategory">CrimeAgainstCategory</label></h2>
			<input id="crimeagainstcategory" name="crimeagainstcategory" value="">
		</p>
		<p>
			<h2><label for="beat">Beat</label></h2>
			<input id="beat" name="beat" value="">
		</p>
		<p>
			<h2><label for="precinct">Precinct</label></h2>
			<input id="precinct" name="precinct" value="">
		</p>
		<p>
			<h2><label for="neighborhoodid">NeighborhoodId</label></h2>
			<input id="neighborhoodid" name="neighborhoodid" value="">
		</p>
		<p>
			<h2><label for="mcpp">MCPP</label></h2>
			<input id="mcpp" name="mcpp" value="">
		</p>
		<p>
			<h2><label for="hundredblockaddress">HundredBlockAddress</label></h2>
			<input id="hundredblockaddress" name="hundredblockaddress" value="">
		</p>
		<p>
			<h2><label for="latitude">Latitude</label></h2>
			<input id="latitude" name="latitude" value="">
		</p>
		<p>
			<h2><label for="longitude">Longitude</label></h2>
			<input id="longitude" name="longitude" value="">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
		</p>
	</form>
	<br/><br/>
	<p>
		<div class="alert alert-success" role="alert">
		<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</p>
	
	</div>
	
	<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>