package com.mskill.ExamPortal.response;

public class FacultyResponse {

	private int faculty_id;
	private String salutation;
	private String first_name;
	private String last_name;
	private String faculty_email;
	private long mobile;
	private int college_id;
	private int subject_id;
	private String subject_name;
	private String college_name;


	public FacultyResponse() {}
	
	public FacultyResponse(int faculty_id, String first_name, String last_name, String faculty_email, long mobile,
			String college_name) {
		this.faculty_id = faculty_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.faculty_email = faculty_email;
		this.mobile = mobile;
		this.college_name = college_name;
	}
	
	
	public FacultyResponse(int faculty_id, String salutation, String first_name, String last_name, String faculty_email,
			long mobile, int college_id, int subject_id, String subject_name) {
		super();
		this.faculty_id = faculty_id;
		this.salutation = salutation;
		this.first_name = first_name;
		this.last_name = last_name;
		this.faculty_email = faculty_email;
		this.mobile = mobile;
		this.college_id = college_id;
		this.subject_id = subject_id;
		this.subject_name = subject_name;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
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

	public String getFaculty_email() {
		return faculty_email;
	}

	public void setFaculty_email(String faculty_email) {
		this.faculty_email = faculty_email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	
	
	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	
	public FacultyResponse(String salutation, int faculty_id, String first_name, String last_name, String faculty_email,
			long mobile, String college_name) {
		
		this.salutation = salutation;
		this.faculty_id = faculty_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.faculty_email = faculty_email;
		this.mobile = mobile;
		this.college_name = college_name;
	}

	
}
