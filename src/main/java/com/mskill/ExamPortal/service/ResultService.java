package com.mskill.ExamPortal.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mskill.ExamPortal.JDBCRepository.ResultJDBCRepositoryImpl;
import com.mskill.ExamPortal.model.ResultResponse;

@Service


public class ResultService {
	@Autowired
	public ResultJDBCRepositoryImpl resultJDBCRepositoryImpl;
	

	public  List<ResultResponse> getResultByStudent(Long studentId) {
		List<ResultResponse> results=resultJDBCRepositoryImpl.findResultbyStudentId(studentId);
		
		return results;
	}

}

