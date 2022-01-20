<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<%@include file="homepageheader.jsp" %>
<div id="blue">
    <div class="container">
      <div class="row">
        <h3>About Us</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
  </div>
<div class="container mtb">
    <div class="row">
      <div class="col-lg-6">
        <img class="img-responsive" src="../images/rto.jpg" alt="">
      </div>

      <div class="col-lg-6">
        <h4>RTO Management</h4>
        <p> This application is Regional Transport Office Management System. This application is developed to 
        automate the manual work of registration of vehicles issuing driving license. Users needs to register 
        on the site inorder to perform the Processes.  </p>
        <p>This application can be used by State and Central government RTo offices to make the process fast 
        and secured.</p>
        <p>This is built using Java MySQL , Hibernate technology and for view we have used JSP, Html, css, & js.</p>
        <p><br/><a href="OurContactView.jsp" class="btn btn-theme">Contact Us</a></p>
      </div>
    </div>
  </div>

<%@include file="footer.jsp" %>
</body>
</html>