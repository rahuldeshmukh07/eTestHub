package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "question_paper")
public class QuestionPaper{
	
	private int qp_id;
	private int subject_id;
	private int exam_id;
	private int marks=5;
	private int created_by = 101;
	private Time start_time;
	private Date start_date;
    private String question;
    private Time end_time;
    private int noofquestions=10;
    
    public QuestionPaper() {}
    
    public QuestionPaper(int qp_id, int subject_id, int exam_id, int marks, int created_by, Time start_time,
			Date start_date, String question, Time end_time, int noofquestions) {
		super();
		this.qp_id = qp_id;
		this.subject_id = subject_id;
		this.exam_id = exam_id;
		this.marks = marks;
		this.created_by = created_by;
		this.start_time = start_time;
		this.start_date = start_date;
		this.question = question;
		this.end_time = end_time;
		this.noofquestions = noofquestions;

		
	}
    
    public QuestionPaper(int qp_id, int subject_id, int exam_id,  int marks, int created_by,
			String question, int noofquestions) {
		this.qp_id = qp_id;
		this.subject_id = subject_id;
		this.exam_id = exam_id;
		this.marks = marks;
		this.created_by = created_by;
		this.question = question;
		this.noofquestions = noofquestions;
	}

    /*public QuestionPaper(int duration, int marks, int created_by, Time start_time,
			Date start_date, int NoOfQuestions) {
		this.start_time = start_time;
		this.duration = duration;
		this.start_date = start_date;
		this.marks = marks;
		this.NoOfQuestions = NoOfQuestions;
		this.created_by = created_by;
	}*/
    
	@Id
	@GeneratedValue(generator = "qp_sequence")
	@SequenceGenerator(schema = "public", name = "qp_seqeunce", sequenceName = "qp_sequence", allocationSize = 1)
    @Column(name = "qp_id", nullable = false)
	public int getQp_id() {
		return qp_id;
	}

	public void setQp_id(int qp_id) {
		this.qp_id = qp_id;
	}

	@Column(name= "subject_id" , nullable=false)
	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	@Column(name= "exam_id" , nullable=false)
	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	@Column(name= "question" , nullable=false)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name= "start_time" , nullable=false)
	public Time getStart_time() {
		return start_time;
	}


	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	@Column(name= "marks" , nullable=false)
	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}


	@Column(name= "created_by" , nullable=false)
	public int getCreated_by() {
		return created_by;
	}
	
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}


	@Column(name= "start_date" , nullable=false)
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	@Column(name= "noofquestions" , nullable=false)
	public int getNoofquestions() {
		return noofquestions;
	}

	public void setNoofquestions(int noofquestions) {
		this.noofquestions = noofquestions;
	}

	@Column(name= "end_time" , nullable=false)
	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "QuestionPaper [qp_id=" + qp_id + ", subject_id=" + subject_id + ", exam_id=" + exam_id + ", marks="
				+ marks + ", created_by=" + created_by + ", start_time=" + start_time + ", start_date=" + start_date
				+ ", question=" + question + ", end_time=" + end_time + ", noofquestions=" + noofquestions + "]";
	}
}
