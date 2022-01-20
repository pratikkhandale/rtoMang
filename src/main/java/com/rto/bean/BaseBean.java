package com.rto.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public abstract class BaseBean implements DropdownListBean,Comparator<BaseBean>,Serializable{

	@Id
    @GenericGenerator(name = "hiIncrement", strategy = "increment")
    @GeneratedValue(generator = "hiIncrement")
    @Column(name = "userid", unique = true, nullable = false)
	protected int id;
	
	
	@Column(name="CREATED_BY",length=45)
	protected String createdBy;
	
	@Column(name="MODIFIED_BY",length=45)
	protected String modifiedBy;
	
	@Column(name="CREATED_DATE_TIME")
	protected Timestamp createdDateTime;
	
	@Column(name="MODIFIED_DATE_TIME")
	protected Timestamp modifiedDateTime;
	
	//Generate setter & getter
	
	
	
	@Override
	public int compare(BaseBean o1, BaseBean o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
