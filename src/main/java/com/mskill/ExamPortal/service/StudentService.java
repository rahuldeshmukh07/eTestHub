/**
 * 
 */
package com.mskill.ExamPortal.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.model.Student;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mskill.ExamPortal.JDBCRepository.StudentJDBCRepository;
import com.mskill.ExamPortal.JPARepository.StudentJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;

/**
 * @author 1838177
 *
 */

@Service
@Transactional
public class StudentService {

	/**
	 * 
	 */
	
	 @Autowired
	 private StudentJPARepository studentJPA;
	 
	 
	 @Autowired
	 private StudentJDBCRepository studentJDBC;
	 

	 public List<StudentResponse> getAllStudents() {
		 List<StudentResponse> student =  studentJDBC.findAllStudentView();
	        return student;
	        
	    }
	 
	 
	 
	 public StudentResponse getStudentById(long student_id) throws ResourceNotFoundException{
		 StudentResponse student = studentJDBC.findStudentViewById(student_id)
		           .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + student_id));
		 return student;
	 }
	 
	 public HashMap<String,List> getStudentTimeTableById(long student_id) throws ResourceNotFoundException{
	 
		 List<TimeTableResponse> paper = studentJDBC.findStudentTimetableViewById(student_id);
		 List<TimeTableResponse> finishedUp = new ArrayList<TimeTableResponse>();
		 List<TimeTableResponse> future = new ArrayList<TimeTableResponse>();
		 List<TimeTableResponse> now = new ArrayList<TimeTableResponse>();
		 
		 HashMap<String,List> responseList = new HashMap<String,List>();
		 Date date = new Date();
		 String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		 long SystemHour = date.getHours();
		 long SystemMinutes = date.getMinutes();
		 long LocalMinutes = (SystemHour*60) + SystemMinutes;
	 
		 for(int i=0;i<paper.size();i++) {
			 int x = date.compareTo(paper.get(i).getStartDate());
			 String dbDate = paper.get(i).getStartDate().toString();
			 long dbStartHour = paper.get(i).getStartTime().getHours();
			 long dbStartMinute = paper.get(i).getStartTime().getMinutes();	 
			 long dbStartLocalMinutes = (dbStartHour*60) + dbStartMinute;
			 long dbEndHour = paper.get(i).getEnd_time().getHours();
			 long dbEndMinute = paper.get(i).getEnd_time().getMinutes();	 
			 long dbEndLocalMinutes = (dbEndHour*60) + dbEndMinute;
			 System.out.println("VAlue Of X: " + x);
			 System.out.println(paper.get(i).getStartDate().toString());
			 System.out.println(modifiedDate);
			 System.out.println("Paper Id: " + paper.get(i).getSubject_id());
			 System.out.println(modifiedDate.equals(dbDate));
			 System.out.println(LocalMinutes + " equals " + dbEndLocalMinutes);

			 if(x>0 && modifiedDate.equals(dbDate)) {
				 if(dbStartLocalMinutes >= LocalMinutes)
					 future.add(paper.get(i));
				 else {
					 if(dbEndLocalMinutes >= LocalMinutes)
						 now.add(paper.get(i));
					 else
						finishedUp.add(paper.get(i));
				 }
			 }
			 else if(x>0)
				 finishedUp.add(paper.get(i));
			 else 
				 future.add(paper.get(i));

		 }
		 
		 for(int i=0;i<now.size();i++) {
			 List<String> temp = studentJDBC.checkifPaperDone(student_id,now.get(i).getSubject_id());
			 if(!temp.isEmpty())
				 {finishedUp.add(now.get(i));
				  now.remove(i);
				 }
		 }
		 
		 
		 responseList.put("Now", now);
		 responseList.put("Future", future);
		 responseList.put("Finished", finishedUp);
		 return responseList;
	 }

	 
	 public Student createStudent(Student student) {
	        return studentJPA.save(student);
	    }
	 
	 

}
