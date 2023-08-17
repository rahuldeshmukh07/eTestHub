package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="subject")

public class Subject {
	private int  subject_id;
	private String subject_name;
	private int created_by;
	
	public Subject() {
		
	}
	
	@Id
	@GeneratedValue(generator = "subject_sequence")
	@SequenceGenerator(schema = "public", name = "subject_seqeunce", sequenceName = "subject_sequence", allocationSize = 1)
    
	@Column(name = "subject_id", nullable = false)
		public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	@Column(name = "subject_name", nullable = false)
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	@Column(name = "created_by", nullable = false)
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	public Subject(int subject_id, String subject_name, int created_by) {
		this.subject_id = subject_id;
		this.subject_name = subject_name;
		this.created_by = created_by;
	}
	@Override
	public String toString() {
		return "Subject [subject_id=" + subject_id + ", subject_name=" + subject_name + ", created_by=" + created_by
				+ "]";
	}
	
	
	

}
