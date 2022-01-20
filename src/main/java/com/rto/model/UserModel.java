package com.rto.model;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rto.bean.UserBean;
import com.rto.exception.ApplicationException;
import com.rto.exception.DatabaseException;
import com.rto.exception.DuplicateRecordException;
import com.rto.exception.RecordNotFoundException;
import com.rto.util.HibDataSource;







public class UserModel {
	private static Logger log = Logger.getLogger(UserModel.class);

	

	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		UserBean bean2=findByLogin(bean.getUserName());
		if(bean2!=null && bean2.getId()!=bean.getId()){
			throw new DuplicateRecordException("duplaicate");
		}
		
		UserBean userBean=findByPK(bean.getId());
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		session.update(bean);
		tx.commit();
	}

	public UserBean findByPK(int pk) throws ApplicationException {

		log.debug("User Model findByPK method start");
		Session session = null;
		UserBean bean = null;

		try {
			session = HibDataSource.getSession();
			bean = (UserBean) session.get(UserBean.class, pk);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting user by PK");
		} finally {
			session.close();
		}

		log.debug("User Model findByPK method End");
		System.out.println("bean in pk"+bean);
		return bean;
	}
	public List list() throws ApplicationException {

		return list(0, 0);
	}
	public List search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}
	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("User Model list  method start");
		Session session = null;

		List list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(UserBean.class);

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
	public UserBean findByLogin(String login) throws ApplicationException {
		System.out.println("Login is "+login);
		log.debug("User Model findbylogin method start");
		Session session = null;
		UserBean bean = null;
		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(UserBean.class);

			criteria.add(Restrictions.like("userName", login)); //pass propery_name which is in bean name.

			List list = criteria.list();

			if (list.size() > 0) {
				bean = (UserBean) list.get(0);
			} else {
				bean = null;
			}
		} catch (HibernateException e) {

			throw new ApplicationException("exception in getting user by login");
		} finally {
			System.out.println("bean is "+bean);
			session.close();
		}
		
		log.debug("User Model findbylogin method end");
		return bean;
	}

	public int add(UserBean bean)throws ApplicationException, DuplicateRecordException, DatabaseException {
		// TODO Auto-generated method stub
		System.out.println("HIB add user model");

		int pk = 0;

		UserBean beanExist = findByLogin(bean.getUserName());

		if (beanExist != null ) {
			System.out.println("beanExist.getLogin :"+beanExist.getUserName());
			throw new DuplicateRecordException("LoginId is already exist");
		}
		System.out.println("bean exist");
		/*UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getId());
		bean.setUserName(uBean.getUserName());*/
		//bean.setAccountNo(findByMaxAccountNo()+1);

		Transaction tx = null;
		Session session = null;

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
		log.debug("User Model add method end");

		return pk;
	}
	public int registerUser(UserBean Bean) throws ApplicationException, DuplicateRecordException, DatabaseException {

		log.debug("Model registerUser Started");

		int pk = add(Bean);


		log.debug("Model registerUser End");

		return pk;

	}
	
	public void delete(UserBean bean) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		
		session=HibDataSource.getSession();
		tx=session.beginTransaction();
		session.delete(bean);
		tx.commit();
	}

	public UserBean authenticate(String userName, String password) {
		Session session=null;
		UserBean bean=null;
		System.out.println("username "+userName);
		System.out.println("password "+password);
		session=HibDataSource.getSession();
		Query q=session.createQuery("from UserBean where userName=? and password=?");
		q.setString(0, userName);
		q.setString(1, password);
		List list=q.list();
		System.out.println("List here "+list);
		if(list.size()>0){
			bean=(UserBean)list.get(0);
		}
		else
		{
			bean=null;
		}
		System.out.println("Bean in authentication"+bean);
		return bean;
	}

	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		System.out.println("bean id here "+bean.getId());
		Session session = null;

		List list = null;

		try {
			System.out.println("bean id here "+bean.getId());
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(UserBean.class);
			
			if (bean.getId() > 0) {
					System.out.println("bean id here "+bean.getId());
				criteria.add(Restrictions.eq("id", bean.getId()));
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				criteria.add(Restrictions.like("name", bean.getName() + "%"));
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {

				criteria.add(Restrictions.like("userName", bean.getUserName() + "%"));

			}
			if (bean.getRoleid() > 0) {
				criteria.add(Restrictions.eq("roleid", bean.getRoleid()));
			}
			/*if (bean.getAccountNo() > 0) {
				criteria.add(Restrictions.eq("accountNo", bean.getAccountNo()));
			}*/

			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			System.out.println("bean id here "+bean.getId());
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in search user");

		} finally {
			session.close();
		}

		log.debug("User Model search method end");
		return list;
		
	}

	public boolean changepassword(int id, String oldPass, String newPass) throws RecordNotFoundException, ApplicationException {
		// TODO Auto-generated method stub
		boolean flag=false;
		UserBean bean=null;
		bean=findByPK(id);
		if (bean != null && bean.getPassword().equals(oldPass)) {
			bean.setPassword(newPass);

			try {
				update(bean);
			} catch (DuplicateRecordException e) {

				throw new ApplicationException("exception in  change password ");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("exception in change password");
		}
		

		
		log.debug("Model changePassword End");
		return flag;
		
	}

}
