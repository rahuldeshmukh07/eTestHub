/**
 * 
 */
package com.mskill.ExamPortal.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.JDBCRepository.StudentJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.service.ExamService;

/**
 * @author 1838177
 *
 */

@RestController
@RequestMapping("/api/v1")
public class ExamController extends HttpServlet {

	/**
	 * 
	 */
	
	@Autowired
	ExamService examService;
	
	 @Autowired
	 private StudentJDBCRepository studentJDBC;
	
	 @GetMapping("/exam/{id}")
	    public ResponseEntity<List<QuestionPaperResponse>> getQuestionPaper(@PathVariable(value = "id") Long subject_id) throws ResourceNotFoundException
	   {
		 List<QuestionPaperResponse> temp = examService.getQuestionPaper(subject_id);
		 if(temp.size()>0)
	    	return ResponseEntity.ok().body(temp);
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(temp);
	    }
	 
	 @PostMapping("/exam/fetchAnswer")
	    public ResponseEntity<AnswerResponse> getAnswer(@RequestBody Answer answer, HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException
	   {
		 HttpSession session = request.getSession(false);
	    	String sessionId = (String) session.getAttribute("username");
	    	int test_id = Integer.parseInt(sessionId);
	    	answer.setStudent_id(test_id);
		 return ResponseEntity.ok().body(examService.getAnswer(answer));
	    }
	 
	 @PostMapping("/exam/createAnswer")
	    public ResponseEntity<Long> createAnswer( @RequestBody Answer answer, HttpServletRequest request, HttpServletResponse response) {
		 HttpSession session = request.getSession(false);
	    	String sessionId = (String) session.getAttribute("username");
	    	int test_id = Integer.parseInt(sessionId);
	    	answer.setStudent_id(test_id);
			Long temp = examService.createAnswer(answer);
			if(temp==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(temp);
			return ResponseEntity.ok().body(temp);
	    }
	 
	    @PatchMapping("/exam/updateAnswer/{id}")
	    public ResponseEntity<Long> updateAnswer(@PathVariable(value = "id") Long answer_id, @RequestBody Answer answer) throws ResourceNotFoundException {
			int temp = examService.updateAnswer(answer,answer_id);
	    	if(temp==1)
	    		return ResponseEntity.ok().body((long)temp);
	    	return ResponseEntity.badRequest().body((long)temp);
	    }
	
	  
	 @GetMapping("/exam/getMinutes/{subject_id}")
	 public ResponseEntity<Long> getMinutes(@PathVariable(value = "subject_id") Long subject_id){
		 
		 return ResponseEntity.ok().body(examService.getMinutes(subject_id));
	 }
	 
	 @GetMapping("/exam/check/{subject_id}")
	 public Long checkIfAttempted(@PathVariable(value="subject_id") int subject_id, HttpServletRequest request, HttpServletResponse response)
	 {
		 HttpSession session = request.getSession(false);
	    	String sessionId = (String) session.getAttribute("username");
	    	int test_id = Integer.parseInt(sessionId);
	    	List<String> temp = studentJDBC.checkifPaperDone((long)test_id,subject_id);
	    	if(!temp.isEmpty())
	    		return (long) 1;
	    	return (long) 0;
	 }
	
}
