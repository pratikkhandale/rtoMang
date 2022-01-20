<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.rto.controller.RTOView"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
  <title>RTO - Management</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/prettyphoto/css/prettyphoto.css" rel="stylesheet">
  <link href="lib/hover/hoverex-all.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
   <link href="css/style.css" rel="stylesheet"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <style type="text/css">
 /*Animated strreet*/
.road
{
	background: url(images/road.jpg) bottom no-repeat;
	background-size:cover;
	width: 100%;
	height: 626px;
	margin: 0 auto;
	overflow: hidden;
}

 .truck_image
{
	background: url(images/car.png) no-repeat center;
	height: 500px;
	width: 500px;
	position: relative;
	animation-name:truck_image;
	animation-duration: 10s;
	animation-timing-function:linear;
	animation-iteration-count:infinite;
	
} 
 .car_image
{
	background: url(images/carr6.png) no-repeat center;
	height: 500px;
	width: 500px;
	position: relative;
	animation-name:car_image;
	animation-duration: 5s;
	animation-timing-function:linear;
	animation-iteration-count:infinite;
	top:-50%
} 
.download_image{
	
	/* height: 64px;
	width: 83px;
	position: relative; */
	animation-name:download_image;
	animation-duration: 10s;
	animation-timing-function:linear;
	animation-iteration-count:infinite;
	
}
@keyframes truck_image
{
	0%
	{
	left:-100%;
	
	}
	50%{
	left:0%;
	}
	100%{
	left: 100%;
	}
}

@keyframes car_image
{
	0%
	{
	right:-100%;
	
	}
	50%{
	right:0%;
	}
	100%{
	right: 100%;
	}
}
@keyframes download_image
{
	0%
	{
	left:-100%;
	
	}
	50%{
	left:0%;
	}
	100%{
	left: 100%;
	}
}
 
 </style>
</head>
<body>
 <%@include file="header.jsp" %> 
 
 
   
<div class="road">

<!-- Truck show  -->

 <div class="truck_image"></div> 
 <div class="car_image"></div> 

</div>

<div class="bottom">
<div class="container alert-warning">
<div class="row">
<div class="jumbotron jumbotron-fluid alert-warning">
  <div class="container">
    <h1 class="display-4">Welcome To RTO Management</h1>
    <p class="lead"><h4 align="center">"Drive Slow and enjoy the Scenery-drive fast and join the Scenery"</h4></p>
    <div class="download_image">
    <marquee behavior="scroll" direction="right" scrollamount="20"><img alt="" src="images/download.png"></div></marquee>
  </div>
</div>
</div></div></div>

<%@include file="footer.jsp" %>
</body>
</html>