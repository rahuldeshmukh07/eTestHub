package com.mskill.ExamPortal.JDBCRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.model.ResultResponse;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;

@Repository
public class ResultJDBCRepositoryImpl {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
//	
//	public List<ResultResponse> findResultbyStudentId(Long studentId){
//		return jdbcTemplate.query("Select a.qp_id,a.evaluator_marks,a.student_id ,c.subject_name from answer a \r\n" + 
//				"Inner join question_paper b on a.qp_id=b.qp_id \r\n" + 
//				"inner join subject c on  c.subject_id=b.subject_id\r\n" + "and evaluation_flag=true \r\n" +
//				"and a.student_id=?;"
//				,new Object[] {studentId},
//				(rs, rowNum) ->
//                new ResultResponse(
//                		rs.getInt("qp_id"),
//                        rs.getInt("evaluator_marks"),
//                        rs.getInt("student_id"),
//                        rs.getString("subject_name")
//                ));
//	}

	
	public List<ResultResponse> findResultbyStudentId(Long studentId){
		return jdbcTemplate.query("Select sum(a.evaluator_marks) \"marks\",a.student_id ,c.subject_name, count(a.evaluation_flag) \"count\" from answer a\r\n" + 
				"Inner join question_paper b on a.qp_id=b.qp_id inner join subject c on  c.subject_id=b.subject_id and a.evaluation_flag=true \r\n" + 
				"and a.student_id=? group by a.evaluation_flag,a.student_id ,c.subject_name;"
				,new Object[] {studentId},
				(rs, rowNum) ->
                new ResultResponse(
                        rs.getInt("student_id"),
                        rs.getString("subject_name"),
                        rs.getInt("marks"),
                        rs.getInt("count")
                ));
//		return jdbcTemplate.query("Select a.evaluator_marks, a.evaluation_flag, s.subject_name from public.answer a, public.question_paper q , public.subject s where a.qp_id=q.qp_id and s.subject_id=q.subject_id and a.student_i?"
//				,new Object[] {studentId},(rs,rowNum) -> new ResultResponse(
//						rs.getString("subject_name"),
//                      rs.getInt("evaluator_marks"),
//                      rs.getBoolean(("evaluation_flag")
//						)));
	}

	
 
}


