/**
 * 
 */
package com.mskill.ExamPortal.controller;

/**
 * @author 1838177
 *
 */
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.model.Student;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.service.StudentService;


@RestController
@RequestMapping("/api/v1")
public class StudentController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	@Autowired
	StudentService studentService;
	HttpStatus status;
	
	 @GetMapping("/student")
	 public ResponseEntity<List<StudentResponse>> getAllStudents() {
		 List<StudentResponse> temp = studentService.getAllStudents();
		 if(temp.size()>0)
	        return ResponseEntity.ok().body(temp);
		 return ResponseEntity.status(404).body(temp);
	    }
	 
	 @GetMapping("/student/dashboard/")
	    public ResponseEntity<HashMap<String,List>> getStudentTimetableById( HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException {
		 HttpSession session = request.getSession(false);
	    	String sessionId = (String) session.getAttribute("username");
	    	Long test_id = Long.parseLong(sessionId);
		 StudentResponse temp = studentService.getStudentById(test_id);
		 if(temp!=null)   
	    	 return ResponseEntity.ok().body(studentService.getStudentTimeTableById(test_id));
	     return ResponseEntity.status(status.BAD_REQUEST).body(null);
	    }
	 
	 	
	    @GetMapping("/student/")
	    public ResponseEntity<StudentResponse> getStudentById( HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException
	       {
	    	HttpSession session = request.getSession(false);
	    	String sessionId = (String) session.getAttribute("username");
	    	Long test_id = Long.parseLong(sessionId);
	    	return ResponseEntity.ok().body(studentService.getStudentById(test_id));	       
	    }
	    
	    
	    @PostMapping("/student")
	    public Student createStudent( @RequestBody Student student) {
	       return studentService.createStudent(student);
	    }
	   
}
