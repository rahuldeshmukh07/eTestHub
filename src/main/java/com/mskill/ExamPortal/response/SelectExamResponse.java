package com.mskill.ExamPortal.response;

public class SelectExamResponse {
	private String exam_name;
	private int exam_id;

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	
	public SelectExamResponse() {
		
	}

	public SelectExamResponse(String exam_name, int exam_id) {
		this.exam_name = exam_name;
		this.setExam_id(exam_id);
	}

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	
	
}
