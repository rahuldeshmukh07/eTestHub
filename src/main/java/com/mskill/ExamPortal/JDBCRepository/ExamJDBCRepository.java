/**
 * 
 */
package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;

/**
 * @author 1838177
 *
 */
public interface ExamJDBCRepository {

	
	 List<QuestionPaperResponse> findQuestionPaperByID(Long id);
	 String insert(Answer answer);
	 int update(Answer answer, long answer_id);
	 List<AnswerResponse> findAnswerById(Long student_id, Long qp_id);
	 TimeTableResponse findOneQuestionPaperTime(Long id);


}
