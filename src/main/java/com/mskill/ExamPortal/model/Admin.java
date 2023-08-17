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
 * @author 1838177
 *
 */

@Entity
@Table(name = "admin")
public class Admin {

	private int admin_id;
	private String salutation;
	private String first_name;
	private String last_name;
	private int college_id;
	private String admin_email;
	private String password;
    private long mobile;

	public Admin(){

	}

	public Admin(int admin_id, String salutation, String first_name, String last_name, int college_id,
			String admin_email, String password, int mobile) {
		this.admin_id = admin_id;
		this.salutation = salutation;
		this.first_name = first_name;
		this.last_name = last_name;
		this.college_id = college_id;
		this.admin_email = admin_email;
		this.password = password;
		this.mobile = mobile;
	}

	@Id
	@GeneratedValue(generator = "admin_sequence")
	@SequenceGenerator(schema = "public", name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
	@Column(name = "admin_id", nullable = false)
	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	@Column(name = "salutation", nullable = false)
	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	@Column(name= "first_name" , nullable=false)
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Column(name= "last_name" , nullable=false)
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Column(name= "college_id" , nullable=false)
	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	@Column(name= "admin_email" , nullable=false)
	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}

	@Column(name= "password" , nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name= "mobile", nullable=false)
	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

}
