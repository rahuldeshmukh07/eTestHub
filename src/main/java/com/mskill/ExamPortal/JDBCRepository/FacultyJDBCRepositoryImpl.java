package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.response.EvaluationResponse;
import com.mskill.ExamPortal.response.FacultyResponse;
import com.mskill.ExamPortal.response.SelectBranchResponse;
import com.mskill.ExamPortal.response.SelectExamResponse;
import com.mskill.ExamPortal.response.SelectSubjectResponse;
import com.mskill.ExamPortal.response.TaskBarResponse;


@Repository
public class FacultyJDBCRepositoryImpl implements FacultyJDBCRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;
 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List getAllFacultyView() {
        return jdbcTemplate.query(
                "select * from public.faculty u, public.college v where u.college_id=v.college_id",
                (bn, rowNum) ->
                        new FacultyResponse(
                        		bn.getInt("faculty_id"),
                                bn.getString("first_name"), 
                                bn.getString("last_name"),
                                bn.getString("faculty_email"),
                                bn.getLong("mobile"),
                                bn.getString("college_name")
                          
                        )
        );
    }
	
	@Override
    public Optional<FacultyResponse> getFacultyViewById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from public.faculty u, public.college v where u.college_id=v.college_id and u.faculty_id = ?",
                new Object[]{id},
                (bn, rowNum) ->
                        Optional.of(new FacultyResponse(
                        		bn.getInt("faculty_id"),
                                bn.getString("first_name"), 
                                bn.getString("last_name"),
                                bn.getString("faculty_email"),
                                bn.getLong("mobile"),
                                bn.getString("college_name")
 
  
                        ))
        );
    }

	
	@Override
	public List<SelectExamResponse> getExamNameView() {
		return jdbcTemplate.query(
                "SELECT exam_id,exam_name FROM exam",
                new Object[]{},
                (en, rowNum) ->
                       new SelectExamResponse(
                        		en.getString("exam_name"),
                        		en.getInt("exam_id")
                        )
        );
	}
	
	
	@Override
	public List<SelectSubjectResponse> getSubjectNameView(int id) {
		return jdbcTemplate.query(
				"SELECT distinct on(u.subject_id) * FROM public.subject u ,public.fac_sub v,public.faculty w where w.faculty_id=v.faculty_id and u.subject_id=v.subject_id and v.faculty_id=?",
                new Object[]{id},
                (sn, rowNum) ->
                       new SelectSubjectResponse(
                        		sn.getString("subject_name"),
                        		sn.getInt("subject_id")
                        )
        );
	}
	
	public List<TaskBarResponse> getTaskBarViewById(int id) {
		return jdbcTemplate.query(
				"SELECT u.subject_name,v.created_on ,v.answer_id FROM public.subject u,public.answer v,public.question_paper w,public.faculty x WHERE v.qp_id=w.qp_id and u.subject_id=w.subject_id and x.faculty_id=v.faculty_id and v.evaluation_flag='false' and x.faculty_id=?",
					new Object[] {id},
					(tb,rowNum) ->
							new TaskBarResponse (
									tb.getString("subject_name"),
									tb.getDate("created_on"),
									tb.getInt("answer_id")
									
									
						)  
				
					
			);				
							
	}

	@Override
	public List<EvaluationResponse> getEvaluateAnswerPage(int answer_id) {
		 return jdbcTemplate.query(
	                "SELECT DISTINCT ON (q.qp_id) * FROM public.question_paper q, public.answer a ,public.faculty f, public.fac_sub e where f.faculty_id=e.faculty_id and e.subject_id=q.subject_id and a.qp_id=q.qp_id AND a.answer_id=?",
	                new Object[] {answer_id},
	                (ep, rowNum) ->
	                        new EvaluationResponse(
	                        		ep.getString("question"),
	                        		ep.getString("answer"),
	                        		ep.getInt("answer_id")
	                        	
	                        	)
	        );
	    }
	
	@Override
	public int updateMarks(int answer_id, int marks) {
		return jdbcTemplate.update("UPDATE public.answer SET evaluator_marks = ? , evaluation_flag = 'true' WHERE evaluation_flag='false' and answer_id = ?", marks,answer_id );
	}
	
	

/*	@Override
    public Optional<FacultyResponse> getFacultyLoginDetailsById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from public.faculty u where u.faculty_id = ?",
                new Object[]{id},
                (bn, rowNum) ->
                        Optional.of(new FacultyResponse(
                        		bn.getInt("faculty_id"),
                                bn.getString("password")
                        ))
        );
    }
*/
	
}