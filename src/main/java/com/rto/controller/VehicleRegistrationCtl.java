package com.rto.controller;

import java.io.IOException;import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rto.bean.BaseBean;
import com.rto.bean.VehicleBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.VehicleModel;
import com.rto.util.DataUtility;
import com.rto.util.DataValidator;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;

/**
 * Servlet implementation class VehicleRegistrationCtl
 */
@WebServlet(name="VehicleRegistrationCtl",urlPatterns={"/ctl/VehicleRegister"})
public class VehicleRegistrationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleRegistrationCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletUtility.forward(getView(), request, response);
	}
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean pass=true;
		if(DataValidator.isNull(request.getParameter("typeofvehicle"))){
			request.setAttribute("typeofvehicle", PropertyReader.getValue("error.require","Type of Vehicle"));
			pass=false;
		}
		if(DataValidator.isNull(request.getParameter("name"))){
			request.setAttribute("name", PropertyReader.getValue("error.require","Owner name"));
			pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("name"))){
			request.setAttribute("name", PropertyReader.getValue("error.name","Owner name"));
			pass=false;
		}
		
		//Validation for Registration Number.
		if(DataValidator.isNull(request.getParameter("regno"))){
			request.setAttribute("regno", PropertyReader.getValue("error.require","Registration Number"));
			pass=false;
		}
		else if(!DataValidator.isRegNo(request.getParameter("regno"))){
			request.setAttribute("regno",PropertyReader.getValue("error.regno","Registration Number"));
			pass=false;
		}
		
		//Validation for dates
		if(DataValidator.isNull(request.getParameter("dor"))){
			request.setAttribute("dor", PropertyReader.getValue("error.require","Date of Registartion"));
			pass=false;
		}
		//Valiadtion for chasssis
		if(DataValidator.isNull(request.getParameter("cno"))){
			request.setAttribute("cno", PropertyReader.getValue("error.require","Chassis No."));
			pass=false;
		}
		else if(!DataValidator.isInteger(request.getParameter("cno"))){
			request.setAttribute("cno",PropertyReader.getValue("error.cno","cno"));
			pass=false;
		}
		//validation Model Name
		
		if(DataValidator.isNull(request.getParameter("modelname"))){
			request.setAttribute("modelname", PropertyReader.getValue("error.require","model name"));
			pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("modelname"))){
			request.setAttribute("modelname",PropertyReader.getValue("error.name","model name"));
			pass=false;
		}
		
		
		
		return pass;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		// TODO Auto-generated method stub
		VehicleBean bean=new VehicleBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setTypeOfVehicle(DataUtility.getString(request.getParameter("typeofvehicle")));
		bean.setOwnerName(DataUtility.getString(request.getParameter("name")));
		bean.setRegistrationNo(DataUtility.getString(request.getParameter("regno")));
		bean.setRegistrationDate(DataUtility.getDate(request.getParameter("dor")));
		bean.setChassisNo(DataUtility.getString(request.getParameter("cno")));
		bean.setModelName(DataUtility.getString(request.getParameter("modelname")));
		populateDTO(bean, request);
		
		return bean;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		VehicleModel model=new VehicleModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SIGN_UP.equalsIgnoreCase(op)){
			VehicleBean bean=(VehicleBean)populateBean(request);
			try {
				int pk=model.registerVehicle(bean);
				request.getSession().setAttribute("VehicleBean", bean);
				ServletUtility.setSuccessMessage("Vehicle Successfully Registered", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Vehicle already Exist",
						request);
				ServletUtility.forward(getView(), request, response);
			}
			
			
			
		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.Vehicle_Registration_View;
	}

}
