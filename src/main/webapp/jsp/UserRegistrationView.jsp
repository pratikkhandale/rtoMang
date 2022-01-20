<%@page import="com.rto.util.ServletUtility"%>
<%@page import="com.rto.util.DataUtility"%>
<%@page import="com.rto.controller.UserRegistrationCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="blue">
    <div class="container">
      <div class="row">
        <h3>User Registration Page</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
    
  </div>

 <!-- Register form starts here -->
 <div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
        <h2>Welcome to RTO, Register here</h2>
        <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
        <br>
        <div class="hline"></div>
        <form action="<%=RTOView.User_Registration_Ctl%>" method="post">
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
  <button type="submit" class="btn btn-primary mb-2" value="<%=UserRegistrationCtl.OP_SIGN_UP%>" name="operation">Make Your Profile</button>
  
</form>
      </div>
    </div>
  </div>

<%@include file="footer.jsp" %>
</body>
</html>