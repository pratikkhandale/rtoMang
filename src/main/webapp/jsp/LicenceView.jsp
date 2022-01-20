<%@page import="com.rto.controller.LicenceCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.rto.util.ServletUtility"%>
    <%@page import="com.rto.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rto.controller.LicenceRegisterCtl"%>
<%@page import="com.rto.util.DataUtility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
</head>
<body>
 <%@include file="header.jsp"%>
 <div id="blue">
    <div class="container">
      <div class="row">
        <h3>Accept Applicant.</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
  </div>
<!-- Apply for licence form -->
<!-- Register form starts here -->
 <div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
        <h2>Welcome to RTO, Apply for Driving Licence Here</h2>
        <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
        <br>
        <div class="hline"></div>
        <form action="<%=RTOView.LICENCE_CTL%>" method="post" enctype="multipart/form-data">
        <jsp:useBean id="bean" class="com.rto.bean.LicenceBean" scope="request"></jsp:useBean>
        <input type="hidden" name="id" value="<%=bean.getId()%>">
        
  <div class="form-group">
    <label for="formGroupExampleInput"> Applicant Name</label>
    <input type="text" class="form-control" name="applicantname" id="formGroupExampleInput" placeholder="Enter Name" value="<%=DataUtility.getStringData(bean.getApplicantName())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("applicant", request)%></font>
  </div>
  <%
						
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");
							map.put("Others", "Others");
							%>
							
							<div class="form-group">
							<label for="formGroupExampleInput2">Gender</label>
								<%=HTMLUtility.getList("gender",String.valueOf(bean.getGender()), map)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
							</div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Date Of Birth</label>
    <input type="text" class="form-control" name="dob" id="datepicker" placeholder="Enter DOB" value="<%=DataUtility.getStringData(bean.getDob())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Place of Birth</label>
    <input type="text" class="form-control" name="pob" id="formGroupExampleInput" placeholder="Enter Place of Birth" value="<%=DataUtility.getStringData(bean.getPlaceOfBirth())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("pob", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Address.</label>
    <input type="text" class="form-control" name="address" id="formGroupExampleInput" placeholder="Enter Address." value="<%=DataUtility.getStringData(bean.getAddress())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
  </div>
  
  
 <%
						
							HashMap map1 = new HashMap();
							map1.put("Light Motor Vehicle", "Light Motor Vehicle");
							map1.put("Medium Vehicle", "Medium Vehicle");
							map1.put("Heavy Vehicle", "Heavy Vehicle");
							%>
							
							<div class="form-group">
							<label for="formGroupExampleInput2">Licence Type</label>
								<%=HTMLUtility.getList("licence",String.valueOf(bean.getTypeOfLicence()), map1)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("licence", request)%></font>
							</div>
  
  <div class="form-group">
    <label for="exampleFormControlFile1">Upload Photo</label>
    <input type="file" class="form-control-file " name="photo" id="exampleFormControlFile1">
    <img src=/RTO-Management/ImageServlet?id=<%=bean.getId()%> alt="Avatar" width="200px" height="128"/ >
  </div>
   <%
						
							HashMap map3 = new HashMap();
							map3.put("Accept", "Accept");
							map3.put("Reject", "Reject");
							
							%>
							
							<div class="form-group">
							<label for="formGroupExampleInput2">Accept/Reject</label>
								<%=HTMLUtility.getList("status",String.valueOf(bean.getStatus()), map3)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("status", request)%></font>
							</div>
 <button type="submit" class="btn btn-primary mb-2" value="<%=LicenceCtl.OP_SAVE%>" name="operation">Save Changes</button>
  
</form>
      </div>
    </div>
  </div> 
 <%@include file="footer.jsp" %>
</body>
</html>