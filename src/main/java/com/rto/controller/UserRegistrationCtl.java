package com.rto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rto.bean.BaseBean;
import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DatabaseException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.DataValidator;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;












/**
 * Servlet implementation class UserRegistrationCtl
 */
@WebServlet(name="UserRegistrationCtl",urlPatterns={"/register"})
public class UserRegistrationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected boolean validate(HttpServletRequest request)
    {
    	boolean pass=true;
    	String username=request.getParameter("uname");
    	if(DataValidator.isNull(request.getParameter("name"))){
    		request.setAttribute("name",PropertyReader.getValue("error.require", "name"));
    		pass=false;
    	}
    	else if(!DataValidator.isName(request.getParameter("name")))
    	{
    		request.setAttribute("name",PropertyReader.getValue("error.name", "name"));
    		pass=false;
    	}
    	if(DataValidator.isNull(username)){
    		request.setAttribute("username", PropertyReader.getValue("error.require","username"));
    		pass=false;
    	}
    	
    	if(DataValidator.isNull(request.getParameter("password"))){
    		request.setAttribute("password", PropertyReader.getValue("error.require", "password"));
    		pass=false;
    	}
    	else if(!DataValidator.isPassword(request.getParameter("password"))){
    		request.setAttribute("password", PropertyReader.getValue("error.password", "password"));
    		pass=false;
    	}
    	
    	if(DataValidator.isNull(request.getParameter("emailid"))){
    		request.setAttribute("emailid", PropertyReader.getValue("error.require", "emailid"));
    		pass=false;
    	}
    	else if(!DataValidator.isEmail(request.getParameter("emailid"))){
    		request.setAttribute("emailid", PropertyReader.getValue("error.emailid", "emailid"));
    		pass=false;
    	}
    	
    	if(DataValidator.isNull(request.getParameter("contact"))){
    		request.setAttribute("contact", PropertyReader.getValue("error.require", "contact"));
    		pass=false;
    	}
    	else if(!DataValidator.isPhoneNo(request.getParameter("contact"))){
    		request.setAttribute("contact", PropertyReader.getValue("error.invalid", "contact"));
    		pass=false;
    	}
    	
		return pass;
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		
		int id=DataUtility.getInt(request.getParameter("id"));
		
		if(id>0 || op!=null){
			UserBean bean=null;
			try {
				bean = model.findByPK(id);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
			ServletUtility.setBean(bean, request);
		}
		
		
		
		
		
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("Id in do psot"+id);
		if(OP_SIGN_UP.equalsIgnoreCase(op))
		{
			UserBean bean=(UserBean)populateBean(request);
			try {
				System.out.println("in try sign up");
				int pk = model.registerUser(bean);
				System.out.println("value of  pk is "+pk);
				//System.out.println("in register");
				bean.setId(pk);
			
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility.setSuccessMessage("User Successfully Registered", request);
				ServletUtility.forward(RTOView.User_Registration_View, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Username already exist!!",request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
}else if (OP_RESET.equalsIgnoreCase(op)) {
	ServletUtility.redirect(RTOView.User_Registration_Ctl, request, response);
	return;
}
}

	protected BaseBean populateBean(HttpServletRequest request) {
		// TODO Auto-generated method stub
		UserBean bean=new UserBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setUserName(DataUtility.getString(request.getParameter("uname")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setEmailid(DataUtility.getString(request.getParameter("emailid")));
		bean.setContactno(DataUtility.getString(request.getParameter("contact")));
		bean.setRoleid(2);
		
		populateDTO(bean, request);
		
		return bean;
	}

	protected String getView() {
		return RTOView.User_Registration_View;
	}

}
