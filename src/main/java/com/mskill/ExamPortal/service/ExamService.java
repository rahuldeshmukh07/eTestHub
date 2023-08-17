/**
 * 
 */
package com.mskill.ExamPortal.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mskill.ExamPortal.JDBCRepository.ExamJDBCRepository;
import com.mskill.ExamPortal.JDBCRepository.StudentJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;

/**
 * @author 1838177
 *
 */
@Service
@Transactional
public class ExamService {

	 @Autowired
	 private ExamJDBCRepository examJDBC;
	 
	
	public List<QuestionPaperResponse> getQuestionPaper(long student_id) throws ResourceNotFoundException{
		 
		 List<QuestionPaperResponse> paper = examJDBC.findQuestionPaperByID(student_id);
		 Date date = new Date();
		 long SystemHour = date.getHours();
		 long SystemMinutes = date.getMinutes();
		 long LocalMinutes = (SystemHour*60) + SystemMinutes;
//		 System.out.println(paper);
		 for(int i=0;i<paper.size();i++) {
			 int x = date.compareTo(paper.get(i).getDate());
			 String dbDate = paper.get(i).getDate().toString();
			 long dbStartHour = paper.get(i).getStart_time().getHours();
			 long dbStartMinute = paper.get(i).getStart_time().getMinutes();	 
			 long dbStartLocalMinutes = (dbStartHour*60) + dbStartMinute;
			 long dbEndHour = paper.get(i).getEnd_time().getHours();
			 long dbEndMinute = paper.get(i).getEnd_time().getMinutes();	 
			 long dbEndLocalMinutes = (dbEndHour*60) + dbEndMinute;
//			 System.out.println(x);
			if(x>0) {
				if(dbStartLocalMinutes >= LocalMinutes)
					paper.clear();
				 else {
					 if(dbEndLocalMinutes >= LocalMinutes)
						return paper;
					 else
						paper.clear();
				 }
			}
			else
				paper.clear();
				 
			
		 }
		 
		 return paper;
	 }
	
	 public AnswerResponse getAnswer(Answer answer) throws ResourceNotFoundException{
		 List<AnswerResponse> ans = examJDBC.findAnswerById((long) answer.getStudent_id(),(long) answer.getQp_id());
		 if(ans.isEmpty())
			 return new AnswerResponse();
		 return ans.get(0);
	 }
	
	 public Long createAnswer(Answer answer) {
		 String result =  examJDBC.insert(answer);
		 System.out.println(result);
		 System.out.println(result.length());
		 if(result.isEmpty())
			 return (long) 0;
		 return (long) 1;
	 }
	 
	 public int updateAnswer(Answer answer, long answer_id) throws ResourceNotFoundException {
     return examJDBC.update(answer, answer_id);
	 }

	 public Long getMinutes(Long subject_id) {
		 
		 TimeTableResponse time = examJDBC.findOneQuestionPaperTime(subject_id);
		 
		 long dbTimeHour = Math.abs(time.getEnd_time().getHours() - time.getStartTime().getHours());
		 long dbTimeMinute = time.getEnd_time().getMinutes() - time.getStartTime().getMinutes();
		 long paperTime;
		 if(dbTimeMinute<0)
			 paperTime = (dbTimeHour*60) - Math.abs(dbTimeMinute);
		 else
			 paperTime = (dbTimeHour*60) + dbTimeMinute;
//		 System.out.println(paperTime);
		 Date date = new Date();
		 int x = date.compareTo(time.getStartDate());
		 long systemHour = date.getHours();
		 long systemMinute = date.getMinutes();
		 long hourLeft = time.getEnd_time().getHours() - systemHour;
		 long minuteLeft = time.getEnd_time().getMinutes() - systemMinute;
		 long totalTimeLeft = 0;
		 String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
//		 System.out.println(x);
//		 System.out.println(modifiedDate.equals(time.getStartDate().toString()));
		 if(x>0 && modifiedDate.equals(time.getStartDate().toString())) {
		 if(minuteLeft<0)
			 totalTimeLeft = (hourLeft*60) - Math.abs(minuteLeft);
		 else
			 totalTimeLeft = (hourLeft*60) + minuteLeft;
		 
		 if(totalTimeLeft < 0)
			 totalTimeLeft = 0;
		 }
		return totalTimeLeft;
	 }
	 
}
