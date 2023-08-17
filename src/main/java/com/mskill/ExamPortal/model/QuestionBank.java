package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="question_bank")
public class QuestionBank {
    private int qb_id;
    private int faculty_id;
 	private int subject_id;
 	private int exam_id;
 	private String questions;
 	private int created_by;
 	
 	public QuestionBank() {}

	public QuestionBank(int qb_id, int faculty_id, int subject_id, int exam_id, String questions, int created_by) {
		this.qb_id = qb_id;
		this.faculty_id = faculty_id;
		this.subject_id = subject_id;
		this.exam_id = exam_id;
		this.questions = questions;
		this.created_by = created_by;
	}
 	
 	
 	@Id
	@GeneratedValue(generator = "qb_sequence")
	@SequenceGenerator(schema = "public", name = "qb_seqeunce", sequenceName = "qb_sequence", allocationSize = 1)
 	@Column(name = "qb_id", nullable = false)
 	public int getQb_id() {
		return qb_id;
	}

	public void setQb_id(int qb_id) {
		this.qb_id = qb_id;
	}
 	
	@Column(name = "faculty_id", nullable = false)
	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	@Column(name = "subject_id", nullable = false)
	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	@Column(name = "exam_id", nullable = false)
	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	@Column(name = "questions", nullable = false)
	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}
	
	@Column(name = "created_by", nullable = false)
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	

	@Override
	public String toString() {
		return "QuestionBank [qb_id=" + qb_id + ", faculty_id=" + faculty_id + ", subject_id=" + subject_id
				+ ", exam_id=" + exam_id + ", questions=" + questions + ", created_by=" + created_by + "]";
	}	
}
