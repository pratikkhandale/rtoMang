package com.rto.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.rto.bean.LicenceBean;
import com.rto.bean.UserBean;
import com.rto.bean.VehicleBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DuplicateRecordException;
import com.rto.util.DataUtility;
import com.rto.util.HibDataSource;
import com.rto.util.JDBCDataSource;

public class LicenceModel {

	public int registerApplicant(LicenceBean bean) throws ApplicationException, DuplicateRecordException, SQLException {
		// TODO Auto-generated method stub
		int pk=add(bean);
		return pk;
		
	}
	
	public LicenceBean findByPK(int pk) throws ApplicationException {

		//log.debug("User Model findByPK method start");
		Session session = null;
		LicenceBean bean = null;

		try {
			session = HibDataSource.getSession();
			bean = (LicenceBean) session.get(LicenceBean.class, pk);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting user by PK");
		} finally {
			session.close();
		}

		
		System.out.println("bean in pk"+bean);
		return bean;
	}
	
	public void update(LicenceBean bean) throws Exception {
		// TODO Auto-generated method stub
		int id=bean.getId();
		String status=bean.getStatus();
		/*String status=bean.getStatus();
		Connection con=JDBCDataSource.getConnection();
		String sql="UPDATE R_LICENCE SET STATUS=? WHERE USERID='"+id+"'";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, bean.getStatus());
		ps.executeUpdate();*/
		Session session=null;
		Transaction tx=null;
		LicenceBean bean2=findByApplicantName(bean.getApplicantName());
		if(bean2!=null && bean2.getId()!=bean.getId()){
			throw new DuplicateRecordException("duplaicate");
		}
		
		LicenceBean lBean=findByPK(bean.getId());
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		String hqlUpdate = "update LicenceBean c set c.status = :status where c.id = :id";
		// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
		int updatedEntities = session.createQuery( hqlUpdate )
		        .setString( "status", status )
		        .setInteger( "id", id )
		        .executeUpdate();
		tx.commit();
		session.close();
	}

	public int add(LicenceBean bean) throws ApplicationException, DuplicateRecordException, SQLException {
		// TODO Auto-generated method stub
		int pk=0;
		LicenceBean beanExist=findByApplicantName(bean.getApplicantName());
		if (beanExist != null ) {
			System.out.println("beanExist.getLogin :"+beanExist.getApplicantName());
			throw new DuplicateRecordException("Applicant is already exist");
		}
		Session session=null;
		Transaction tx=null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(bean);
			pk = bean.getId();

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User Add " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

		}
		//session.saveOrUpdate(inputStream);
		
		/*String sql="INSERT INTO R_LICENCE (APPLICANTNAME,GENDER,DOB,PLACEOFBIRTH,ADDRESS,TYPEOFLICENCE,PHOTO,CREATED_BY,CRAETED_DATE_TIME,MODIFIED_BY,MODIFIED_DATE_TIME) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, bean.getApplicantName());
		ps.setString(2, bean.getGender());
		ps.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		ps.setString(4, bean.getPlaceOfBirth());
		ps.setString(5, bean.getAddress());
		ps.setString(6, bean.getTypeOfLicence());
		ps.setBlob(7, inputStream);
		ps.setString(8, bean.getCreatedBy());
		ps.setString(9, bean.getModifiedBy());
		ps.setTimestamp(10, bean.getCreatedDateTime());
		ps.setTimestamp(11, bean.getCreatedDateTime());
		ps.setInt(12, bean.getId());
		pk=ps.executeUpdate();*/
		return pk;
		
	}

	public LicenceBean findByApplicantName(String applicantName) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		LicenceBean bean = null;
		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(LicenceBean.class);

			criteria.add(Restrictions.like("applicantName", applicantName)); //pass propery_name which is in bean name.

			List list = criteria.list();

			if (list.size() > 0) {
				bean = (LicenceBean) list.get(0);
			} else {
				bean = null;
			}
		} catch (HibernateException e) {

			throw new ApplicationException("exception in getting user by login");
		} finally {
			System.out.println("bean is "+bean);
			session.close();
		}
		
		return bean;
	}
	
	public List list() throws ApplicationException {

		return list(0, 0);
	}
	public List search(LicenceBean bean) throws ApplicationException {
		return search(bean,0,0);
	}
	public List list(int pageNo, int pageSize) throws ApplicationException {

		//log.debug("Vehicle Model list  method start");
		Session session = null;

		List list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(LicenceBean.class);

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

		
		return list;
	}
	public List search( LicenceBean bean,int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session=null;
		List list=null;
		try{
		session=HibDataSource.getSession();
		Criteria criteria=session.createCriteria(LicenceBean.class);
		if(bean.getId()>0){
			System.out.println("i dis "+bean.getId());
			criteria.add(Restrictions.eq("id",bean.getId()));
			
		}
		if (bean.getApplicantName() != null && bean.getApplicantName().length() > 0) {
			criteria.add(Restrictions.like("applicantName", bean.getApplicantName() + "%"));
		}
		if (bean.getTypeOfLicence() != null && bean.getTypeOfLicence().length() > 0) {
			criteria.add(Restrictions.like("typeOfLicence", bean.getTypeOfLicence() + "%"));
			

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

	public void delete(LicenceBean bean) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		session.delete(bean);
		tx.commit();
	}

}
