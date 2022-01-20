package com.rto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rto.bean.BaseBean;
import com.rto.bean.UserBean;
import com.rto.bean.VehicleBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DatabaseException;
import com.rto.exception.DuplicateRecordException;
import com.rto.model.UserModel;
import com.rto.model.VehicleModel;
import com.rto.util.DataUtility;
import com.rto.util.ServletUtility;

/**
 * Servlet implementation class VehicleCtl
 */
@WebServlet(name="VehicleCtl",urlPatterns={"/ctl/VehicleCtl"})
public class VehicleCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		VehicleModel model=new VehicleModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is "+id);
		if(id>0 ||op!=null){
			VehicleBean bean;
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
		VehicleModel model=new VehicleModel();
		System.out.println("op in post "+op);
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id in update"+id);
		if(OP_SAVE.equalsIgnoreCase(op)){
			System.out.println("inside save");
			VehicleBean bean=(VehicleBean) populateBean(request);
			try {
			if(id>0){
				
					System.out.println("before calling update in model");
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Vehicle Edited Successfully", request);
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
				}
			}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			VehicleBean bean=(VehicleBean) populateBean(request);
			model.delete(bean);
			//ServletUtility.redirect(RTOView.USER_LIST_CTL, request, response);
			
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.VEHICLE_VIEW;
	}

}
