<%@page import="java.sql.*"%>
<%-- <%@page import="com.mysql.xdevapi.Statement"%> --%>
<%@page import="com.rto.util.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.rto.controller.StatusCtl"%>
<%@page import="org.hibernate.*"%>
<%@page import="org.hibernate.cfg.*"%>
<%@page import="com.rto.util.HibDataSource"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rto.util.ServletUtility"%>
<%@page import="com.rto.bean.LicenceBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.hidden {

  display: none;

}
</style>
<title>Insert title here</title>
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

</head>
<body>
<%@include file="header.jsp" %>
<div id="blue">
		<div class="container">
			<div class="row">
				<h3>Check Status.</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<%
	UserBean u=(UserBean)session.getAttribute("user");
	int id=u.getId();
	String cb=u.getName();
	
	%>
	
	<%
	Configuration cf=new Configuration();
	cf.configure();
	Session s=null;
	SessionFactory sf = cf.buildSessionFactory();  
	s =sf.openSession(); 
	
	//tx=s.beginTransaction();
	String q="FROM LicenceBean where applicantName =:cb";
	
	Query query=s.createQuery(q);
	query.setParameter("cb", cb);
	Iterator itr=query.iterate();
	if(itr.hasNext()){
		LicenceBean lb=(LicenceBean)itr.next();
		String status=lb.getStatus();
		System.out.print("status is "+status);
	%>
	<!-- Button trigger modal -->
	
<div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
    	<button onClick="toggleTable()" class="btn-danger" >Check Here For Status</button>
<br><br><br><br>
<table id="myTable" style="width:50%;margin-left: 25%; " class="hidden" border="1"  >

  <tr align="center">

    <th align="center" class="bg-danger">Status</th>

  </tr>
  <tr>
	<%if(status.equalsIgnoreCase("Accept")){ %>
    <th align="center" class="bg-success">You're Application is accepted!!!</th>
	<%}else if(status.equalsIgnoreCase("Reject")){ %>
	<th align="center" class="bg-success">You're Application is Rejected!!!</th>
	<% }else if(status.equalsIgnoreCase(null)){%>
		
	<th align="center" class="bg-success">Please First Apply</th>
	<%}else{ %>
	<th align="center" class="bg-success">Wait for Admin, to verify</th>
	<%} %>
  </tr>

 

</table>

<%} %>










</div>
</div>
</div>      

<br><br><br><br><br><br><br><br><br><br><br><br>

<script type="text/javascript">
/* JS: */
function toggleTable() {

	  document.getElementById("myTable").classList.toggle("hidden");

	}

</script>
<%@include file="footer.jsp" %>
</body>
</html>