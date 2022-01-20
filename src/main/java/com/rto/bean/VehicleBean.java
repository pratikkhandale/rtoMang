package com.rto.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="r_vehicle")
public class VehicleBean extends BaseBean {
	
	@Column(name="typeofvehicle",length=45)
	private String typeOfVehicle;
	
	@Column(name="ownername",length=45)
	private String ownerName;
	
	@Column(name="registrationno", length=45)
	private String registrationNo;
	
	@Column(name="registrationdate")
	private Date registrationDate;
	
	@Column(name="chassisno", length=45)
	private String chassisNo; 
	
	@Column(name="modelname", length=45)
	private String modelName;

	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}

	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
}
