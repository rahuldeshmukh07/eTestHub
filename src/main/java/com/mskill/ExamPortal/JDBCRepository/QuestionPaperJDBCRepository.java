package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.response.QuestionPaperResponse;

public interface QuestionPaperJDBCRepository {

	List findAllQPView();
    Optional<QuestionPaperResponse> findQPViewById(Long qp_id);
}
