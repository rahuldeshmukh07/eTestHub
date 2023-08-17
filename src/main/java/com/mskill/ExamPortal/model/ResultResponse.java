package com.mskill.ExamPortal.model;

public class ResultResponse {

	public int student_id;
	public String subject_name;
	public int marks;
	public int count;
	public ResultResponse() {
		super();
	}
	
	
	public ResultResponse(int student_id, String subject_name, int marks, int count) {
		super();
		this.student_id = student_id;
		this.subject_name = subject_name;
		this.marks = marks;
		this.count = count;
	}


	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	
	

}
