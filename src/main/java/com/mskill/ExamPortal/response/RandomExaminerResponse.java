package com.mskill.ExamPortal.response;

public class RandomExaminerResponse {
	private int faculty_id;
	
	public RandomExaminerResponse() {}

	public RandomExaminerResponse(int faculty_id) {
		this.faculty_id=faculty_id;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
}
