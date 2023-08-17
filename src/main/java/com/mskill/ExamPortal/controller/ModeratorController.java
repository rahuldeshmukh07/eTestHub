/**
 * 
 */
package com.mskill.ExamPortal.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mskill.ExamPortal.JDBCRepository.ModeratorJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.response.ModeratorResponse;
import com.mskill.ExamPortal.service.ModeratorService;
import com.mskill.ExamPortal.response.FacultyResponse;

@RestController
@RequestMapping("/api/v1")
public class ModeratorController extends HttpServlet{

	@Autowired
	ModeratorService moderatorService;
	@Autowired
	ModeratorJDBCRepository moderatorJDBC;
	HttpStatus status;
	public static long subid=0;
	public static long modid=0;
	@GetMapping("/moderator")
	 public List<ModeratorResponse> findAllModView(){
	        return moderatorService.findAllModView();
	    }
	
	public static long getModid() {
		return modid;
	}

	public static void setModid(long modid) {
		ModeratorController.modid = modid;
	}

	@GetMapping("/randomExaminer")
	 public List GenerateRandomExaminerId(){
	        return moderatorService.GenerateRandomExaminerId();
	    }
	
	@GetMapping("/examiner")
	 public List<ModeratorResponse> findAllExaminer(){
	        return moderatorService.findAllExaminer();
	    }
	 
	@PostMapping("/assignRandomFaculty")
	public List<Integer> setFacultyToAnswer(){
		return moderatorService.setFacultyToAnswer(subid);
	}
	 	
	   /* @GetMapping("/moderator/{id}")
	    public ResponseEntity<ModeratorResponse> getModById(@PathVariable(value = "id") Integer moderator_id) throws ResourceNotFoundException
	    {
	        return ResponseEntity.ok().body(moderatorService.getModById(moderator_id));
	    }*/
	
	@GetMapping("/moderator/")
    public ResponseEntity<ModeratorResponse> findModViewById(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException {
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Integer test_id = Integer.parseInt(sessionId);
		ModeratorResponse temp = moderatorService.getModById(test_id);
		subid=temp.getSubject_id();
		modid=test_id;
		//if(temp!=null)   
	    //	 return ResponseEntity.ok().body(moderatorService.getModById(test_id));
	     //return ResponseEntity.status(status.BAD_REQUEST).body(null);
		return ResponseEntity.ok().body(moderatorService.getModById(test_id));
		/*try {	ModeratorResponse moderator = moderatorJDBC.findModViewById(moderator_id)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + moderator_id));
	 return ResponseEntity.ok().body(moderator);
	 }catch (EmptyResultDataAccessException e) {
		 return null;
		}*/
    }
	/*
	@GetMapping("/moderator/")
    public ResponseEntity<Optional<ModeratorResponse>> findModViewById(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException {
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Long test_id = Long.parseLong(sessionId);
		Optional<ModeratorResponse> temp =  moderatorJDBC.findModViewById(test_id);
		if(temp!=null)   
	    	 return ResponseEntity.ok().body(moderatorJDBC.findModViewById(test_id));
	     return ResponseEntity.status(status.BAD_REQUEST).body(null);
		/*try {	ModeratorResponse moderator = moderatorJDBC.findModViewById(moderator_id)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + moderator_id));
	 return ResponseEntity.ok().body(moderator);
	 }catch (EmptyResultDataAccessException e) {
		 return null;
		}
    }*/
	
	/*@GetMapping("/examiner/")
    public ResponseEntity<FacultyResponse> findExaminerBySubId(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException {
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Integer test_id = Integer.parseInt(sessionId);
		//FacultyResponse temp = moderatorService.getExaminerById(test_id);
		return ResponseEntity.ok().body(moderatorService.getExaminerById(test_id));
    }*/
	

	@GetMapping("/examinerbyId/")
    public List findExaminerBySubId(HttpServletRequest request, HttpServletResponse response)
        throws ResourceNotFoundException {
		HttpSession session = request.getSession(false);
	String sessionId = (String) session.getAttribute("username");
	Integer test_id = Integer.parseInt(sessionId);
	ModeratorResponse temp = moderatorService.getModById(test_id);
			//@SuppressWarnings("rawtypes")
			List moderator = ((List) moderatorJDBC.findExaminerBySubId((long) temp.getSubject_id()));
			return moderator;
    }
	
	    @PostMapping("/mod")
	    public Moderator createMod( @RequestBody Moderator mod) {
	       return moderatorService.createModerator(mod);
	    }

}
