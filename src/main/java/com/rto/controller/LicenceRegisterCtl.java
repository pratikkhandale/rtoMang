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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.lucene.util.IOUtils;

import com.rto.bean.BaseBean;
import com.rto.bean.LicenceBean;
import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.LicenceModel;
import com.rto.model.UserModel;
import com.rto.util.DataUtility;
import com.rto.util.DataValidator;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;

/**
 * Servlet implementation class LicenceRegisterCtl
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name="LicenceRegisterCtl",urlPatterns={"/ctl/Licence"})
public class LicenceRegisterCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LicenceRegisterCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		UserBean userBean=(UserBean) session.getAttribute("user");
		
		int id=userBean.getId();
		System.out.println("ID is "+id);
		
		String op=DataUtility.getString(request.getParameter("operation"));
		//get Model
		
		UserModel model=new UserModel();
		
		
		if(id>0||op !=null){
			
			UserBean bean;
			try{
				bean=model.findByPK(id);
				String name=bean.getName();
				request.setAttribute("Aname",name);
				//ServletUtility.setBean(bean, request);
				
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
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		// TODO Auto-generated method stub
		LicenceBean bean=new LicenceBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setApplicantName(DataUtility.getString(request.getParameter("applicantname")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setPlaceOfBirth(DataUtility.getString(request.getParameter("pob")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setTypeOfLicence(DataUtility.getString(request.getParameter("licence")));
		bean.setStatus("0");
		InputStream inputStream=null; 
		Blob blob=null;
		try {
			 //input stream of the uploaded file...
			Part filepart=request.getPart("photo");
			inputStream =filepart.getInputStream();
			byte[] b=new byte[inputStream.available()];
			inputStream.read(b);
			
		         
		         try {
					blob = new SerialBlob(b );
				} catch (SerialException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setPhoto(blob);
		populateDTO(bean, request);
		
		
		
		return bean;
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean pass=true;
		/*if(DataValidator.isNull(request.getParameter("applicantname"))){
			request.setAttribute("applicant", PropertyReader.getValue("error.require","Name of Applicant"));
			pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("applicantname"))){
			request.setAttribute("name", PropertyReader.getValue("error.name","name"));
			pass=false;
		}*/
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob","Min Age Must be 17 years");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("pob"))){
			request.setAttribute("pob", PropertyReader.getValue("error.require","Place of Birth"));
			pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("pob"))){
			request.setAttribute("name", PropertyReader.getValue("error.name","name"));
			pass=false;
		}
		if(DataValidator.isNull(request.getParameter("address"))){
			request.setAttribute("name", PropertyReader.getValue("error.require","Address"));
			pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("address"))){
			request.setAttribute("name", PropertyReader.getValue("error.name","name"));
			pass=false;
		}
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("licence"))) {
			request.setAttribute("licence",
					PropertyReader.getValue("error.require", "Licence"));
			pass = false;
		}
		
		return pass;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("helloe");
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("in op of dopost"+op);
		LicenceModel model=new LicenceModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SIGN_UP.equalsIgnoreCase(op)){
			LicenceBean bean=(LicenceBean)populateBean(request);
			/*InputStream inputStream=null;  //input stream of the uploaded file...
			Part filepart=request.getPart("photo");
			inputStream =filepart.getInputStream();*/
			
			try {
				int pk=model.registerApplicant(bean);
				request.getSession().setAttribute("LicenceBean", bean);
				ServletUtility.setSuccessMessage("Thank You!! Admin will verify the Information", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Application Already Exist",
						request);
				ServletUtility.forward(getView(), request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.Licence_Registration_View;
	}

}
