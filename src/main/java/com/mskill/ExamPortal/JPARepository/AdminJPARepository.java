/**
 * 
 */
package com.mskill.ExamPortal.JPARepository;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mskill.ExamPortal.model.Admin;




/**
 * @author 1838177
 *
 */
@Configuration
@EnableTransactionManagement
public interface AdminJPARepository extends JpaRepository<Admin, Integer>{

}
