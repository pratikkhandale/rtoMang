package com.rto.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rto.bean.UserBean;
import com.rto.bean.VehicleBean;
import com.rto.controller.RTOView;
import com.rto.exception.ApplicationException;
import com.rto.exception.DuplicateRecordException;
import com.rto.util.HibDataSource;
import com.rto.util.ServletUtility;



public class VehicleModel {
	private static Logger log = Logger.getLogger(VehicleModel.class);
	public int registerVehicle(VehicleBean bean) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		int pk=add(bean);
		
		return pk;
		
	}
	public int add(VehicleBean bean) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		int pk=0;
		VehicleBean beanExist=findByChassisNo(bean.getChassisNo());
		if(beanExist!=null){
			throw new DuplicateRecordException("Vehicle already exist");
			
		}
		Transaction tx=null;
		Session session=null;
		try{
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		session.saveOrUpdate(bean);
		pk=bean.getId();
		
		tx.commit();
		}
		catch(HibernateException e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Vehicle Addition " + e.getMessage());
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return pk;
	}
	public VehicleBean findByChassisNo(String chassisno) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session=null;
		VehicleBean vehicleBean=null;
		try{
		session=HibDataSource.getSession();
		Criteria criteria=session.createCriteria(VehicleBean.class);
		criteria.add(Restrictions.like("chassisNo",chassisno));
		List list=criteria.list();
		if(list.size()>0){
			vehicleBean=(VehicleBean)list.get(0);
		}
		else
		{
			vehicleBean=null;
		}
		}
		catch(HibernateException e){
			throw new ApplicationException("exception in getting user");
		}
		finally {
			session.close();}
			
		
		
		return vehicleBean;
	}
	public List list() throws ApplicationException {

		return list(0, 0);
	}
	public List search(VehicleBean bean) throws ApplicationException {
		return search(bean,0,0);
	}
	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Vehicle Model list  method start");
		Session session = null;

		List list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(VehicleBean.class);

			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;// (pageNo - 1) * pageSize+1

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("exception in getting list of User");
		} finally {
			session.close();
		}

		log.debug("User Model list  method end");
		return list;
	}
	public List search( VehicleBean bean,int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		String tp=bean.getTypeOfVehicle();
		System.out.println("Type of vehicle"+tp);
		Session session=null;
		List list=null;
		try{
		session=HibDataSource.getSession();
		Criteria criteria=session.createCriteria(VehicleBean.class);
		if(bean.getId()>0){
			System.out.println("i dis "+bean.getId());
			criteria.add(Restrictions.eq("id",bean.getId()));
			
		}
		if (bean.getOwnerName() != null && bean.getOwnerName().length() > 0) {
			criteria.add(Restrictions.like("ownerName", bean.getOwnerName() + "%"));
		}
		if (bean.getTypeOfVehicle() != null && bean.getTypeOfVehicle().length() > 0) {

			criteria.add(Restrictions.like("typeOfVehicle", bean.getTypeOfVehicle() + "%"));

		}
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		System.out.println("bean id here "+bean.getId());
		list = criteria.list();
		}
		catch(HibernateException e){
			throw new ApplicationException("Exception in search user");
			
		}
		finally {
			session.close();
		}
		return list;
	}
	public VehicleBean findByPK(int pk)throws ApplicationException {
		// TODO Auto-generated method stub
		
			Session session = null;
			VehicleBean bean = null;

			try {
				session = HibDataSource.getSession();
				bean = (VehicleBean) session.get(VehicleBean.class, pk);
			} catch (HibernateException e) {

				throw new ApplicationException("Exception in getting user by PK");
			} finally {
				session.close();
			}

			log.debug("User Model findByPK method End");
			System.out.println("bean in pk"+bean);
			return bean;
		}
	public void update(VehicleBean bean) throws DuplicateRecordException, ApplicationException {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		VehicleBean bean2=findByChassisNo(bean.getChassisNo());
		if(bean2!=null && bean2.getId()!=bean.getId()){
			throw new DuplicateRecordException("duplaicate");
		}
		
		VehicleBean vBean=findByPK(bean.getId());
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		session.update(bean);
		tx.commit();
	}
	public void delete(VehicleBean bean) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		session.delete(bean);
		tx.commit();
	}
	}


