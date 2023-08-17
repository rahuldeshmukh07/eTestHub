package com.mskill.ExamPortal.model;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="answer")
public class Answer_Fac {
	

	private int answer_id;
	private long student_id;
	private int qp_id;
	private int evaluator_marks;
	private boolean evaluation_flag;
	private String answer;
	private int faculty_id;
	private Timestamp created_on;
	
	public Answer_Fac() {
		// TODO Auto-generated constructor stub
	}

	public Answer_Fac(int answer_id, long student_id, int qp_id, int evaluator_marks, 
			boolean evaluation_flag, String answer, int faculty_id, Timestamp created_on) {
		this.answer_id = answer_id;
		this.student_id = student_id;
		this.qp_id = qp_id;
		this.evaluator_marks = evaluator_marks;
		this.evaluation_flag = evaluation_flag;
		this.answer = answer;
		this.faculty_id = faculty_id;
		this.created_on = created_on;
	}

	@Id
	@GeneratedValue(generator = "answer_sequence")
	@SequenceGenerator(schema = "public", name = "answer_seqeunce", sequenceName = "answer_sequence", allocationSize = 1)
	
    @Column(name = "answer_id", nullable = false)
	public int getAnswer_id() {
		return answer_id;
	}
	
	
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	@Column(name = "student_id", nullable = false)
	public long getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}
	
	@Column(name = "qp_id", nullable = false)
	public int getQp_id() {
		return qp_id;
	}
	
	public void setQp_id(int qp_id) {
		this.qp_id = qp_id;
	}
	
	@Column(name = "evaluator_marks", nullable = false)
	public int getEvaluator_marks() {
		return evaluator_marks;
	}

	public void setEvaluator_marks(int evaluator_marks) {
		this.evaluator_marks = evaluator_marks;
	}
	
	
	@Column(name = "evaluation_flag", nullable = false)
	public boolean isEvaluation_flag() {
		return evaluation_flag;
	}

	public void setEvaluation_flag(boolean evaluation_flag) {
		this.evaluation_flag = evaluation_flag;
	}
	
	@Column(name = "answer", nullable = false)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(name = "faculty_id", nullable = false)
	public long getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	
	@Column(name = "created_on", nullable = false)
	public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	@Override
	public String toString() {
		return "Answer [answer_id=" + answer_id + ", student_id=" + student_id + ", qp_id=" + qp_id
				+ ", evaluator_marks=" + evaluator_marks  + ", evaluation_flag="
				+ evaluation_flag + ", answer=" + answer + ", faculty_id=" + faculty_id + ", created_on=" + created_on
				+ "]";
	}
	
	
	

	
	
	
	
}
