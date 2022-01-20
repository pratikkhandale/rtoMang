<%@page import="com.rto.controller.MyProfileCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.rto.controller.UserCtl"%>
     <%@page import="com.rto.util.ServletUtility"%>
<%@page import="com.rto.util.DataUtility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Favicons -->
<link href="../img/favicon.png" rel="icon">
<link href="../img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet"> -->
<link href="../lib/hover/hoverex-all.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<div id="blue">
		<div class="container">
			<div class="row">
				<h3>Update Profile.</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
        <h2>Welcome to RTO, Update Your Profile</h2>
        <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
        <br>
        <div class="hline"></div>
        <form action="<%=RTOView.MY_PROFILE_CTL%>" method="post">
        <jsp:useBean id="bean" class="com.rto.bean.UserBean" scope="request"></jsp:useBean>
        <input type="hidden" name="id" value="<%=bean.getId()%>">
  <div class="form-group">
    <label for="formGroupExampleInput">Name</label>
    <input type="text" class="form-control" name="name" id="formGroupExampleInput" placeholder="Enter Name" value="<%=DataUtility.getStringData(bean.getName())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">UserName</label>
    <input type="text" class="form-control" name="uname" id="formGroupExampleInput" placeholder="Enter UserName" value="<%=DataUtility.getStringData(bean.getUserName())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("username", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Password</label>
    <input type="password" class="form-control" name="password" id="formGroupExampleInput2" placeholder="Enter Password" value="<%=DataUtility.getStringData(bean.getPassword())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Email Id</label>
    <input type="text" class="form-control" name="emailid" id="formGroupExampleInput" placeholder="Enter Email id" value="<%=DataUtility.getStringData(bean.getEmailid())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("emailid", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Contact No.</label>
    <input type="text" class="form-control" name="contact" id="formGroupExampleInput" placeholder="Enter Phone No." value="<%=DataUtility.getStringData(bean.getContactno())%>">
  	<font  color="red"><%=ServletUtility.getErrorMessage("contact", request)%></font>
  </div>
  <button type="submit" class="btn btn-primary mb-2" value="<%=MyProfileCtl.OP_SAVE%>" name="operation">Update</button>
  
</form>
      </div>
    </div>
  </div>
	
	
<%@include file="footer.jsp" %>
</body>
</html>