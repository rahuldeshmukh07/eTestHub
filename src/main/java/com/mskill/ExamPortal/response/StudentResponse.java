/**
 * 
 */
package com.mskill.ExamPortal.response;

import com.mskill.ExamPortal.model.Student;

/**
 * @author 1838177
 *
 */
public class StudentResponse {

	/**
	 * 
	 */
	private long student_id;
	private String salutation;
	private String first_name;
    private String last_name;
    private String student_email;
    private String college_name;
    private String branch_name;
    private long mobile;
    private int academic_year;
    
    public StudentResponse() {
		// TODO Auto-generated constructor stub
	}
    
    public StudentResponse(long student_id, String salutation, String first_name, String last_name,
    	     String student_email,String college_name, String branch_name, long mobile,int academic_year) 
    {
    			this.student_id = student_id;
    	    	this.salutation = salutation;
    	    	this.first_name=first_name;
    	    	this.last_name = last_name;
    	    	this.student_email = student_email;
    	    	this.college_name = college_name;
    	    	this.branch_name = branch_name;
    	    	this.mobile = mobile;
    	    	this.academic_year = academic_year;
    	    	
    }
    
    
    
	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public int getAcademic_year() {
		return academic_year;
	}

	public void setAcademic_year(int academic_year) {
		this.academic_year = academic_year;
	}

	
	public static StudentResponse generateView(Student obj) {
		StudentResponse studentView = new StudentResponse();
		 studentView.setAcademic_year(obj.getAcademic_year());
		 studentView.setFirst_name(obj.getFirst_name());
		 studentView.setLast_name(obj.getLast_name());
		 studentView.setMobile(obj.getMobile());
		 studentView.setSalutation(obj.getSalutation());
		 studentView.setStudent_email(obj.getStudent_email());
		 studentView.setStudent_id(obj.getStudent_id());
		 return studentView;
		
	}

}
