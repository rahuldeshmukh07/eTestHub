package com.mskill.ExamPortal.response;

import java.sql.Time;
import java.util.Date;

public class QuestionPaperResponse {

	private int qp_id;
	private int subject_id;
	private int exam_id;
	private int marks;
	private Time start_time;
	private Date start_date;
	private String question;
	private Time end_time;
    private int noofquestions;

	private String exam_name;
	private String subject_name;
//	private Object questions;
	private Date date;
//	private int passing_marks;

	
	
	public QuestionPaperResponse(String exam_name,
	 String subject_name,
	 int qp_id,
	 Time start_time,
	 String question,
	 Time end_time,
	 Date date,
	 int marks) {
		this.exam_name = exam_name;
		this.subject_name = subject_name;
		this.qp_id = qp_id;
		this.start_time = start_time;
		this.question = question;
		this.end_time = end_time;
		this.date = date;
		this.marks = marks;
	}

    
	public QuestionPaperResponse() {}

	public QuestionPaperResponse(int qp_id, int subject_id, int exam_id, int marks, Time start_time, Date start_date,
			String question, Time end_time, int noofquestions) {
		super();
		this.qp_id = qp_id;
		this.subject_id = subject_id;
		this.exam_id = exam_id;
		this.marks = marks;
		this.start_time = start_time;
		this.start_date = start_date;
		this.question = question;
		this.end_time = end_time;
		this.noofquestions = noofquestions;
	}

	public int getQp_id() {
		return qp_id;
	}

	public void setQp_id(int qp_id) {
		this.qp_id = qp_id;
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

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public int getNoofquestions() {
		return noofquestions;
	}

	public void setNoofquestions(int noofquestions) {
		this.noofquestions = noofquestions;
	}


	public String getExam_name() {
		return exam_name;
	}


	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}


	public String getSubject_name() {
		return subject_name;
	}


	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}



	
}