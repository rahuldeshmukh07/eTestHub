package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.response.QuestionPaperResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.response.TimeTableResponse;
 
public interface StudentJDBCRepository{

    List findAllStudentView();
    Optional<StudentResponse> findStudentViewById(Long id);
    
    List<TimeTableResponse> findStudentTimetableViewById(Long id);
    
    List<String> checkifPaperDone(Long id, int sub_id);
    
//    Optional<QuestionPaperResponse> findQuestionPaperByID(Long id);
}