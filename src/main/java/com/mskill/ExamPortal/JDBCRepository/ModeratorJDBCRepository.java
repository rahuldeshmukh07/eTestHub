package com.mskill.ExamPortal.JDBCRepository;

import java.util.List;
import java.util.Optional;

import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.response.FacultyResponse;
import com.mskill.ExamPortal.response.ModeratorResponse;

public interface ModeratorJDBCRepository {

	List findAllModView();
    Optional<ModeratorResponse> findModViewById(Long moderator_id);
    List findAllExaminer();
    //Optional<FacultyResponse> findExaminerBySubId(Long subject_id);
    List findExaminerBySubId(Long id);
/*	Optional<ModeratorResponse> getModeratorLoginDetailsById(int moderator_id);	
*/
	List<Integer> fetchAllFacultyIdView(Long subject_id);
	List GenerateRandomExaminerId();
	List<Integer> fetchAllAnswerId();
	int updateFacultyAnswer(int evaluatorId, int answerId);
}