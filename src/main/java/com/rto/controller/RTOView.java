package com.rto.controller;

public interface RTOView {
	public String APP_CONTEXT="/RTO-Management";
	public String PAGE_FOLDER="/jsp";
	
	
	
	public String WELCOME_VIEW=PAGE_FOLDER+"/welcome.jsp";
	public String Our_AboutUs_PageView=PAGE_FOLDER+"/OurAboutUsPage.jsp";
	public String Our_ContactUs_PageView=PAGE_FOLDER+"/OurContactView.jsp";
	public String User_Registration_View=PAGE_FOLDER+"/UserRegistrationView.jsp";
	public String User_Login_View=PAGE_FOLDER+"/LoginView.jsp";
	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	public String USER_LIST_VIEW=PAGE_FOLDER+"/UserListView.jsp";
	public String USER_VIEW=PAGE_FOLDER+"/UserView.jsp";
	public String Vehicle_Registration_View=PAGE_FOLDER+"/VehicleRegistrationView.jsp";
	public String VEHICLE_LIST_VIEW=PAGE_FOLDER+"/VehicleListView.jsp";
	public String VEHICLE_VIEW=PAGE_FOLDER+"/VehicleView.jsp";
	public String Licence_Registration_View=PAGE_FOLDER+"/LicenceRegistrationView.jsp";
	public String LICENCE_LIST_VIEW=PAGE_FOLDER+"/LicenceListView.jsp";
	public String LICENCE_VIEW=PAGE_FOLDER+"/LicenceView.jsp";
	public String MY_PROFILE_VIEW=PAGE_FOLDER+"/MyProfileView.jsp";
	public String CHANGE_PASS_VIEW=PAGE_FOLDER+"/ChangePasswordView.jsp";
	public String STATUS_VIEW=PAGE_FOLDER+"/StatusView.jsp";
	
	
	
	
	public String WELCOME_CTL=APP_CONTEXT+"/welcome";
	public String User_Registration_Ctl=APP_CONTEXT+"/register";
	public String ERROR_CTL = "/ctl/ErrorCtl";
	public String User_Login_Ctl=APP_CONTEXT+"/login";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl"; 
	public String Vehile_Regustration_Ctl=APP_CONTEXT+"/ctl/VehicleRegister";
	public String VEHICLE_LIST_CTL = APP_CONTEXT + "/ctl/VehicleListCtl";
	public String VEHICLE_CTL = APP_CONTEXT + "/ctl/VehicleCtl";
	public String Licence_Registration_Ctl=APP_CONTEXT+"/ctl/Licence";
	public String LICENCE_LIST_CTL=APP_CONTEXT+"/ctl/LicenceListCtl";
	public String LICENCE_CTL=APP_CONTEXT+"/ctl/LicenceCtl";
	public String MY_PROFILE_CTL=APP_CONTEXT+"/ctl/MyProfileCtl";
	public String CHANGE_PASS_CTL=APP_CONTEXT+"/ctl/ChangePasswordCtl";
	public String STATUS_CTL=APP_CONTEXT+"/ctl/StatusCtl";
	
}
