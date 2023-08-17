package com.mskill.ExamPortal.JDBCRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.response.AdminResponse;


@Repository
public class AdminJDBCRepositoryImpl implements AdminJDBCRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
 
    
/*    @Override
    public List findAllAdminView() {
        return jdbcTemplate.query(
                "select u.admin_id, u.salutation, u.first_name, u.last_name, u.admin_email,  v.college_name, w.branch_name, u.mobile, u.academic_year from public.student u, public.college v, public.branch w where u.college_id=v.college_id and u.branch_id=w.branch_id",
                (rs, rowNum) ->
                        new AdminResponse(
                        		rs.getLong("admin_id"),
                                rs.getString("salutation"),
                                rs.getString("first_name"), 
                                rs.getString("last_name"),
                                rs.getString("admin_email"),
                                rs.getString("college_name"),
                                rs.getString("branch_name"),
                                rs.getLong("mobile"),
                                rs.getInt("academic_year")
                        )
        );
    }*/
    
    @Override
    public Optional<AdminResponse> findAdminViewById(int id) {
        return jdbcTemplate.queryForObject(
        		"select u.admin_id, u.salutation, u.first_name, u.last_name, u.admin_email,  v.college_name, u.mobile from public.admin u, public.college v, public.branch w where u.college_id=v.college_id and u.admin_id = ? LIMIT 1",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new AdminResponse(
                        		rs.getLong("admin_id"),
                                rs.getString("salutation"),
                                rs.getString("first_name"), 
                                rs.getString("last_name"),
                                rs.getString("admin_email"),
                                rs.getString("college_name"),
                                rs.getLong("mobile")
                              
                               
                        ))
        );
    }



	@Override
	public List findAllAdminView() {
		// TODO Auto-generated method stub
		return null;
	}
 
 
	
}
