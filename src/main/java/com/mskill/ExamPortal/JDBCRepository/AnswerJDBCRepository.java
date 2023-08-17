package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.response.AnswerResponse;
import com.mskill.ExamPortal.response.ModeratorAnswerResponse;

public interface AnswerJDBCRepository {

	List findAllAnswerView();
    Optional<ModeratorAnswerResponse> findAnswerViewById(Long answer_id);
}
