package com.mskill.ExamPortal.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.ResultResponse;
import com.mskill.ExamPortal.service.ResultService;

@RestController
@RequestMapping("/api/v1/result")
public class ResultController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ResultService resultService;
	
	@GetMapping(value="/", produces= {"application/json"})
    public ResponseEntity<List<ResultResponse>> getResults(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException
   {
    	HttpSession session = request.getSession(false);
    	String sessionId = (String) session.getAttribute("username");
    	Long student_id = Long.parseLong(sessionId);

    	return ResponseEntity.ok().body(resultService.getResultByStudent(student_id));
    }
}
