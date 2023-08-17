package com.mskill.ExamPortal.model;

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="answer")
public class Answer {

	private int answer_id;
	private int student_id;
	private int qp_id;
	private Time start_time;
	private Time end_time;
	private int evaluator_marks;
	private boolean evaluation_flag;
	private String answer;
	private int faculty_id;
	
	public Answer() {}

	public Answer(int answer_id, int student_id, int qp_id, Time start_time,
			Time end_time, int evaluator_marks, boolean evaluation_flag, String answer, int faculty_id) {
		super();
		this.answer_id = answer_id;
		this.student_id = student_id;
		this.qp_id = qp_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.evaluator_marks = evaluator_marks;
		this.evaluation_flag = evaluation_flag;
		this.answer=answer;
		this.faculty_id=faculty_id;
	}
	
	//Called by Student Controller
	public Answer(int student_id, int qp_id, String answer, Time start_time, Time end_time) {
		
		this.student_id = student_id;
		this.qp_id = qp_id;
		this.answer = answer;
		this.start_time = start_time;
		this.end_time = end_time;
		this.evaluator_marks = 0;
		this.evaluation_flag = false;
//		this.active_flag = active_flag;
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
	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	@Column(name = "qp_id", nullable = false)
	public int getQp_id() {
		return qp_id;
	}

	public void setQp_id(int qp_id) {
		this.qp_id = qp_id;
	}


	@Column(name = "start_time", nullable = false)
	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time", nullable = false)
	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
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
	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}

	@Override
	public String toString() {
		return "Answer [answer_id=" + answer_id + ", student_id=" + student_id + ", qp_id=" + qp_id + ", start_time="
				+ start_time + ", end_time=" + end_time + ", evaluator_marks=" + evaluator_marks + ", evaluation_flag="
				+ evaluation_flag + ", answer=" + answer + ", faculty_id=" + faculty_id + "]";
	}

}
