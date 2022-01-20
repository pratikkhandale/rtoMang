package com.rto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rto.bean.BaseBean;
import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.DataValidator;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;



/**
 * Servlet implementation class MyProfileCtl
 */
@WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
    public static final String OP_CHANGE_MY_PASSWORD="ChangePassword"; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
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
    @Override
    protected boolean validate(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	boolean pass=true;
    	String username=request.getParameter("uname");
    	String op=DataUtility.getString(request.getParameter("operation"));
    	if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)|| op==null){
    		return pass;
    	}
    	
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
		HttpSession session=request.getSession(true);
		
		UserBean userBean=(UserBean) session.getAttribute("user");
		
		int id=userBean.getId();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		//get Model
		
		UserModel model=new UserModel();
		
		
		if(id>0||op !=null){
			
			UserBean bean;
			try{
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
				
			}catch(ApplicationException e){
				
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		UserBean bean=(UserBean)session.getAttribute("user");
		int id=bean.getId();
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		if(OP_SAVE.equalsIgnoreCase(op)){
			UserBean bean2=(UserBean) populateBean(request);
			try {
				model.update(bean2);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Profile Updated!!!", request);
				
				
			} catch (ApplicationException | DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.MY_PROFILE_VIEW;
	}

}
