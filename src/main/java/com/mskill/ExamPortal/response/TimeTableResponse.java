/**
 * 
 */
package com.mskill.ExamPortal.response;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.xml.crypto.Data;

/**
 * @author 1838177
 *
 */
public class TimeTableResponse {

	/**
	 * 
	 */
	public TimeTableResponse() {
		// TODO Auto-generated constructor stub
	}
//	private int qp_id;
	private int noofquestions;
	private int subject_id;
	private String exam_name;
	private String subject_name;
	private Date start_date;
	private int marks;
//	private int passing_marks;
	private Time start_time;
	private Time end_time;
	
	public TimeTableResponse( String exam_name,
			int noofquestions,
	 int subject_id,
	 String subject_name,
	 Date start_date,
	 int marks,
	 Time start_time,
	 Time end_time) {
		this.exam_name = exam_name;
		this.noofquestions=noofquestions;
		this.subject_id = subject_id;
		this.subject_name = subject_name;
		this.start_date = start_date;
		this.marks = (marks*this.noofquestions);
		this.start_time = start_time;
		this.end_time = end_time;
	}
	

	public int getNoofquestions() {
		return noofquestions;
	}








	public void setNoofquestions(int noofquestions) {
		this.noofquestions = noofquestions;
	}








	public int getSubject_id() {
		return subject_id;
	}









	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}









	public Time getEnd_time() {
		return end_time;
	}









	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}









	public Time getStartTime() {
		return start_time;
	}




	public void setStartTime(Time start_time) {
		this.start_time = start_time;
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
	
	
	public Date getStartDate() {
		return start_date;
	}
	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	

	
	
}
