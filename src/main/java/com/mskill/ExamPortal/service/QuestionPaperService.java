package com.mskill.ExamPortal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.QuestionPaper;
import com.mskill.ExamPortal.JDBCRepository.QuestionPaperJDBCRepository;
import com.mskill.ExamPortal.JPARepository.QuestionPaperJPARepository;
import com.mskill.ExamPortal.response.QuestionPaperResponse;

@Service
@Transactional
public class QuestionPaperService {

	@Autowired
	 private QuestionPaperJPARepository qpRepository;
	
	@Autowired
	 private QuestionPaperJDBCRepository qpJDBC;
	
	public List<QuestionPaperResponse> getAllQP() {
		List<QuestionPaper> qp =  qpRepository.findAll();
        
        List<QuestionPaperResponse> qpResponse = new ArrayList<>();
        
        
        for (int i = 0; i < qp.size(); i++) {
         QuestionPaperResponse qpView = new QuestionPaperResponse();
         qpView.setQp_id(qp.get(i).getQp_id());
         qpView.setSubject_id(qp.get(i).getSubject_id());
         qpView.setExam_id(qp.get(i).getExam_id());
         qpView.setMarks(qp.get(i).getMarks());
         qpView.setStart_time(qp.get(i).getStart_time());
         qpView.setStart_date(qp.get(i).getStart_date());
         qpView.setQuestion(qp.get(i).getQuestion());
         qpView.setEnd_time(qp.get(i).getEnd_time());
         qpView.setNoofquestions(qp.get(i).getNoofquestions());
   		 qpResponse.add(qpView);
        }
        
        return qpResponse;
    }
   

	public QuestionPaperResponse getQPById(int qp_id) throws ResourceNotFoundException{
		 QuestionPaper qp = qpRepository.findById(qp_id).orElseThrow(() -> new ResourceNotFoundException("Question Paper not found for this id :: " + qp_id));
		 
		 QuestionPaperResponse qpView = new QuestionPaperResponse();
		 qpView.setQp_id(qp.getQp_id());
         qpView.setSubject_id(qp.getSubject_id());
         qpView.setExam_id(qp.getExam_id());
         qpView.setMarks(qp.getMarks());
         qpView.setStart_time(qp.getStart_time());
         qpView.setStart_date(qp.getStart_date());
         qpView.setQuestion(qp.getQuestion());
         qpView.setEnd_time(qp.getEnd_time());
         qpView.setNoofquestions(qp.getNoofquestions());
		 
		 return qpView;         
	 }
	 
	 public QuestionPaper createQP(@RequestBody QuestionPaper qp) {
	        return qpRepository.save(qp);
	    }

	public List<QuestionPaperResponse> findAllQPView() {
		 List<QuestionPaperResponse> qp =  qpJDBC.findAllQPView();
	     return qp;
	}
}