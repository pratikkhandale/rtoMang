package com.rto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rto.bean.BaseBean;
import com.rto.bean.UserBean;
import com.rto.bean.VehicleBean;
import com.rto.exception.ApplicationException;
import com.rto.model.UserModel;
import com.rto.model.VehicleModel;
import com.rto.util.DataUtility;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;

/**
 * Servlet implementation class VehicleListCtl
 */
@WebServlet(name="VehicleListCtl", urlPatterns={"/ctl/VehicleListCtl"})

public class VehicleListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleListCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected BaseBean populateBean(HttpServletRequest request) {
	
		VehicleBean bean = new VehicleBean();

		bean.setOwnerName(DataUtility.getString(request.getParameter("ownername")));

		bean.setTypeOfVehicle(DataUtility.getString(request.getParameter("typeofvehicle")));
		
		
		return bean;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//initailise list with null
		List list=null;
		int pageNo=1;
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
		VehicleBean bean=(VehicleBean)populateBean(request);
		String vp=bean.getTypeOfVehicle();
		
		String op=DataUtility.getString(request.getParameter("operation"));
		String ids[]=request.getParameterValues("ids");
		VehicleModel model=new VehicleModel();
		try{
		list=model.search(bean,pageNo,pageSize);
		if(list==null || list.size()==0){
			ServletUtility.setErrorMessage("No record found", request);
		}
		
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		}
		catch(ApplicationException e){
		
			ServletUtility.handleException(e, request, response);
			ServletUtility.forward(RTOView.ERROR_CTL, request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		VehicleBean bean = (VehicleBean) populateBean(request);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		
		String[] ids = request.getParameterValues("ids");
		
		VehicleModel model = new VehicleModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} /*else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(RTOView.USER_, request, response);
				return;
			} */else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					VehicleBean deletebean = new VehicleBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(RTOView.VEHICLE_LIST_CTL, request, response);
				return;

			}

			list = model.search(bean, pageNo, pageSize);
			
			
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			
			ServletUtility.handleException(e, request, response);
			return;
		}
		
	}


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return RTOView.VEHICLE_LIST_VIEW;
	}

}
