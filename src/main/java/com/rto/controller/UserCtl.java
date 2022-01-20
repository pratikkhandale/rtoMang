package com.rto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DatabaseException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.ServletUtility;




/**
 * Servlet implementation class UserCtl
 */
@WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected com.rto.bean.BaseBean populateBean(HttpServletRequest request) {
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		UserModel model=new UserModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is "+id);
		if(id>0 ||op!=null){
			UserBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
               // log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        

        ServletUtility.forward(getView(), request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=DataUtility.getStringData(request.getParameter("operation"));
		UserModel model=new UserModel();
		System.out.println("op in post "+op);
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id in update"+id);
		if(OP_SAVE.equalsIgnoreCase(op)){
			System.out.println("inside save");
			UserBean bean=(UserBean) populateBean(request);
			try {
			if(id>0){
				
					System.out.println("before calling update in model");
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Successfully Edited", request);
					ServletUtility.setBean(bean, request);
				}else{
					int pk=model.add(bean);
					ServletUtility.setSuccessMessage("Successfully Registered", request);
					ServletUtility.setBean(bean, request);
				}
			
			}catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.forward(RTOView.ERROR_CTL, request, response);
					return;
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("User Is already exists",
							request);
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.forward(RTOView.ERROR_CTL, request, response);
					return;
				}
			}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			UserBean bean=(UserBean) populateBean(request);
			model.delete(bean);
			//ServletUtility.redirect(RTOView.USER_LIST_CTL, request, response);
			
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.USER_VIEW;
	}

}
