package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.model.QuestionPaper;
import com.mskill.ExamPortal.response.QuestionBankResponse;

public interface QuestionBankJDBCRepository {

	List findAllQBankView();
    Optional<QuestionBankResponse> findQBankViewById(Long qb_id);
    //Object generateQPaper();
	List<QuestionBankResponse> generateQPBySubId(Long id, int noofq,long subject_id);
	Long[] insertQuestion(QuestionPaper questionpaper);
	//Long insertQuestion(QuestionPaper questionpaper, ArrayList<QuestionBankResponse> qlis);
}
