<%@page import="com.rto.util.ServletUtility"%>
<%@page import="com.rto.util.DataUtility"%>
<%@page import="com.rto.controller.LoginCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
 <%@include file="header.jsp" %>
<div id="blue">
    <div class="container">
      <div class="row">
        <h3>Login</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
    
  </div>
 
 <!-- Login form starts here -->
 <div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
        <h2>Welcome to RTO, Login here</h2>
        <br>
        <div class="hline"></div>
        <form action="<%=RTOView.User_Login_Ctl%>" method="post">
        <jsp:useBean id="bean" class="com.rto.bean.UserBean" scope="request"></jsp:useBean>
        <% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
            
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
             	<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
  <div class="form-group">
    <label for="formGroupExampleInput">UserName</label>
    <input type="text" class="form-control" name="uname" id="formGroupExampleInput" placeholder="Enter Username" value="<%=DataUtility.getStringData(bean.getUserName())%>" required>
 	<font  color="red"><%=ServletUtility.getErrorMessage("username", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Password</label>
    <input type="password" class="form-control" name="password" id="formGroupExampleInput2" placeholder="Enter Password" value="<%=DataUtility.getStringData(bean.getPassword())%>" required>
  	<font  color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
  </div>
  <button type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN%>" class="btn btn-primary mb-2">Login</button>
  <a href="<%=RTOView.User_Registration_Ctl %>" class="btn btn-primary mb-2" >Not yet Registered?</a>
</form>
      </div>
    </div>
  </div>
 
 
<div>
<%@include file="footer.jsp" %></div>
</body>
</html>