package com.mskill.ExamPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.mskill.ExamPortal.JDBCRepository.QuestionBankJDBCRepository;
import com.mskill.ExamPortal.JPARepository.QuestionBankJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.model.QuestionBank;
import com.mskill.ExamPortal.model.QuestionPaper;
import com.mskill.ExamPortal.response.QuestionBankResponse;

@Service
@Transactional
public class QuestionBankService {

	@Autowired
	 private QuestionBankJPARepository qbankRepository;
	
	@Autowired
	 private QuestionBankJDBCRepository qbankJDBC;
	
	
	
	public List<QuestionBankResponse> getAllQBank() {
		List<QuestionBank> qbank =  qbankRepository.findAll();
       
       List<QuestionBankResponse> qbankResponse = new ArrayList<>();
       
       
       for (int i = 0; i < qbank.size(); i++) {
        QuestionBankResponse qbankView = new QuestionBankResponse();
        qbankView.setQb_id(qbank.get(i).getQb_id());
        qbankView.setFaculty_id(qbank.get(i).getFaculty_id());
        qbankView.setSubject_id(qbank.get(i).getSubject_id());
        qbankView.setExam_id(qbank.get(i).getExam_id());
        qbankView.setQuestions(qbank.get(i).getQuestions());
  		 qbankResponse.add(qbankView);
       }
       
       return qbankResponse;
       
   }

	public QuestionBankResponse getQBankById(int qb_id) throws ResourceNotFoundException{
		 QuestionBank qbank = qbankRepository.findById(qb_id).orElseThrow(() -> new ResourceNotFoundException("Question Bank not found for this id :: " + qb_id));
		 
		 QuestionBankResponse qbankView = new QuestionBankResponse();
		 qbankView.setQb_id(qbank.getQb_id());
	        qbankView.setFaculty_id(qbank.getFaculty_id());
	        qbankView.setSubject_id(qbank.getSubject_id());
	        qbankView.setExam_id(qbank.getExam_id());
	        qbankView.setQuestions(qbank.getQuestions());
		 
		 
		 return qbankView;         
	 }
	
	public Long[] createQuestion(QuestionPaper questionpaper) {
		 return qbankJDBC.insertQuestion(questionpaper);
		    
	 }
	
	 public QuestionBank createQBank(@RequestBody QuestionBank qbank) {
	        return qbankRepository.save(qbank);
	    }

	public List<QuestionBankResponse> findAllQBankView() {
		 List<QuestionBankResponse> qbank =  qbankJDBC.findAllQBankView();
	     return qbank;
	}
	
	/*public List<QuestionBankResponse> generateQPBySubId(long subject_id,int noofqu) {
		 List<QuestionBankResponse> qbank =  qbankJDBC.generateQPBySubId(subject_id, 10);
	     return qbank;
	}*/
}
