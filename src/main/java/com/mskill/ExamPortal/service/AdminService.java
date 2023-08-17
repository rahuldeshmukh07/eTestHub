package com.mskill.ExamPortal.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mskill.ExamPortal.JDBCRepository.AdminJDBCRepository;
import com.mskill.ExamPortal.JPARepository.FacultyRepository;
import com.mskill.ExamPortal.JPARepository.ModeratorRepository;
import com.mskill.ExamPortal.JPARepository.StudentJPARepository;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Faculty;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.model.Student;
import com.mskill.ExamPortal.response.AdminResponse;
import com.mskill.ExamPortal.response.StudentResponse;

@Service
public class AdminService {
	@Autowired
	StudentJPARepository repository1;

	public void save1(MultipartFile file1) {
		try {
			List<Student> student = AdminImportService.csvToStudent(file1.getInputStream());
			repository1.saveAll(student);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream loadStudent() {
		List<Student> student = repository1.findAll();

		ByteArrayInputStream in = AdminImportService.studentToCSV(student);
		return in;
	}

	public List<Student> getAllStudent() {
		return repository1.findAll();
	}

	@Autowired
	FacultyRepository repository2;

	public void save2(MultipartFile file2) {
		try {
			List<Faculty> faculty = AdminImportService.csvToFaculty(file2.getInputStream());
			repository2.saveAll(faculty);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream loadFaculty() {
		List<Faculty> faculty = repository2.findAll();

		ByteArrayInputStream in = AdminImportService.facultyToCSV(faculty);
		return in;
	}

	public List<Faculty> getAllFaculty() {
		return repository2.findAll();
	}

	@Autowired
	ModeratorRepository repository;

	public void save(MultipartFile file3) {
		try {
			List<Moderator> moderator = AdminImportService.csvToModerator(file3.getInputStream());
			repository.saveAll(moderator);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream loadModerator() {
		List<Moderator> moderator = repository.findAll();

		ByteArrayInputStream in = AdminImportService.moderatorToCSV(moderator);
		return in;
	}

	public List<Moderator> getAllModerator() {
		return repository.findAll();
	}
	
	
	@Autowired
	 private AdminJDBCRepository adminJDBC;
		
	 public AdminResponse getStudentById(int admin_id) throws ResourceNotFoundException{
		 AdminResponse admin = adminJDBC.findAdminViewById(admin_id)
		           .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + admin_id));
		 return admin;
	 }
}
