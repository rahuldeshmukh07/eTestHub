package com.mskill.ExamPortal.response;

public class SelectSubjectResponse {
	
	private String subject_name;
	private int subject_id;

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	
	
	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public SelectSubjectResponse() {
		
	}

	public SelectSubjectResponse(String subject_name,int subject_id) {
		this.subject_name = subject_name;
		this.subject_id=subject_id;
	}
	
	

}
