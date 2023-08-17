package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
//import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;

//@SecondaryTable(name = "mod_sub")
@Entity
@Table(name = "moderator")

public class Moderator{
	
	private int moderator_id;
	private String salutation;
	private String first_name;
	private String last_name;
	private String moderator_email;
	private String password;
	private long mobile;
	private int college_id;
	private int created_by;
	//private int subject_id;
	//private String subject_name;
	
	public Moderator() {}

	public Moderator(String salutation, int moderator_id, String first_name, String last_name, String moderator_email, String password,
			long mobile, int college_id, int created_by) {
		this.salutation = salutation;
		this.moderator_id = moderator_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.moderator_email = moderator_email;
		this.password = password;
		this.mobile = mobile;
		this.college_id = college_id;
		this.created_by = created_by;
		//this.subject_id = subject_id;
		//this.subject_name = subject_name;
	}
	
	public Moderator(String salutation,/* int moderator_id,*/ String first_name, String last_name, String moderator_email, String password,
			long mobile, int college_id, int created_by) {
		this.salutation = salutation;
//s		this.moderator_id = moderator_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.moderator_email = moderator_email;
		this.password = password;
		this.mobile = mobile;
		this.college_id = college_id;
		this.created_by = created_by;
	}

	

	@Id
	@GeneratedValue(generator = "moderator_sequence")
	@SequenceGenerator(schema = "public", name = "moderator_seqeunce", sequenceName = "moderator_sequence", allocationSize = 1)
    @Column(name = "moderator_id", nullable = false)
	public int getModerator_id() {
		return moderator_id;
	}

	public void setModerator_id(int moderator_id) {
		this.moderator_id = moderator_id;
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

	@Column(name= "moderator_email" , nullable=false)
	public String getModerator_email() {
		return moderator_email;
	}

	public void setModerator_email(String moderator_email) {
		this.moderator_email = moderator_email;
	}

	@Column(name= "password" , nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name= "mobile" , nullable=false)
	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Column(name= "college_id" , nullable=false)
	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	@Column(name= "created_by" , nullable=false)
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	
	/*//@OneToOne
	@Column(name="subject_id", table="mod_sub", nullable=false)
	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}*/

	/*public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}*/

	@Override
	public String toString() {
		return "Moderator [moderator_id=" + moderator_id + ", salutation=" + salutation + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", moderator_email=" + moderator_email + ", password=" + password
				+ ", mobile=" + mobile + ", college_id=" + college_id + ", created_by=" + created_by + "]";
	}

	
}
