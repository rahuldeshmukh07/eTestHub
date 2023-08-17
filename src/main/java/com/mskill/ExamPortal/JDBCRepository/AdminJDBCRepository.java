package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.response.AdminResponse;

 
public interface AdminJDBCRepository{

    List findAllAdminView();
    Optional<AdminResponse> findAdminViewById(int id);
    
   
    
//    Optional<QuestionPaperResponse> findQuestionPaperByID(Long id);
}