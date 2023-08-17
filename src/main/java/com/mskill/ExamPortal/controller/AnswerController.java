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

import com.mskill.ExamPortal.JDBCRepository.AnswerJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.ModeratorAnswerResponse;
import com.mskill.ExamPortal.service.AnswerService;

@RestController
@RequestMapping("/api/v1")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	@Autowired
	AnswerJDBCRepository answerJDBC;
	
	@GetMapping("/answer")
	 public List<ModeratorAnswerResponse> findAllAnswerView() {
	        return answerService.findAllAnswerView();
	    }
	 
	//Get answer by studnet_id
	@GetMapping("/answer/{id}")
    public ResponseEntity<ModeratorAnswerResponse> findAnswerViewById(@PathVariable(value = "id") Long answer_id)
        throws ResourceNotFoundException {
		ModeratorAnswerResponse answer = answerJDBC.findAnswerViewById(answer_id)
          .orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id :: " + answer_id));
        return ResponseEntity.ok().body(answer);
    }
	    
	    /*@PostMapping("/answer")
	    public Answer createAnswer( @RequestBody Answer answer) {
	       return answerService.createAnswer(answer);
	    }*/
}
