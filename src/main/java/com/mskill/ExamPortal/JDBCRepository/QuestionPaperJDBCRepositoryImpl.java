package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mskill.ExamPortal.response.QuestionPaperResponse;

@Repository
public class QuestionPaperJDBCRepositoryImpl implements QuestionPaperJDBCRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
    public List findAllQPView() {
        return jdbcTemplate.query(
                "SELECT q.qp_id, q.subject_id, q.exam_id, q.marks, q.start_time, q.start_date, q.question, q.end_time, q.noofquestions FROM public.question_paper q;",
                (rs, rowNum) ->
                        new QuestionPaperResponse(
                        		rs.getInt("qp_id"),
                        		rs.getInt("subject_id"),
                        		rs.getInt("exam_id"),
                        		rs.getInt("marks"),
                        		rs.getTime("start_time"),
                        		rs.getDate("start_date"),
                        		rs.getString("question"),
                        		rs.getTime("end_time"),
                        		rs.getInt("noofquestions")
                        )
        );
    }
  
    @Override
    public Optional<QuestionPaperResponse> findQPViewById(Long id) {
        return jdbcTemplate.queryForObject(
        		"SELECT q.qp_id, q.subject_id, q.exam_id, q.marks, q.start_time, q.start_date, q.question, q.end_time, q.noofquestions FROM public.question_paper q where q.qp_id=?;",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new QuestionPaperResponse(
                        		rs.getInt("qp_id"),
                        		rs.getInt("subject_id"),
                        		rs.getInt("exam_id"),
                        		rs.getInt("marks"),
                        		rs.getTime("start_time"),
                        		rs.getDate("start_date"),
                        		rs.getString("question"),
                        		rs.getTime("end_time"),
                        		rs.getInt("noofquestions")
                        ))
        );
 }

	/*@Override
	public Optional<QuestionPaperResponse> findQPViewById(Long qp_id) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
