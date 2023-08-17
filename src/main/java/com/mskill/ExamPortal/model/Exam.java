package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="exam")
public class Exam {
	private long exam_id;
	private String exam_name;
	private int created_by;
	
	
	
	public Exam() {
		
	}
	@Id
	@GeneratedValue(generator = "exam_sequence")
	@SequenceGenerator(schema = "public", name = "exam_seqeunce", sequenceName = "exam_sequence", allocationSize = 1)

	@Column(name = "exam_id", nullable = false)
	public long getExam_id() {
		return exam_id;
	}
	public void setExam_id(long exam_id) {
		this.exam_id = exam_id;
	}
	@Column(name = "exam_name", nullable = false)
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	@Column(name = "created_by", nullable = false)
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Exam(long exam_id, String exam_name, int created_by) {
		this.exam_id = exam_id;
		this.exam_name = exam_name;
		this.created_by = created_by;
	}
	@Override
	public String toString() {
		return "Exam [exam_id=" + exam_id + ", exam_name=" + exam_name + ", created_by=" + created_by + "]";
	}
	
	
	
}
