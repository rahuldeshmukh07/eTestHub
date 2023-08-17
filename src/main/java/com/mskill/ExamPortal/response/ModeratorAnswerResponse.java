package com.mskill.ExamPortal.response;

import java.sql.Time;

public class ModeratorAnswerResponse {

	private int answer_id;
	private long student_id;
	private int qp_id;
	private Time start_time;
	private Time end_time;
	private int evaluator_marks;
	private String answer;
	private int faculty_id;
	
	public ModeratorAnswerResponse(int answer_id, long student_id, int qp_id, Time start_time, Time end_time, int evaluator_marks,
			String answer, int faculty_id) {
		this.answer_id = answer_id;
		this.student_id = student_id;
		this.qp_id = qp_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.evaluator_marks = evaluator_marks;
		this.answer = answer;
		this.faculty_id = faculty_id;
	}

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public int getQp_id() {
		return qp_id;
	}

	public void setQp_id(int qp_id) {
		this.qp_id = qp_id;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public int getEvaluator_marks() {
		return evaluator_marks;
	}

	public void setEvaluator_marks(int evaluator_marks) {
		this.evaluator_marks = evaluator_marks;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	
	
	
}
