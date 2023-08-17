
package com.mskill.ExamPortal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mskill.ExamPortal.JDBCRepository.FacultyJDBCRepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.FacultyQuestionBank;
import com.mskill.ExamPortal.model.QuestionBank;
import com.mskill.ExamPortal.response.EvaluationResponse;
import com.mskill.ExamPortal.response.FacultyResponse;
import com.mskill.ExamPortal.response.SelectExamResponse;
import com.mskill.ExamPortal.response.SelectSubjectResponse;
import com.mskill.ExamPortal.response.TaskBarResponse;
import com.mskill.ExamPortal.service.FacultyService;


@RestController
@RequestMapping("/api/v1")
public class FacultyController extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	FacultyService facultyService;

	@Autowired
	FacultyJDBCRepository facultyJDBC;

	@GetMapping("/faculty")
	public List<FacultyResponse> getAllFaculties() throws ResourceNotFoundException {
		return facultyService.getAllFaculties();
	}


	@GetMapping("/faculty/")
	public ResponseEntity<FacultyResponse> getFacultyById(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException
	{
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Integer faculty_id = Integer.parseInt(sessionId);
		return ResponseEntity.ok().body(facultyService.getFacultyById(faculty_id));
	}


	@GetMapping("/faculty/subjectview/")
	public List<SelectSubjectResponse> getSubjectName(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException, IOException  {
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Integer faculty_id = Integer.parseInt(sessionId);
		return facultyService.getSubjectName(faculty_id);
	}


	@GetMapping(path="/faculty/examview/", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SelectExamResponse> getExamName(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException  {
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Integer faculty_id = Integer.parseInt(sessionId);
		return facultyService.getExamName(faculty_id);
	}


	@SuppressWarnings("unused")
	@RequestMapping(value="/upload", method= RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Integer> fileUpload(@RequestParam("file")MultipartFile file,@RequestParam("exam_id")int examId,@RequestParam("subject_id")int subjectId, HttpServletRequest request, HttpServletResponse response) throws IOException, ResourceNotFoundException {


		List<FacultyQuestionBank> records= new ArrayList<FacultyQuestionBank>();
		if (file.isEmpty()) {
			String message="Please select a valid CSV file to upload.";
			return ResponseEntity.ok().body(0);
		}else {
			try{	
				BufferedReader b = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String line;
				while ((line = b.readLine()) != null){
					FacultyQuestionBank question=new FacultyQuestionBank();
					HttpSession session = request.getSession(false);
					String sessionId = (String) session.getAttribute("username");
					Integer faculty_id = Integer.parseInt(sessionId);

					question.setfaculty_id(faculty_id);
//					System.out.println(faculty_id);
					question.setexam_id(examId);
					System.out.println(examId);
					question.setsubject_id(subjectId);
					System.out.println(subjectId);
					question.setquestions(line);
					System.out.println(line);

					records.add(question);
				}
				if(records!=null) {
					facultyService.setQuestionBank(records);
					return ResponseEntity.ok().body(1);
				}
				else {
					return ResponseEntity.ok().body(0);
				}

			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		//	        records.forEach(action->System.out.println(action));
		//	        facultyService.setQuestionBank(records);
		return null;


	}


	
	@GetMapping(path="/taskbar/")
	public List<TaskBarResponse> getTaskBarById(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException
	{
		HttpSession session = request.getSession(false);
		String sessionId = (String) session.getAttribute("username");
		Integer faculty_id = Integer.parseInt(sessionId);
		return facultyService. getTaskBarById(faculty_id);
	}



	@GetMapping(path="/faculty/evaluationpage/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EvaluationResponse> getEvaluation(@PathVariable(value = "id") int answer_id) throws ResourceNotFoundException  {
		return facultyService.getEvaluationPage(answer_id);
	}


	@PatchMapping(value="/submitmarks/{id}")
	public ResponseEntity<Integer> setMarks(@PathVariable(value ="id") int answer_id, @RequestParam("marks") int marks) throws ResourceNotFoundException {
		return ResponseEntity.ok().body(facultyService.setMarks(answer_id,marks));

	}


}













