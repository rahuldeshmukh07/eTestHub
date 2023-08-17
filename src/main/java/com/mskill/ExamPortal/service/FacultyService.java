
package com.mskill.ExamPortal.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.mskill.ExamPortal.model.FacultyQuestionBank;
import com.mskill.ExamPortal.response.EvaluationResponse;
import com.mskill.ExamPortal.response.FacultyResponse;
import com.mskill.ExamPortal.response.SelectBranchResponse;
import com.mskill.ExamPortal.response.SelectExamResponse;
import com.mskill.ExamPortal.response.SelectSubjectResponse;
import com.mskill.ExamPortal.response.TaskBarResponse;
import com.mskill.ExamPortal.JDBCRepository.FacultyJDBCRepository;
import com.mskill.ExamPortal.JPARepository.Questionrepository;

import com.mskill.ExamPortal.exception.ResourceNotFoundException;

@Service
@Transactional
public class FacultyService {
		
	@Autowired
	 private Questionrepository questionBankJPA;

	 @Autowired
	 private FacultyJDBCRepository facultyJDBC;


	 public List<FacultyResponse> getAllFaculties() {
		 List<FacultyResponse> faculty =  facultyJDBC.getAllFacultyView();
	        return faculty;
	 }
		 
		 
	  public FacultyResponse getFacultyById(int faculty_id) throws ResourceNotFoundException{
		
		  	FacultyResponse faculty = facultyJDBC.getFacultyViewById(faculty_id)
		           .orElseThrow(() -> new ResourceNotFoundException("Faculty not found for this id :: " + faculty_id));

		 return faculty;   
	 } 

	 
	  
	 public List<SelectExamResponse> getExamName(int faculty_id) throws ResourceNotFoundException{
		 
		 List<SelectExamResponse> exam = facultyJDBC.getExamNameView();
		 return exam;
	 }
	 
	 public List<SelectSubjectResponse> getSubjectName(int faculty_id) throws IOException {
		 List<SelectSubjectResponse> subject = facultyJDBC.getSubjectNameView(faculty_id);
		 return subject;
	 }

	 public List<FacultyQuestionBank> setQuestionBank(List<FacultyQuestionBank> quest) throws ResourceNotFoundException {
		 return questionBankJPA.saveAll(quest);
	 }

	 public List<TaskBarResponse> getTaskBarById(int faculty_id) throws ResourceNotFoundException {
		 	List<TaskBarResponse> answer = facultyJDBC.getTaskBarViewById(faculty_id);
		 	return answer;
		
	 }

	 public List<EvaluationResponse> getEvaluationPage(int answer_id) throws ResourceNotFoundException {
			List<EvaluationResponse> evaluate= facultyJDBC.getEvaluateAnswerPage(answer_id);
			return evaluate;
		}
	 
	 public int setMarks(int answer_id,int marks) throws ResourceNotFoundException {
			
			
			return facultyJDBC.updateMarks(answer_id, marks);
			
		}
	 
	 /*		public FacultyResponse getFacultyLoginDetailsById(int faculty_id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
	  	FacultyResponse faculty = facultyJDBC.getFacultyLoginDetailsById(faculty_id)
		           .orElseThrow(() -> new ResourceNotFoundException("Faculty not found for this id :: " + faculty_id));

		return faculty;
	}
*/

	 
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 