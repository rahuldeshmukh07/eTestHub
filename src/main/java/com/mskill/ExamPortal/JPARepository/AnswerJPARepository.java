/**
 * 
 */
package com.mskill.ExamPortal.JPARepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mskill.ExamPortal.model.Answer;

@Configuration
@EnableTransactionManagement
public interface AnswerJPARepository extends JpaRepository<Answer, Integer> {

}
