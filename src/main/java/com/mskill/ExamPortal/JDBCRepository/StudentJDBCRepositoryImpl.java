package com.mskill.ExamPortal.JDBCRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;

@Repository
public class StudentJDBCRepositoryImpl implements StudentJDBCRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
 
    
    @Override
    public List findAllStudentView() {
        return jdbcTemplate.query(
                "select u.student_id, u.salutation, u.first_name, u.last_name, u.student_email,  v.college_name, w.branch_name, u.mobile, u.academic_year from public.student u, public.college v, public.branch w where u.college_id=v.college_id and u.branch_id=w.branch_id",
                (rs, rowNum) ->
                        new StudentResponse(
                        		rs.getLong("student_id"),
                                rs.getString("salutation"),
                                rs.getString("first_name"), 
                                rs.getString("last_name"),
                                rs.getString("student_email"),
                                rs.getString("college_name"),
                                rs.getString("branch_name"),
                                rs.getLong("mobile"),
                                rs.getInt("academic_year")
                        )
        );
    }
    
    @Override
    public Optional<StudentResponse> findStudentViewById(Long id) {
        return jdbcTemplate.queryForObject(
                "select u.student_id, u.salutation, u.first_name, u.last_name, u.student_email,  v.college_name, w.branch_name, u.mobile, u.academic_year from public.student u, public.college v, public.branch w where u.college_id=v.college_id and u.branch_id=w.branch_id and u.student_id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new StudentResponse(
                        		rs.getLong("student_id"),
                                rs.getString("salutation"),
                                rs.getString("first_name"), 
                                rs.getString("last_name"),
                                rs.getString("student_email"),
                                rs.getString("college_name"),
                                rs.getString("branch_name"),
                                rs.getLong("mobile"),
                                rs.getInt("academic_year")
                        ))
        );
    }

	@Override
	public List<TimeTableResponse> findStudentTimetableViewById(Long id) {
		String query = "SELECT DISTINCT v.exam_name,w.subject_name, u.noofquestions, u.subject_id,u.start_time, u.end_time, u.start_date, u.marks FROM public.question_paper u, public.exam v, public.subject w, public.branch z, public.student x , public.sub_branch y where u.exam_id=v.exam_id and u.subject_id=w.subject_id and w.subject_id = y.subject_id and y.branch_id = z.branch_id and x.branch_id=z.branch_id and x.student_id=?";
		return jdbcTemplate.query(
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
 
	@Override
	public List<String> checkifPaperDone(Long id, int sub_id) {
		String query = "SELECT a.answer_id FROM public.answer a, public.question_paper u, public.student x where x.student_id = a.student_id and u.qp_id = a.qp_id and u.subject_id=? and  x.student_id=? LIMIT 1;";
		List<String> val = jdbcTemplate.query(
				query,
                new Object[]{sub_id, id},
                (rs, rowNum) ->
                       rs.getString("answer_id")
        );
		
		return val;
	}
 
	
 
//	@Override
//	public Optional<QuestionPaperResponse> findQuestionPaperByID(Long id) {
//		return jdbcTemplate.queryForObject(
//                "SELECT DISTINCT ON (qp_id) v.exam_name,w.subject_name, q.subject_id,q.start_time,q.questions, q.end_time, q.start_date, q.marks, q.passing_marks\r\n" + 
//                "FROM public.question_paper q, public.exam v, public.subject w, public.student x , public.sub_branch y where \r\n" + 
//                "q.exam_id=v.exam_id and q.subject_id=w.subject_id and x.branch_id = y.branch_id and q.qp_id = ?",
//                new Object[]{id},
//                (rs, rowNum) ->
//                        Optional.of(new QuestionPaperResponse(
//                        		rs.getString("exam_name"),
//                        		rs.getString("subject_name"),
//                        		rs.getInt("qp_id"),
//                        		rs.getTime("start_time"),
//                        		rs.getObject("questions"),
//                        		rs.getTime("end_time"),
//                        		rs.getDate("start_date"),
//                        		rs.getInt("marks"),
//                        		rs.getInt("passing_marks")
//                        		
//                        		                        ))
//        );
//	}
//	
	
}
