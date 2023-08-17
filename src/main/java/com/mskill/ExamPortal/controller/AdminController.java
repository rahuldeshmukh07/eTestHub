package com.mskill.ExamPortal.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mskill.ExamPortal.JPARepository.StudentJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.JPARepository.FacultyRepository;
import com.mskill.ExamPortal.JPARepository.ModeratorRepository;
import com.mskill.ExamPortal.model.Faculty;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.model.Student;
import com.mskill.ExamPortal.response.AdminImportResponse;
import com.mskill.ExamPortal.response.AdminResponse;
import com.mskill.ExamPortal.response.StudentResponse;
import com.mskill.ExamPortal.service.AdminService;

@Controller
@RequestMapping("/api/v1")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501884487563539597L;
	@Autowired
	AdminService fileService;

	@PostMapping("/uploadStudent")
	public ResponseEntity<AdminImportResponse> uploadFile1(@RequestParam("student") MultipartFile file) {
		String message = "";
		try {
			fileService.save1(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/")
					.path(file.getOriginalFilename()).toUriString();

			return ResponseEntity.status(HttpStatus.OK).body(new AdminImportResponse(message, fileDownloadUri));
		} catch (Exception e) {
			String msg = e.getMessage();
			message = "Could not upload the file: " + msg + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new AdminImportResponse(message, ""));
		}
	}

	@PostMapping("/uploadFaculty")
	public ResponseEntity<AdminImportResponse> uploadFile2(@RequestParam("faculty") MultipartFile file) {
		String message = "";
		try {
			fileService.save2(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/")
					.path(file.getOriginalFilename()).toUriString();

			return ResponseEntity.status(HttpStatus.OK).body(new AdminImportResponse(message, fileDownloadUri));
		} catch (Exception e) {
			String msg = e.getMessage();
			e.printStackTrace();
			message = "Could not upload the file: " + msg + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new AdminImportResponse(message, ""));
		}
	}

	@PostMapping("/uploadModerator")
	public ResponseEntity<AdminImportResponse> uploadFile3(@RequestParam("moderator") MultipartFile file) {
		String message = "";

		try {
			fileService.save(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/")
					.path(file.getOriginalFilename()).toUriString();

			return ResponseEntity.status(HttpStatus.OK).body(new AdminImportResponse(message, fileDownloadUri));
		} catch (Exception e) {
			String msg = e.getMessage();
			message = "Could not upload the file: " + msg + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new AdminImportResponse(message, ""));
		}
	}

	@GetMapping("/getStudent")
	public ResponseEntity<List<Student>> getAllStudent() {
		try {
			List<Student> student = fileService.getAllStudent();

			if (student.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getFaculty")
	public ResponseEntity<List<Faculty>> getAllFaculty() {
		try {
			List<Faculty> faculty = fileService.getAllFaculty();

			if (faculty.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(faculty, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getModerator")
	public ResponseEntity<List<Moderator>> getAllModerator() {
		try {
			List<Moderator> moderator = fileService.getAllModerator();

			if (moderator.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(moderator, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @GetMapping("/admin/")
    public ResponseEntity<AdminResponse> getStudentById( HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException
       {
    	HttpSession session = request.getSession(false);
    	String sessionId = (String) session.getAttribute("username");
    	Integer test_id = Integer.parseInt(sessionId);
    	return ResponseEntity.ok().body(fileService.getStudentById(test_id));
    	
       
    }
	
	@Autowired
	private ModeratorRepository moderatorRepository;
//	@GetMapping(path="/admin/moderators")
	
	@RequestMapping(value="/admin/moderator", method=RequestMethod.GET, produces= {"application/json"})
	public @ResponseBody Iterable<Moderator> getAllModerators(){
		return moderatorRepository.findAll();
	}
	
	@Autowired
	private FacultyRepository facultyRepository;
	@RequestMapping(value="/admin/faculty", method=RequestMethod.GET, produces= {"application/json"})
	public @ResponseBody List<Faculty> getAllFacultys(){
		return facultyRepository.findAll();
	}
	
	@Autowired
	private StudentJPARepository studentRepository;
	@RequestMapping(value="/admin/student", method=RequestMethod.GET, produces= {"application/json"})
	public @ResponseBody Iterable<Student> getAllStudents(){
		return studentRepository.findAll();
	}

}
