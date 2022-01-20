<%@page import="com.rto.controller.VehicleCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.rto.util.ServletUtility"%>
<%@page import="com.rto.util.DataUtility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Favicons -->
    <link href="../img/favicon.png" rel="icon">
  <link href="../img/apple-touch-icon.png" rel="apple-touch-icon"> 

 <!--  Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

 <!--  Libraries CSS Files -->
  <link href="../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet">
  <link href="../lib/hover/hoverex-all.css" rel="stylesheet"> 

 <!--  Main Stylesheet File -->
  <link href="../css/style.css" rel="stylesheet" type="text/css"/> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<div id="blue">
    <div class="container">
      <div class="row">
        <h3>Edit Vehicle Info.</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
  </div>
  
  
  <!--  EDit vehicle form-->
	<div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
      <marquee behavior="scroll" direction="right" scrollamount="10"><img alt="" src="../images/boy.jpg"></div></marquee>
        <h2>Welcome to RTO!!!<br> Update Users Vehicle Info</h2>
        <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
        <br>
        <div class="hline"></div>
        <form action="<%=RTOView.VEHICLE_CTL%>" method="post">
        <jsp:useBean id="bean" class="com.rto.bean.VehicleBean" scope="request"></jsp:useBean>
        <input type="hidden" name="id" value="<%=bean.getId()%>">
        <%if(bean.getTypeOfVehicle().equals("Two Wheeler")){ %>
        <div class="form-check form-check-inline">
        
  <input class="form-check-input"  type="radio" name="typeofvehicle" id="inlineRadio1" checked="checked"  value="<%=DataUtility.getStringData(bean.getTypeOfVehicle())%>">
  <label class="form-check-label" for="inlineRadio1">Two wheeler</label></div>
	<%} %><%else{ %>
 <div class="form-check form-check-inline"> 
  <input class="form-check-input" type="radio" name="typeofvehicle" id="inlineRadio2" checked="checked"  value="<%=DataUtility.getStringData(bean.getTypeOfVehicle())%>">
  <label class="form-check-label" for="inlineRadio2">Four Wheeler</label>
  <font  color="red"><%=ServletUtility.getErrorMessage("typeofvehicle", request)%></font>
  
</div><%} %>
  <div class="form-group">
    <label for="formGroupExampleInput">Owner Name</label>
    <input type="text" class="form-control" name="name" id="formGroupExampleInput" placeholder="Enter Owner Name" value="<%=DataUtility.getStringData(bean.getOwnerName())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Registration No.</label>
    <input type="text" class="form-control" name="regno" id="formGroupExampleInput" placeholder="Enter Registartion No." value="<%=DataUtility.getStringData(bean.getRegistrationNo()) %>" >
  	<font  color="red"><%=ServletUtility.getErrorMessage("regno", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Registration Date</label>
    <input id="datepicker" type="text" class="form-control"  name="dor"  placeholder="Enter Date of Registration" value="<%=DataUtility.getDateString(bean.getRegistrationDate())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("dor", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Chassis No.</label>
    <input type="text" class="form-control" name="cno" id="formGroupExampleInput" placeholder="Enter Chassis No" value="<%=DataUtility.getStringData(bean.getChassisNo())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("cno", request)%></font>
  </div>
  
	<div class="form-group">
    <label for="formGroupExampleInput">Model Name</label>
    <input type="text" class="form-control" name="modelname" id="formGroupExampleInput" placeholder="Enter Model Name" value="<%=DataUtility.getStringData(bean.getModelName())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("modelname", request)%></font>
  </div>
 
  <button type="submit" class="btn btn-primary mb-2" value="<%=VehicleCtl.OP_SAVE%>" name="operation">Edit Vehicle Info</button>
  
</form>
      </div>
    </div>
  </div>  
<%@include file="footer.jsp" %>
</body>
</html>