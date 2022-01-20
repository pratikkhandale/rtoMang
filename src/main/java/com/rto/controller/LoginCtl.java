package com.rto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rto.bean.BaseBean;
import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.ServletUtility;


/**
 * Servlet implementation class LoginCtl
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/login" })
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	private static Logger log = Logger.getLogger(LoginCtl.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in login doget");
		HttpSession session=request.getSession(true);
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if (id > 0) {
			UserBean userBean;
			try {
				userBean = model.findByPK(id);
				ServletUtility.setBean(userBean, request);
		
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility.setSuccessMessage("You have been logged out successfully", request);
			
			ServletUtility.forward(RTOView.User_Login_View, request, response);
			return;
		}
		if (session.getAttribute("user") != null) {
			ServletUtility.redirect(RTOView.WELCOME_CTL, request, response);
			return;
		}
		
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. create a session
		HttpSession session = request.getSession(true);
		// 2. Take operation
		String op = DataUtility.getString(request.getParameter("operation"));
		// 3. Create Model object
		UserModel model = new UserModel();
		// 4. get id
		int id = DataUtility.getInt(request.getParameter("id"));
		// 5. check value of operation
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			bean = model.authenticate(bean.getUserName(), bean.getPassword());
			if (bean != null) {
				System.out.println("Hiiiiii");
				session.setAttribute("user", bean);
				session.setMaxInactiveInterval(10 * 6000);
				int roleid = bean.getRoleid();
				System.out.println("roleid" + roleid);
				if (roleid != 0) {
					session.setAttribute("role", bean.getName());
				}
				// save state
				String uri = request.getParameter("uri");
				if (uri == null || "null".equalsIgnoreCase(uri)) {
					ServletUtility.redirect(RTOView.WELCOME_CTL, request, response);
					return;
				} else {
					ServletUtility.redirect(uri, request, response);
				}
				return;
			} else {
				bean = (UserBean) populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doPost Ended");
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		// TODO Auto-generated method stub
		UserBean bean = new UserBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setUserName(DataUtility.getString(request.getParameter("uname")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		return bean;
	}

	protected String getView() {
		return RTOView.User_Login_View;
	}
}
