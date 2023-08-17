/**
 * 
 */
package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 *
 */
@Entity
@Table(name = "faculty")
public class Faculty {

	private int faculty_id;
	private String salutation;
	private String first_name;
	private String last_name;
	private String faculty_email;
	private String password;
	private long mobile;
	private int college_id;
	private int created_by;

	public Faculty() {
		
	}
	public Faculty(String salutation, /*int faculty_id,*/ String first_name, String last_name, String faculty_email, String password,
			long mobile, int college_id, int created_by) {
		this.salutation = salutation;
	//	this.faculty_id = faculty_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.faculty_email = faculty_email;
		this.password = password;
		this.mobile = mobile;
		this.college_id = college_id;
		this.created_by = created_by;
	}

	
	@Id
	@GeneratedValue(generator = "faculty_sequence")
	@SequenceGenerator(schema = "public", name = "faculty_seqeunce", sequenceName = "faculty_sequence", allocationSize = 1)
	@Column(name = "faculty_id", nullable = false)
	public int getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	
	@Column(name = "salutation", nullable = false)
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	
	
	@Column(name = "first_name", nullable = false)
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name = "faculty_email", nullable = false)
	public String getFaculty_email() {
		return faculty_email;
	}
	public void setFaculty_email(String faculty_email) {
		this.faculty_email = faculty_email;
	}
	
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "mobile", nullable = false)
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "college_id", nullable = false)
	public int getCollege_id() {
		return college_id;
	}
	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}
	
	@Column(name = "created_by", nullable = false)
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	@Override
	public String toString() {
		return "Faculty [salutation=" + salutation + ", faculty_id=" + faculty_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", faculty_email=" + faculty_email + ", password=" + password + ", mobile=" + mobile + ", college_id="
				+ college_id + ", created_by=" + created_by + "]";
	}
	
	

	
	
}
