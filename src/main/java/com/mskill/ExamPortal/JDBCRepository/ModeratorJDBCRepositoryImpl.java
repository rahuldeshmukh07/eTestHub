package com.mskill.ExamPortal.JDBCRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mskill.ExamPortal.response.ModeratorResponse;
import com.mskill.ExamPortal.response.RandomExaminerResponse;
import com.mskill.ExamPortal.response.FacultyResponse;

@Repository
public class ModeratorJDBCRepositoryImpl implements ModeratorJDBCRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
    public List findAllModView() {
        return jdbcTemplate.query(
                "SELECT s.moderator_id, m.salutation, m.first_name, m.last_name, m.moderator_email, m.mobile, m.college_id, s.subject_id, sub.subject_name FROM public.moderator m INNER JOIN public.mod_sub s ON m.moderator_id=s.moderator_id INNER JOIN public.subject sub ON s.subject_id=sub.subject_id;",
                (rs, rowNum) ->
                        new ModeratorResponse(
                        		rs.getInt("moderator_id"),
                        		rs.getString("salutation"),
                        		rs.getString("first_name"),
                        		rs.getString("last_name"),
                        		rs.getString("moderator_email"),
                        		rs.getLong("mobile"),
                        		rs.getInt("college_id"),
                        		rs.getInt("subject_id"),
                        		rs.getString("subject_name")
                        )
        );
    }
  
    @Override
    public Optional<ModeratorResponse> findModViewById(Long id) {
    	return jdbcTemplate.queryForObject(
        		"SELECT s.moderator_id, m.salutation, m.first_name, m.last_name, m.moderator_email, m.mobile, m.college_id, s.subject_id, sub.subject_name FROM public.moderator m INNER JOIN public.mod_sub s ON m.moderator_id=s.moderator_id INNER JOIN public.subject sub ON s.subject_id=sub.subject_id WHERE m.moderator_id=?;",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new ModeratorResponse(
                        		rs.getInt("moderator_id"),
                        		rs.getString("salutation"),
                        		rs.getString("first_name"),
                        		rs.getString("last_name"),
                        		rs.getString("moderator_email"),
                        		rs.getLong("mobile"),
                        		rs.getInt("college_id"),
                        		rs.getInt("subject_id"),
                        		rs.getString("subject_name")
                        ))
        );
 }
    
    @Override
    public List findAllExaminer(){
        return jdbcTemplate.query(
                "SELECT s.faculty_id, f.salutation, f.first_name, f.last_name, f.faculty_email, f.mobile, f.college_id, s.subject_id, sub.subject_name FROM public.faculty f INNER JOIN public.fac_sub s ON f.faculty_id=s.faculty_id INNER JOIN public.subject sub ON s.subject_id=sub.subject_id INNER JOIN public.mod_sub m ON m.subject_id=s.subject_id AND m.subject_id=sub.subject_id;",
                	
                (rs, rowNum) ->
                        new FacultyResponse(
                        		rs.getInt("faculty_id"),
                        		rs.getString("salutation"),
                        		rs.getString("first_name"),
                        		rs.getString("last_name"),
                        		rs.getString("faculty_email"),
                        		rs.getLong("mobile"),
                        		rs.getInt("college_id"),
                        		rs.getInt("subject_id"),
                        		rs.getString("subject_name")
                        )
        );
    }

   /* @Override
    public Optional<FacultyResponse> findExaminerBySubId(Long id) {
    	return jdbcTemplate.queryForObject(
        		"SELECT s.faculty_id, f.salutation, f.first_name, f.last_name, f.faculty_email, f.mobile, f.college_id, s.subject_id, sub.subject_name FROM public.faculty f INNER JOIN public.fac_sub s ON f.faculty_id=s.faculty_id INNER JOIN public.subject sub ON s.subject_id=sub.subject_id INNER JOIN public.mod_sub m ON m.subject_id=s.subject_id AND m.subject_id=sub.subject_id WHERE s.subject_id=?;",
                new Object[]{id},
                (rs, rowNum) ->
        		Optional.of(new FacultyResponse(
                        		rs.getInt("faculty_id"),
                        		rs.getString("salutation"),
                        		rs.getString("first_name"),
                        		rs.getString("last_name"),
                        		rs.getString("faculty_email"),
                        		rs.getLong("mobile"),
                        		rs.getInt("college_id"),
                        		rs.getInt("subject_id"),
                        		rs.getString("subject_name")
                        ))
        );
    	
 }*/
    @Override
    public List findExaminerBySubId(Long id) {
    	return jdbcTemplate.query(
        		"SELECT s.faculty_id, f.salutation, f.first_name, f.last_name, f.faculty_email, f.mobile, f.college_id, s.subject_id, sub.subject_name FROM public.faculty f INNER JOIN public.fac_sub s ON f.faculty_id=s.faculty_id INNER JOIN public.subject sub ON s.subject_id=sub.subject_id INNER JOIN public.mod_sub m ON m.subject_id=s.subject_id AND m.subject_id=sub.subject_id WHERE s.subject_id=?;",
                new Object[]{id},
                (rs, rowNum) ->
        		new FacultyResponse(
                        		rs.getInt("faculty_id"),
                        		rs.getString("salutation"),
                        		rs.getString("first_name"),
                        		rs.getString("last_name"),
                        		rs.getString("faculty_email"),
                        		rs.getLong("mobile"),
                        		rs.getInt("college_id"),
                        		rs.getInt("subject_id"),
                        		rs.getString("subject_name")
                        )
        );
    	
 }
    
    @Override
	public List GenerateRandomExaminerId() {
		
		//Returns all Evaluator id in Random order
		return jdbcTemplate.query(
                "SELECT f.faculty_id FROM public.faculty f ,public.fac_sub s\r\n" +
                 "where f.faculty_id=s.faculty_id ORDER BY random();",
                (rs, rowNum) ->
                        new RandomExaminerResponse(
                        		rs.getInt("faculty_id")	
                        )
        );
	}
	
	@Override
	public List<Integer> fetchAllFacultyIdView(Long id) {
		
		return jdbcTemplate.query(
                "SELECT f.faculty_id FROM public.faculty f ,public.fac_sub s where f.faculty_id=s.faculty_id AND s.subject_id=? ORDER BY random();",
                 new Object[]{id},
                 (rs, rowNum) ->
                        new Integer(
                        		rs.getInt("faculty_id")	
                        )
        );
		
	}

	@Override
	public List<Integer> fetchAllAnswerId() {
	
		return jdbcTemplate.query(
                "SELECT p.answer_id FROM public.answer p WHERE p.faculty_id IS NULL ;",
                       (rs, rowNum) ->
                               new Integer(
                               		rs.getInt("answer_id")	
                               )
               );
	}
	// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	 
	@Override
	public int updateFacultyAnswer(int evaluatorId, int answerId ) {
		String updateQuery = "update public.answer set faculty_id = ? where answer_id = ?";
		return jdbcTemplate.update(updateQuery,evaluatorId,answerId);
	}
	
	
	/*	@Override
    public Optional<ModeratorResponse> getModeratorLoginDetailsById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from public.moderator u where u.moderator_id = ?",
                new Object[]{id},
                (bn, rowNum) ->
                        Optional.of(new ModeratorResponse(
                        		bn.getInt("moderator_id"),
                                bn.getString("password")
                        ))
        );
    }
*/

       
}

