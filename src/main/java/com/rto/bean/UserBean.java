package com.rto.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="r_user")
public class UserBean extends BaseBean {
	
	/*@Column(name="userid")
	private int userid;*/
	
	@Column(name="name",length=45)
	private String name;

	@Column(name="username",length=45)
	private String userName;
	
	@Column(name="password",length=45)
	private String password;
	
	@Column(name="emailid",length=45)
	private String emailid;
	
	@Column(name="contactno",length=45)
	private String contactno;
	
	@Column(name="roleid",length=45)
	private int roleid;
	
	/*public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
	
}
