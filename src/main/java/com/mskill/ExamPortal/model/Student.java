/**
 * 
 */
package com.mskill.ExamPortal.model;

/**
 * @author 1838177
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student")
public class Student {

	/**
	 * 
	 */
	private long student_id;
	private String salutation;
	private String first_name;
    private String last_name;
    private int college_id;
    private int branch_id;
    private String student_email;
    private String password;
    private long mobile;
    private int academic_year;
//    private boolean consent;
    private int created_by;
    
    
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String salutation, String first_name, String last_name,
    int college_id,
     int branch_id,
     String student_email,
     String password,
     long mobile,
     int academic_year, int created_by) {
    	this.salutation = salutation;
    	this.first_name=first_name;
    	this.last_name = last_name;
    	this.college_id = college_id;
    	this.branch_id = branch_id;
    	this.student_email = student_email;
    	this.password = password;
    	this.mobile = mobile;
    	this.academic_year = academic_year;
    	this.created_by = created_by;
//    	this.consent = consent;
    }
	
	
	
	@Id
	@GeneratedValue(generator = "student_sequence")
	@SequenceGenerator(schema = "public", name = "student_seqeunce", sequenceName = "student_sequence", allocationSize = 1)
    @Column(name = "student_id", nullable = false)
    public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	@Column(name= "salutation" , nullable=false)
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

	@Column(name= "branch_id" , nullable=false)
	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	@Column(name= "student_email" , nullable=false)
	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
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

	@Column(name= "academic_year" , nullable=false)
	public int getAcademic_year() {
		return academic_year;
	}

	public void setAcademic_year(int academic_year) {
		this.academic_year = academic_year;
	}

	@Column(name= "created_by" , nullable=false)
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	@Override
    public String toString() {
        return "Student [id=" + student_id + ", firstName=" + first_name + ", lastName=" + last_name + ", emailId=" + student_email
       + "]";
    }

}
