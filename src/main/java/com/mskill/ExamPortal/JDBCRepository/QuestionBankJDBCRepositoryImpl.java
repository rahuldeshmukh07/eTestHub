package com.mskill.ExamPortal.JDBCRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mskill.ExamPortal.model.QuestionPaper;
import com.mskill.ExamPortal.response.QuestionBankResponse;

@Repository
public class QuestionBankJDBCRepositoryImpl implements QuestionBankJDBCRepository {

	ArrayList<QuestionBankResponse> generatedQlist= new ArrayList<QuestionBankResponse>();
	ArrayList<QuestionBankResponse> QList;
	int noofques=0;
	long modID=0;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
    public List findAllQBankView() {
        return jdbcTemplate.query(
                "SELECT q.qb_id, q.faculty_id, q.subject_id, q.exam_id, q.questions\r\n" + 
                "	FROM public.question_bank q;",
                (rs, rowNum) ->
                        new QuestionBankResponse(
                        		rs.getInt("qb_id"),
                        		rs.getInt("faculty_id"),
                        		rs.getInt("subject_id"),
                        		rs.getInt("exam_id"),
                        		rs.getString("questions")
                        )
        );
    }
  
    @Override
    public Optional<QuestionBankResponse> findQBankViewById(Long id) {
        return jdbcTemplate.queryForObject(
        		"SELECT q.qb_id, q.faculty_id, q.subject_id, q.exam_id, q.questions\r\n" + 
                        "	FROM public.question_bank q where q.qb_id=?;",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new QuestionBankResponse(
                        		rs.getInt("qb_id"),
                        		rs.getInt("faculty_id"),
                        		rs.getInt("subject_id"),
                        		rs.getInt("exam_id"),
                        		rs.getString("questions")
                        ))
        );
 }
    
    @Override
    public List<QuestionBankResponse> generateQPBySubId(Long id, int noofq,long modid){
    	noofques= noofq;
    	modID=modid;
    	jdbcTemplate.query(
                "SELECT q.subject_id, q.exam_id, q.questions FROM public.question_bank q where q.subject_id=? ORDER BY random() \r\n" + 
                "LIMIT "+noofq+"",
                new Object[]{id},
                (rs, rowNum) ->
                generatedQlist.add(new QuestionBankResponse(
                        		rs.getInt("subject_id"),
                        		rs.getInt("exam_id"),
                        		rs.getString("questions")
                        ))
                        
        );
    	QList = (ArrayList<QuestionBankResponse>) generatedQlist.stream() 
                .distinct() 
                .collect(Collectors.toList());
    	generatedQlist.clear();
    	
    	return QList;
    }
    
    @Override
	public Long[] insertQuestion(QuestionPaper questionpaper) {
    	//System.out.println("Moderatorid:"+modID);
    	Long[] toret=null;
    	for(int i=0;i<QList.size();i++){
    		toret=new Long[QList.size()];
    	String sql = "INSERT INTO public.question_paper(qp_id, subject_id, exam_id, marks, created_by, start_time, start_date, question, end_time, noofquestions) VALUES (nextval('qp_sequence'), ?,?, ?, ?, ?, ?, ?,?, ?) RETURNING qp_id";
    	
		toret=jdbcTemplate.queryForObject(sql,new Object[] {
				QList.get(i).getSubject_id(),
				QList.get(i).getExam_id(),
				questionpaper.getMarks(),				
				modID,
				questionpaper.getStart_time(),
				questionpaper.getStart_date(),
				QList.get(i).getQuestions().toString(),
				questionpaper.getEnd_time(),
				noofques
		}, Long[].class);
    }
		return toret;
    	
	}


	/*@Override
	public Optional<QuestionBankResponse> findQBankViewById(Long qb_id) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
