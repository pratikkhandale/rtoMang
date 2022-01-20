package com.rto.bean;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="r_licence")
public class LicenceBean extends BaseBean{

	@Column(name="applicantname",length=45)
	private String applicantName;
	
	@Column(name="gender",length=45)
	private String gender;
	
	@Column(name="dob",length=45)
	private Date dob;
	
	@Column(name="status")
	private String status;
	
	@Column(name="placeofbirth",length=45)
	private String placeOfBirth;
	
	@Column(name="address",length=145)
	private String address;
	
	@Column(name="typeoflicence",length=45)
	private String typeOfLicence;
	
	@Column(name="photo")
	private Blob photo;

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTypeOfLicence() {
		return typeOfLicence;
	}

	public void setTypeOfLicence(String typeOfLicence) {
		this.typeOfLicence = typeOfLicence;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob inputStream) {
		this.photo = inputStream;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
