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
import com.rto.exception.RecordNotFoundException;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.DataValidator;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;


/**
 * Servlet implementation class ChangePasswordCtl
 */
@WebServlet(name="ChangePasswordCtl",urlPatterns="/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected boolean validate(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	boolean pass=true;
    	String op=DataUtility.getString(request.getParameter("operation"));
    	if (DataValidator.isNull(request.getParameter("old"))) {
			request.setAttribute("old", PropertyReader.getValue("error.require", "Old Password"));
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("old"))) {
			request.setAttribute("old", PropertyReader.getValue("error.password", "Old Password"));
			return false;
		}
		if (DataValidator.isNull(request.getParameter("new"))) {
			request.setAttribute("new", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("new"))) {
			request.setAttribute("new", PropertyReader.getValue("error.password", "New Password"));
			return false;
		}
		
    	return pass;
    }
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	UserBean bean=new UserBean();
    	bean.setPassword(DataUtility.getString(request.getParameter("old")));
    	bean.setPassword(DataUtility.getString(request.getParameter("new")));
    	
    	return bean;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		UserBean bean=(UserBean)session.getAttribute("user");
		String newPass=request.getParameter("new");
		String oldPass=request.getParameter("old");
		int id=bean.getId();
		if(OP_SAVE.equalsIgnoreCase(op)){
			try {
				boolean flag=model.changepassword(id,oldPass,newPass);
				if (flag == true) {
					
					bean = model.findByLogin(bean.getUserName());
					
					session.setAttribute("user", bean);
					
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Password changed Successfully", request);
				}
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.setErrorMessage("Please enter valid OLd Password", request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.CHANGE_PASS_VIEW;
	}

}
