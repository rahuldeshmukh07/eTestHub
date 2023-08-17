/**
 * 
 */
package com.mskill.ExamPortal.JPARepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mskill.ExamPortal.model.Student;

/**
 * @author 1838177
 *
 */
@Configuration
@EnableTransactionManagement
public interface StudentJPARepository extends JpaRepository<Student, Long>{

}
