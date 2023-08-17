/**
 * 
 */
package com.mskill.ExamPortal.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.JPARepository.FacultyJPARepository;
import com.mskill.ExamPortal.JPARepository.StudentJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Faculty;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.model.Student;

/*import com.mskill.ExamPortal.model.UserBean;
*/


/**
 * @author 1838177
 *
 */



@RestController
@Transactional
@RequestMapping("/api/v1")
public class TestController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @throws ParseException 
	 * 
	 */
	
/*	@Autowired
	UserBean userBean;
*/
	@Autowired
	private StudentJPARepository studentJPA;
	private FacultyJPARepository facultyJPA;
	
    @GetMapping("/test")
	public String sessionEntries(/*HttpSession session,*/ HttpServletRequest request, HttpServletResponse response) {
/*    	System.out.println(userBean.getUsername());
    	String uname= userBean.getUsername();
    	System.out.println(userBean);
        return uname;
*/
    	HttpSession session = request.getSession(false);
    	System.out.println(session);
		String uname = (String) session.getAttribute("username");
		System.out.println("Welcome "+uname);
    	return uname;
    }
        
    @GetMapping("/passwordgenerator")
	public String passwordgenerator() {
    	String str = "Maruti@9876543210";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password1 = passwordEncoder.encode(str);
		System.out.println(password1);
    	return password1;
    }
    
    
/*    @PostMapping("/passwordvalidate")
	public String passwordvalidate(@RequestBody String password) {
    	String val = "dummy";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password1 = passwordEncoder.encode(val);
		System.out.println(password1);
		if(passwordEncoder.matches(val, password1)) {
			System.out.println("True");			
		}
		else {
			System.out.println("False");			
		}
    	return password1;
    }
*/
}
