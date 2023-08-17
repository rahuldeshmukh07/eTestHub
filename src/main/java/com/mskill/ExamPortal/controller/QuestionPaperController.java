package com.mskill.ExamPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.JDBCRepository.QuestionPaperJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.QuestionPaper;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.service.QuestionPaperService;

@RestController
@RequestMapping("/api/v1")
public class QuestionPaperController {

	@Autowired
	QuestionPaperService qpService;
	@Autowired
	QuestionPaperJDBCRepository qpJDBC;
	
	@GetMapping("/qp")
	 public List<QuestionPaperResponse> findAllQPView() {
	        return qpService.findAllQPView();
	    }
	 

	    /*@GetMapping("/qp/{id}")
	    public ResponseEntity<QuestionPaper> getQPById(@PathVariable(value = "id") Integer qp_id)
	        throws ResourceNotFoundException {
	        return ResponseEntity.ok().body(qpService.getQPById(qp_id));
	    }*/
	
	@GetMapping("/qp/{id}")
    public ResponseEntity<QuestionPaperResponse> findQPViewById(@PathVariable(value = "id") Long qp_id)
        throws ResourceNotFoundException {
		QuestionPaperResponse qp = qpJDBC.findQPViewById(qp_id)
          .orElseThrow(() -> new ResourceNotFoundException("Question Paper not found for this id :: " + qp_id));
        return ResponseEntity.ok().body(qp);
    }
	    
	    @PostMapping("/qp")
	    public QuestionPaper createQP( @RequestBody QuestionPaper qp) {
	       return qpService.createQP(qp);
	    }
}
