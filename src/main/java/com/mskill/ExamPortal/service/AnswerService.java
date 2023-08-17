package com.mskill.ExamPortal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.mskill.ExamPortal.JDBCRepository.AnswerJDBCRepository;
import com.mskill.ExamPortal.JPARepository.AnswerJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.ModeratorAnswerResponse;

@Service
@Transactional
public class AnswerService {

	@Autowired
	 private AnswerJPARepository answerRepository;
	
	@Autowired
	 private AnswerJDBCRepository answerJDBC;
	
	public List<AnswerResponse> getAllAnswer() {
		List<Answer> answer =  answerRepository.findAll();
       
       List<AnswerResponse> answerResponse = new ArrayList<>();
       
       
       for (int i = 0; i < answer.size(); i++) {
        AnswerResponse answerView = new AnswerResponse();
        answerView.setAnswer_id(answer.get(i).getAnswer_id());
        answerView.setStudent_id(answer.get(i).getStudent_id());
        answerView.setQp_id(answer.get(i).getStudent_id());
        answerView.setStart_time(answer.get(i).getStart_time());
        answerView.setEnd_time(answer.get(i).getEnd_time());
        answerView.setEvaluator_marks(answer.get(i).getEvaluator_marks());
        answerView.setAnswer(answer.get(i).getAnswer());
        answerView.setFaculty_id(answer.get(i).getFaculty_id());
  		 answerResponse.add(answerView);
       }
       
       return answerResponse;
       
   }
  

	public AnswerResponse getAnswerById(int answer_id) throws ResourceNotFoundException{
		 Answer answer = answerRepository.findById(answer_id).orElseThrow(() -> new ResourceNotFoundException("Answer Paper not found for this id :: " + answer_id));
		 
		 AnswerResponse answerView = new AnswerResponse();
		 answerView.setAnswer_id(answer.getAnswer_id());
	        answerView.setStudent_id(answer.getStudent_id());
	        answerView.setQp_id(answer.getStudent_id());
	        answerView.setStart_time(answer.getStart_time());
	        answerView.setEnd_time(answer.getEnd_time());
	        answerView.setEvaluator_marks(answer.getEvaluator_marks());
	        answerView.setAnswer(answer.getAnswer());
	        answerView.setFaculty_id(answer.getFaculty_id());
		 
		 
		 return answerView;         
	 }
	 
	 public Answer createAnswer(@RequestBody Answer answer) {
	        return answerRepository.save(answer);
	    }

	public List<ModeratorAnswerResponse> findAllAnswerView() {
		 List<ModeratorAnswerResponse> answer =  answerJDBC.findAllAnswerView();
	     return answer;
	}
}
