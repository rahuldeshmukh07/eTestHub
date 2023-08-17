package com.mskill.ExamPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mskill.ExamPortal.JPARepository.AdminRepository;
import com.mskill.ExamPortal.JPARepository.FacultyJPARepository;
import com.mskill.ExamPortal.JPARepository.ModeratorJPARepository;
import com.mskill.ExamPortal.JPARepository.StudentJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Admin;
import com.mskill.ExamPortal.model.Faculty;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.model.Student;

@Service
@Transactional
public class LoginService {

	@Autowired
	FacultyService facultyService;
	ModeratorService moderatorService;
	
/*	@Autowired
	BCryptPasswordEncoder passwordEncoder;
*/
	@Autowired
	private StudentJPARepository studentJPA;

	@Autowired
	private ModeratorJPARepository moderatorJPA;

	@Autowired
	private FacultyJPARepository facultyJPA;

	@Autowired
	private AdminRepository adminJPA;
	
	public boolean validateStudent(String username, String password) throws ResourceNotFoundException {
		Student student = studentJPA.findById( Long.parseLong(username)).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, student.getPassword())) {
			System.out.println("Successfull Login by Student");
			return true;
		}
		else {
			return false;
		}
	}

	public boolean validateModerator(String username, String password) throws ResourceNotFoundException {
		Moderator moderator = moderatorJPA.findById( Integer.parseInt(username)).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, moderator.getPassword())) {
			System.out.println("Successfull Login by Moderator");
			return true;
		}
		else {
			return false;
		}
	}

	public boolean validateFaculty(String username, String password) throws ResourceNotFoundException {
		Faculty faculty = facultyJPA.findById( Integer.parseInt(username)).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, faculty.getPassword())) {
			System.out.println("Successfull Login by Faculty");
			return true;
		}
		else {
			return false;
		}
	}


	public boolean validateAdmin(String username, String password) throws ResourceNotFoundException {
	Admin admin = adminJPA.findById(Integer.parseInt(username)).orElseThrow(() -> new ResourceNotFoundException("Password does not match for this id :: " + Long.parseLong(username)));
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	if (passwordEncoder.matches(password, admin.getPassword())) {
		System.out.println("Successfull Login by Admin");
		return true;
	}
	else {
		return false;
	}
}
 

/*	
 * 
 * 	public boolean validateFaculty(String username, String password) throws ResourceNotFoundException {
		FacultyResponse faculty = facultyService.getFacultyLoginDetailsById(Integer.parseInt(username));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, faculty.getPassword())) {
			System.out.println("Successfull Login by Faculty");
			return true;
		}
		else {
			return false;
		}
	}
public boolean validateModerator(String username, String password) throws ResourceNotFoundException {
		ModeratorResponse moderator = moderatorService.getModeratorLoginDetailsById(Integer.parseInt(username));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, moderator.getPassword())) {
			System.out.println("Successfull Login by Moderator");
			return true;
		}
		else {
			return false;
		}
	}
*/


}
