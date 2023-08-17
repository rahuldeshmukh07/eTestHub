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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.JDBCRepository.QuestionBankJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.QuestionBank;
import com.mskill.ExamPortal.model.QuestionPaper;
import com.mskill.ExamPortal.response.ModeratorResponse;
import com.mskill.ExamPortal.response.QuestionBankResponse;
import com.mskill.ExamPortal.service.ModeratorService;
import com.mskill.ExamPortal.service.QuestionBankService;

@RestController
@RequestMapping("/api/v1")
public class QuestionBankController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	QuestionBankService qbankService;
	@Autowired
	QuestionBankJDBCRepository qbankJDBC;
	ModeratorService moderatorService;
	@GetMapping("/qbank")
	 public List<QuestionBankResponse> findAllQBankView() {
	        return qbankService.findAllQBankView();
	    }
	
	 
	@GetMapping("/qbank/{id}")
    public ResponseEntity<QuestionBankResponse> findQBankViewById(@PathVariable(value = "id") Long qb_id)
        throws ResourceNotFoundException {
		QuestionBankResponse qbank = qbankJDBC.findQBankViewById(qb_id)
          .orElseThrow(() -> new ResourceNotFoundException("Question Bank not found for this id :: " + qb_id));
        return ResponseEntity.ok().body(qbank);
    }
	
	@GetMapping("/qbank/subject/")
	public Object generateQPBySubId(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException {
		/*System.out.println("working");
		HttpSession session = request.getSession(false);
		System.out.println("working1");
		String sessionId = (String) session.getAttribute("username");
		System.out.println("working2");
		int test_id = Integer.parseInt(sessionId);
		long modId = (long) test_id;
		System.out.println("working3"+test_id);
		//call service layer
		//fetch from db moderator subject id - returntype long
		//store that in a long var
		//pass that in your jdbc
		ModeratorResponse temp = moderatorService.getModById(modId);
		System.out.println("working4");
		//Long subid=(long) temp.getSubject_id();
		System.out.println(subid);
		//Long sub=(long) 14;*/
		return qbankJDBC.generateQPBySubId(ModeratorController.subid,5,ModeratorController.modid);
	}
	
	@PostMapping("/qbank/createQuestion")
    public Long[] createQuestion( @RequestBody QuestionPaper questionpaper) {
		return qbankService.createQuestion(questionpaper);
    }
	    
	    @PostMapping("/qbank")
	    public QuestionBank createQBank( @RequestBody QuestionBank qbank) {
	       return qbankService.createQBank(qbank);
	    }
}
