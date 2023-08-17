package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.ModeratorAnswerResponse;

@Repository
public class AnswerJDBCRepositoryImpl implements AnswerJDBCRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
    public List findAllAnswerView() {
        return jdbcTemplate.query(
                "SELECT a.answer_id, a.student_id, a.qp_id, a.start_time, a.end_time, a.evaluator_marks, a.answer, a.faculty_id\r\n" + 
                "	FROM public.answer a;",
                (rs, rowNum) ->
                        new ModeratorAnswerResponse(
                        		rs.getInt("answer_id"),
                        		rs.getInt("student_id"),
                        		rs.getInt("qp_id"),
                        		rs.getTime("start_time"),
                        		rs.getTime("end_time"),
                        		rs.getInt("evaluator_marks"),
                        		rs.getString("answer"),
                        		rs.getInt("faculty_id")
                        )
        );
    }
  
    @Override
    public Optional<ModeratorAnswerResponse> findAnswerViewById(Long id) {
        return jdbcTemplate.queryForObject(
        		"SELECT a.answer_id, a.student_id, a.qp_id, a.start_time, a.end_time, a.evaluator_marks, a.answer, a.faculty_id\r\n" + 
                        "	FROM public.answer a where a.student_id=?;",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new ModeratorAnswerResponse(
                        		rs.getInt("answer_id"),
                        		rs.getInt("student_id"),
                        		rs.getInt("qp_id"),
                        		rs.getTime("start_time"),
                        		rs.getTime("end_time"),
                        		rs.getInt("evaluator_marks"),
                        		rs.getString("answer"),
                        		rs.getInt("faculty_id")
                        ))
        );
 }

	/*@Override
	public Optional<AnswerResponse> findAnswerViewById(Long answer_id) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
