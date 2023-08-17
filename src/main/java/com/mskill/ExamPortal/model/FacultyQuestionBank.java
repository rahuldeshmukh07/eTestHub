package com.mskill.ExamPortal.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "question_bank")
public class FacultyQuestionBank {

	@Id
	@GeneratedValue(generator = "qb_sequence")
	@SequenceGenerator(schema = "public", name = "qb_sequence", sequenceName = "qb_sequence", allocationSize = 1)
	private int qb_id;
	private int faculty_id;
	private int subject_id;
	private int exam_id;
	private String questions;


	public FacultyQuestionBank() {

	}
	
	
	public int getqb_id() {
		return qb_id;
	}

	public void setqb_id(int qb_id) {
		this.qb_id = qb_id;
	}



	public int getfaculty_id() {
		return faculty_id;
	}



	public void setfaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}



	public int getsubject_id() {
		return subject_id;
	}



	public void setsubject_id(int subject_id) {
		this.subject_id = subject_id;
	}



	public int getexam_id() {
		return exam_id;
	}



	public void setexam_id(int exam_id) {
		this.exam_id = exam_id;
	}



	public String getquestions() {
		return questions;
	}

	public void setquestions(String questions) {
		this.questions = questions;
	}


	@Override
	public String toString() {
		return "QuestionBank [qb_id=" + qb_id + ", faculty_id=" + faculty_id + ", subject_id=" + subject_id + ", exam_id="
				+ exam_id + ", questions=" + questions + "]";
	}
	
	


}
