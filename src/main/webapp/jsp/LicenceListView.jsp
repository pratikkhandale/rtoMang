<%@page import="com.rto.util.DataUtility"%>
<%@page import="com.rto.model.LicenceModel"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.rto.util.HibDataSource"%>
<%@page import="java.sql.Blob"%>
<%@page import=" org.hibernate.Query"%>
<%@page import="com.rto.bean.LicenceBean"%>
<%@page import="com.rto.controller.LicenceListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.rto.controller.VehicleListCtl"%>
<%@page import="com.rto.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
				<h3>License Application List.</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>

<!-- Serach filed -->
	<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-11">
				<form action="<%=RTOView.LICENCE_LIST_CTL%>" method="post">
				<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font
						color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b> <br>
					<!-- <div class="hline"></div> -->
					<div class="form-group col-lg-4">
						<input type="text" class="form-control" name="applicantName"
							placeholder="Search By Applicant name"
							value="<%=ServletUtility.getParameter("applicantName", request)%>">
					</div>
					<div class="form-group col-lg-4">
						<input type="text" class="form-control" name="typeoflicense"
							placeholder="Search By Type of License"
							value="<%=ServletUtility.getParameter("typeoflicense", request)%>">
					</div>
					<div class="form-group col-lg-4">
						<input type="submit" name="operation"
							class="btn btn-success"
							value="<%=LicenceListCtl.OP_SEARCH%>">
							 <input type="submit"
							name="operation" class="btn btn-success.focus"
							value="<%=LicenceListCtl.OP_RESET%>">
					</div>
				</div>
			</div>
		</div>
	</div>
		<jsp:useBean id="bean" class="com.rto.bean.LicenceBean" scope="session"></jsp:useBean>		
	
	<!-- Tables -->
	<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-11">
				<table class="table table-striped table-dark">
    <thead>
        <tr>
            <th>Select</th>
            <th>#</th>
            <th>ID Proof</th>
            <th>Applicant Name</th>
            <th>Gender</th>
            <th>Date of Birth</th>
            <th>Place of Birth</th>
            <th>Address</th>
            <th>Type of License</th>
            <th>Accept</th>
           
        </tr>
    </thead>
    
    <tbody>
    
    <%
    
    
    int pageNo = ServletUtility.getPageNo(request);
    System.out.print("Page no is "+pageNo);
  	int pageSize = ServletUtility.getPageSize(request);
  	int index = ((pageNo - 1) * pageSize) + 1;
   // LicenceBean bean=null;
  
    List list=ServletUtility.getList(request);
    Iterator<LicenceBean> itr=list.iterator();
    while(itr.hasNext()){
    bean=itr.next();
 
    %>
    
        <tr>
        <td><input type="checkbox" class="case" name="ids"
									value="<%=bean.getId()%>"></td>
            <td><%=index++ %></td>
            
         <td><img src=/RTO-Management/ImageServlet?id=<%=bean.getId()%> alt="image not found" width="100px" height="100px"></td>
            <td><%=bean.getApplicantName()%></td>
            <td><%=bean.getGender() %></td>
            <td><%=DataUtility.getDateString(bean.getDob())%></td>
            <td><%=bean.getPlaceOfBirth() %></td>
            <td><%=bean.getAddress() %></td>
            <td><%=bean.getTypeOfLicence() %></td>
            <%
            String s=bean.getStatus();
            System.out.println("S value is "+s);
            if(bean.getStatus().equalsIgnoreCase("Accept"))
            
            {System.out.print("Here ::::"+bean.getStatus());%>
             <td><a class="btn btn-primary"
									 onclick="return false">Accepted</a></td>
									<%}else if(bean.getStatus().equalsIgnoreCase("Reject"))
										{%>
			<td><a class="btn btn-warning "
									 onclick="return false" >Rejected</a></td>							
										<%} else{%>
										<td><a class="btn btn-warning "
									href="LicenceCtl?id=<%=bean.getId()%>" onclick="return true" >Accept</a></td>
										<%} %>
									
            <%-- <td><a class="btn btn-primary "
									href="LicenceCtl?id=<%=bean.getId()%>">Edit</a></td> --%>

        </tr>
       <%}%>            
    </tbody>
</table>
<div class="col-sm-7"></div>
				<div class="col-sm-11">
					<table class="table ">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=LicenceListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					
					<td><input type="submit" name="operation" class="btn btn-danger pull-right" 
						value="<%=LicenceListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
					<%
						LicenceModel model = new LicenceModel();
					%>
					 <td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=LicenceListCtl.OP_NEXT%>" 
						<%=((list.size() < pageSize))  ? "disabled" : ""%>></td>
				</tr>
			</table></div>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>			
				
</div></div></div></div>
<%@include file="footer.jsp" %>
</body>
</html>