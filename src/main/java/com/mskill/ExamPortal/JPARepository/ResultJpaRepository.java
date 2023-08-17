package com.mskill.ExamPortal.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mskill.ExamPortal.model.Answer;
import com.mskill.ExamPortal.model.Moderator;

public interface ResultJpaRepository extends JpaRepository<Answer, Long>{

}
