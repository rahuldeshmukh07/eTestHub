package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.mskill.ExamPortal.response.EvaluationResponse;
import com.mskill.ExamPortal.response.FacultyResponse;
import com.mskill.ExamPortal.response.SelectBranchResponse;
import com.mskill.ExamPortal.response.SelectSubjectResponse;
import com.mskill.ExamPortal.response.TaskBarResponse;
import com.mskill.ExamPortal.response.SelectExamResponse;


public interface FacultyJDBCRepository{
	
	List<FacultyResponse> getAllFacultyView();
	Optional<FacultyResponse> getFacultyViewById(int id);
	List<SelectSubjectResponse> getSubjectNameView(int faculty_id);
	List<SelectExamResponse> getExamNameView();
	List<TaskBarResponse> getTaskBarViewById(int id);
	List<EvaluationResponse> getEvaluateAnswerPage(int answer_id);
	int updateMarks(int answer_id, int marks);
	
	/*	Optional<FacultyResponse> getFacultyLoginDetailsById(int faculty_id);
	*/	
}

