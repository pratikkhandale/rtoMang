package com.rto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.rto.bean.BaseBean;
import com.rto.bean.LicenceBean;
import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.LicenceModel;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.ServletUtility;

/**
 * Servlet implementation class LicenceCtl
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name="LicenceCtl",urlPatterns={"/ctl/LicenceCtl"})
public class LicenceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LicenceCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	
    	LicenceBean bean=new LicenceBean();
    	bean.setStatus(DataUtility.getString(request.getParameter("status")));
    	bean.setId(DataUtility.getInt(request.getParameter("id")));
    
		
		
		populateDTO(bean, request);
		
		
		
		return bean;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		LicenceModel model=new LicenceModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is "+id);
		if(id>0 ||op!=null){
			LicenceBean bean;
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
		String op=DataUtility.getString(request.getParameter("operation"));
		LicenceModel model=new LicenceModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			LicenceBean bean=(LicenceBean)populateBean(request);
			try {
			if(id>0){
				
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Changes Done", request);
					ServletUtility.setBean(bean, request);
					
			}
			else {
				int pk=model.add(bean);
				ServletUtility.setSuccessMessage("Successfully Registered", request);
				ServletUtility.setBean(bean, request);
			}
			
			
			} 
			
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.forward(RTOView.ERROR_CTL, request, response);
				}
			}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			LicenceBean bean=(LicenceBean) populateBean(request);
			model.delete(bean);
			//ServletUtility.redirect(RTOView.USER_LIST_CTL, request, response);
			
		}
		ServletUtility.forward(getView(), request, response);
		}
	

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.LICENCE_VIEW;
	}

}
