/**
 * 
 */
package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;

/**
 * @author 1838177
 *
 */
@Repository
public class ExamJDBCRepositoryImpl implements ExamJDBCRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<QuestionPaperResponse> findQuestionPaperByID(Long id) {
		return jdbcTemplate.query(
                "SELECT DISTINCT ON (qp_id) v.exam_name,w.subject_name, q.qp_id,q.start_time,q.question, q.end_time, q.start_date, q.marks \r\n" + 
                "FROM public.question_paper q, public.exam v, public.subject w, public.student x , public.sub_branch y where \r\n" + 
                "q.exam_id=v.exam_id and q.subject_id=w.subject_id and x.branch_id = y.branch_id and q.subject_id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new QuestionPaperResponse(
                        		rs.getString("exam_name"),
                        		rs.getString("subject_name"),
                        		rs.getInt("qp_id"),
                        		rs.getTime("start_time"),
                        		rs.getString("question"),
                        		rs.getTime("end_time"),
                        		rs.getDate("start_date"),
                        		rs.getInt("marks")
                        		
                        		                        )
        );
	}
	

	@Override
	public String insert(Answer answer) {
		String sql = "INSERT INTO public.answer"+
	"(answer_id, student_id, qp_id,start_time, end_time,evaluator_marks, evaluation_flag, answer)"
	+"VALUES (nextval('answer_sequence'), ?, ?, ?, ?,?,?,  ?) RETURNING answer";
		
		return jdbcTemplate.queryForObject(sql,new Object[] {
				answer.getStudent_id(),
				answer.getQp_id(),
				answer.getStart_time(),
				answer.getEnd_time(),				
				answer.getEvaluator_marks(),
				answer.isEvaluation_flag(),
				answer.getAnswer()
		}, String.class);
		
	}
	
	
	@Override
	public int update(Answer answer, long answer_id) {
		String updateQuery = "update public.answer set answer= ? where answer_id = ?";
		return jdbcTemplate.update(updateQuery,answer.getAnswer() ,answer_id);
		
	}


	@Override
	public List<AnswerResponse> findAnswerById(Long student_id, Long qp_id) {
		List<AnswerResponse> result = jdbcTemplate.query(
                "SELECT answer_id, student_id, qp_id,answer FROM public.answer WHERE student_id=? and qp_id=?",
                new Object[]{student_id,qp_id},
                (rs, rowNum) ->
                        new AnswerResponse(
                        		rs.getLong("student_id"),
                        		rs.getInt("answer_id"),
                        		rs.getInt("qp_id"),
                        		rs.getString("answer")
                        )
        ); 
		
		return result;
	}
	

	@Override
	public TimeTableResponse findOneQuestionPaperTime(Long id) {
		String query = "SELECT DISTINCT v.exam_name,w.subject_name, u.noofquestions, u.subject_id,u.start_time, u.end_time, u.start_date, u.marks FROM public.question_paper u, public.exam v, public.subject w, public.branch z, public.student x , public.sub_branch y where u.exam_id=v.exam_id and u.subject_id=w.subject_id and w.subject_id = y.subject_id and y.branch_id = z.branch_id and x.branch_id=z.branch_id and u.subject_id=?";
		return jdbcTemplate.queryForObject(
               query,
                new Object[]{id},
                (rs, rowNum) ->
               new TimeTableResponse(
               		rs.getString("exam_name"),
               		rs.getInt("noofquestions"),
               		rs.getInt("subject_id"),
                       rs.getString("subject_name"),
                       rs.getDate("start_date"),
                       rs.getInt("marks"),
                       rs.getTime("start_time"),
                       rs.getTime("end_time")
               )
        );
	}
	
	

}
