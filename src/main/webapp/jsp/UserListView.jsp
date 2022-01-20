<%@page import="com.rto.controller.UserCtl"%>
<%@page import="com.rto.model.UserModel"%>
<%@page import="com.rto.controller.UserListCtl"%>
<%@page import="com.rto.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Favicons -->
<link href="../img/favicon.png" rel="icon">
<link href="../img/apple-touch-icon.png" rel="apple-touch-icon">

<!--  Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!--  Libraries CSS Files -->
<link href="../lib/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet">
<link href="../lib/hover/hoverex-all.css" rel="stylesheet">

<!--  Main Stylesheet File -->
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp"%>
	<div id="blue">
		<div class="container">
			<div class="row">
				<h3>Registered Users.</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>

	<div class="container mtb">
		<div class="row">
			<form action="<%=RTOView.USER_LIST_CTL%>" method="post">
				<div class="col-lg-8 col-lg-offset-2 centered">
					<!-- <h2>Welcome to RTO, Register here</h2> -->
					<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font
						color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b> <br>
					<!-- <div class="hline"></div> -->
					<div class="form-group col-lg-4">
						<input type="text" class="form-control" name="username"
							placeholder="Search By UserName"
							value="<%=ServletUtility.getParameter("username", request)%>">
					</div>
					<div class="form-group col-lg-4">
						<input type="text" class="form-control" name="name"
							placeholder="Search By Name"
							value="<%=ServletUtility.getParameter("name", request)%>">
					</div>
					<div class="form-group col-lg-4">
						<input type="submit" name="operation"
							class="btn btn-success pull-right"
							value="<%=UserListCtl.OP_SEARCH%>"> 
							 <input type="submit"
							name="operation" class="btn btn-success.focus pull-left"
							value="<%=UserListCtl.OP_RESET%>">
					</div>

				</div>
		</div>
	</div>
<!--  Tables -->
	<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-11">
				<table class="table table-striped table-dark">
				<thead>
							<tr>
								<th >Select</th>
								<th >Sr.No</th>
								<th >Name</th>
								<th >Username</th>
								<th >Emailid</th>
								<th >Phone</th>
								<th >Edit</th>
								
							</tr>
						</thead>
						<tbody>
							<%
   int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = ((pageNo - 1) * pageSize) + 1; 
	//int size =ServletUtility.getSize(request);
// int index=1;
  UserBean bean=null;
  List list=ServletUtility.getList(request);
  Iterator<UserBean> itr=list.iterator();
  while(itr.hasNext()){
	  bean=itr.next();
   
  %>

							<tr>
								<%if(bean.getRoleid()==1){ %>
								<td></td>
								<%}else{ %>
								<td><input type="checkbox" class="case" name="ids"
									value="<%=bean.getId()%>">
									<%} %>
								<td><%=index++ %></td>
								<td><%=bean.getName() %></td>
								<td><%=bean.getUserName()%></td>
								<td><%=bean.getEmailid() %></td>
								<td><%=bean.getContactno() %></td>
								<%if(bean.getRoleid()==1){ %>
								<td></td>
								<%}else{ %>
								
								<td><a class="btn btn-primary "
									href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
								<%} %>
								
							</tr>
							<%}%>
						</tbody>
					</table>
					
					<hr> 
					<div class="col-sm-7"></div>
				<div class="col-sm-11">
					<table class="table ">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=UserListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					
					<td><input type="submit" name="operation" class="btn btn-danger pull-right" 
						value="<%=UserListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
					<%
						UserModel model = new UserModel();
					%>
					 <td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=UserListCtl.OP_NEXT%>" 
						<%=((list.size() < pageSize))  ? "disabled" : ""%>></td>
				</tr>
			</table></div>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
					</form>
				</div>

			</div>
		</div>
	</div>




	<%@include file="footer.jsp"%>
</body>
</html>