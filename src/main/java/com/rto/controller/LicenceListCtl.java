package com.rto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rto.bean.BaseBean;
import com.rto.bean.LicenceBean;
import com.rto.bean.UserBean;
import com.rto.bean.VehicleBean;
import com.rto.exception.ApplicationException;
import com.rto.model.LicenceModel;
import com.rto.model.UserModel;
import com.rto.model.VehicleModel;
import com.rto.util.DataUtility;
import com.rto.util.PropertyReader;
import com.rto.util.ServletUtility;

/**
 * Servlet implementation class LicenceListCtl
 */
@WebServlet(name="LicenceListCtl",urlPatterns={"/ctl/LicenceListCtl"})
public class LicenceListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LicenceListCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	LicenceBean bean = new LicenceBean();

		bean.setApplicantName(DataUtility.getString(request.getParameter("applicantName")));

		bean.setTypeOfLicence(DataUtility.getString(request.getParameter("typeoflicense")));
		
		
		return bean;
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List list=null;
		int pageNo=1;
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
		System.err.println("page size "+pageSize);
		LicenceBean bean=(LicenceBean)populateBean(request);
		String op=DataUtility.getString(request.getParameter("operation"));
		String ids[]=request.getParameterValues("ids");
		LicenceModel model=new LicenceModel();
		try{
		list=model.search(bean,pageNo,pageSize);
		System.out.println("Lit is "+list);
		if(list==null || list.size()==0){
			ServletUtility.setErrorMessage("No record found", request);
		}
		
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		
		}
		catch(ApplicationException e){
		
			ServletUtility.handleException(e, request, response);
			return;
		}
		HttpSession session=null;
		//session.invalidate();
		//request.getSession(false);
		ServletUtility.forward(getView(), request, response);
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
		
		LicenceBean bean = (LicenceBean) populateBean(request);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		
		String[] ids = request.getParameterValues("ids");
		
		LicenceModel model = new LicenceModel();
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
					LicenceBean deletebean = new LicenceBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(RTOView.LICENCE_LIST_CTL, request, response);
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
		return RTOView.LICENCE_LIST_VIEW;
	}

}
