<!DOCTYPE html>
<%@page import="com.rto.controller.LoginCtl"%>
<%@page import="com.rto.bean.UserBean"%>
<%@page import="com.rto.controller.RTOView"%>
<html lang="en">
<head>
 <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
<meta charset="utf-8">
<title>RTO - Management</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicons -->
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet"> -->
<link href="lib/hover/hoverex-all.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet" type="text/css" />



</head>

<body>
	<%
UserBean userbean=(UserBean)session.getAttribute("user");

boolean userWhoIsLogin=userbean!=null;
System.out.print("who is logged in "+userWhoIsLogin);
String welcomeMsg="Hey ";
if(userWhoIsLogin){
	String role=(String)session.getAttribute("roleid");
	welcomeMsg+=userbean.getName();
	if(userbean.getRoleid()==1){
		welcomeMsg+="";}
		else if(userbean.getRoleid()==2){
			welcomeMsg+="";	
		}
	}
else{
	welcomeMsg+="Guest";
}

%>
<%System.out.print("Hello in header.jsp"); %>
	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=RTOView.WELCOME_CTL%>">RTO -
					Management</a>
			</div>
			<div class="navbar-collapse collapse navbar-right">
				<ul class="nav navbar-nav">


					<li><a class="navbar-brand" href="<%=RTOView.WELCOME_CTL%>">HOME</a></li>

					
					<%if(userWhoIsLogin){ %>
					<%--  <li><a href="<%=RTOView.WELCOME_CTL%>">HOME</a></li> --%>
					

					<%if(userbean.getRoleid()==1) {%>
					<li><a class="navbar-brand" href="<%=RTOView.USER_LIST_CTL%>">User List</a></li>
					<li><a class="navbar-brand" href="<%=RTOView.VEHICLE_LIST_CTL%>">Vehicle List</a></li>
					<li><a class="navbar-brand" href="<%=RTOView.LICENCE_LIST_CTL%>">License Application List</a></li>
					
					<li><a href="<%=RTOView.MY_PROFILE_CTL%>">My Profile</a></li>
							<li><a href="<%=RTOView.CHANGE_PASS_CTL%>">Change Password</a></li>
							<li><a
								href="<%=RTOView.User_Login_Ctl%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Log
									out</a></li>
					

					<%}else if(userbean.getRoleid()==2){ %>
					<li><a class="navbar-brand" href="<%=RTOView.Vehile_Regustration_Ctl%>">Vehicle Registration</a></li>
					<li><a class="navbar-brand" href="<%=RTOView.Licence_Registration_Ctl%>">Apply For Driving License</a></li>
					<li><a href="<%=RTOView.STATUS_CTL%>">Check Status for License</a></li>
					<li><a href="<%=RTOView.MY_PROFILE_CTL%>">My Profile</a></li>
							<li><a href="<%=RTOView.CHANGE_PASS_CTL%>">Change Password</a></li>
							<li><a
								href="<%=RTOView.User_Login_Ctl%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Log
									out</a></li>
					
					<%}}else{ %>
					
					<li><a
						href="/RTO-Management/<%=RTOView.Our_AboutUs_PageView%>">ABOUT
							US</a></li>
					<li><a
						href="/RTO-Management/<%=RTOView.Our_ContactUs_PageView%>">CONTACT</a></li>
					<li><a href="<%=RTOView.User_Login_Ctl%>">LOGIN</a></li>
					 <%} %> 
					<%-- <%if(userWhoIsLogin){ %>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><%=welcomeMsg %><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="blog.html">My Profile</a></li>
							<li><a href="single-post.html">Change Password</a></li>
							<li><a
								href="<%=RTOView.User_Login_Ctl%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Log
									out</a></li>
						</ul></li>
					<%} %> --%> 
					<%if(userWhoIsLogin){ %>
					<li><a class=""><%=welcomeMsg %></a></li>
					<%-- <li class=""><a href="#" class=""
						data-toggle="dropdown"><%=welcomeMsg %><b class="caret"></b></a> --%>
						<%-- <ul class="dropdown-menu">
							<li><a href="blog.html">My Profile</a></li>
							<li><a href="single-post.html">Change Password</a></li>
							<li><a
								href="<%=RTOView.User_Login_Ctl%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Log
									out</a></li>
						</ul></li> --%>
					<%} %>
					
				</ul>


			</div>

			<!--/.nav-collapse -->
		</div>

	</div>