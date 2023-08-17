package com.mskill.ExamPortal.response;

import java.sql.Date;


public class TaskBarResponse {
	private String subject_name;
	private Date created_on;
	private int answer_id;
	
	
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public Date getCreated_on() {
		return created_on;
	}
	
	
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
	
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	public TaskBarResponse() {
		
	}
	
	public TaskBarResponse(String subject_name, Date created_on, int answer_id) {
		
		this.subject_name = subject_name;
		this.created_on = created_on;
		this.answer_id = answer_id;
	}
	@Override
	public String toString() {
		return "TaskBarResponse [subject_name=" + subject_name + ", created_on=" + created_on + ", answer_id="
				+ answer_id + "]";
	}
	
	
	
	
	
	

}
