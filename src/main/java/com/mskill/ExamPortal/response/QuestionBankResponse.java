package com.mskill.ExamPortal.response;

public class QuestionBankResponse {

	private int qb_id;
    private int faculty_id;
 	private int subject_id;
 	private int exam_id;
 	private String questions;
 	
 	public QuestionBankResponse() {}
 	
	public QuestionBankResponse(int qb_id, int faculty_id, int subject_id, int exam_id, String questions) {
		super();
		this.qb_id = qb_id;
		this.faculty_id = faculty_id;
		this.subject_id = subject_id;
		this.exam_id = exam_id;
		this.questions = questions;
	}
	
	public QuestionBankResponse(int subject_id, int exam_id, String questions) {
		this.subject_id = subject_id;
		this.exam_id = exam_id;
		this.questions = questions;
	}

	public int getQb_id() {
		return qb_id;
	}

	public void setQb_id(int qb_id) {
		this.qb_id = qb_id;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "QuestionBankResponse [subject_id=" + subject_id + ", exam_id=" + exam_id + ", questions=" + questions
				+ "]";
	}

	
 	
 	
}
