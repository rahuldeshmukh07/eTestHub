/**
 * 
 */
package com.mskill.ExamPortal.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mskill.ExamPortal.model.FacultyQuestionBank;
import com.mskill.ExamPortal.model.QuestionBank;

/**
 * @author 1838177
 *
 */
public interface Questionrepository extends JpaRepository<FacultyQuestionBank,Integer>{


}
