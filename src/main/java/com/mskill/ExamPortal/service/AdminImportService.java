package com.mskill.ExamPortal.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mskill.ExamPortal.model.Faculty;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.model.Student;

public class AdminImportService {
	public static String TYPE = "text/csv";
	static String[] HEADERstudent = { "student_id", "salutation", "first_name", "last_name", "college_id", "branch_id",
			"student_email", "password", "mobile", "academic_year" };
	// static String[] HEADERfaculty = { "faculty_id", "salutation", "first_name",
	// "last_name", "clg_id", "branch",
	// "email", "password", "mobile" };
	// static String[] HEADERmoderator = { "moderator_id", "salutation",
	// "first_name", "last_name", "clg_id", "email",
	// "password", "mobile" };

	public static boolean hasCSVFormat(MultipartFile file1) {
		System.out.println(file1.getContentType());
		if (TYPE.equals(file1.getContentType()) || file1.getContentType().equals("application/vnd.ms-excel")) {
			return true;
		}

		return false;
	}

	public static List<Student> csvToStudent(InputStream is) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Student> studentList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Student studentImport = new Student(
						csvRecord.get("salutation"), csvRecord.get("first_name"), csvRecord.get("last_name"),
						Integer.parseInt(csvRecord.get("college_id")), Integer.parseInt(csvRecord.get("branch_id")),
						csvRecord.get("student_email"),
						passwordEncoder.encode(csvRecord.get("first_name") + "@" + csvRecord.get("mobile")),
						Long.parseLong(csvRecord.get("mobile")), Integer.parseInt(csvRecord.get("academic_year")), 101);

				studentList.add(studentImport);
			}

			return studentList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static List<Faculty> csvToFaculty(InputStream is) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Faculty> facultyList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Faculty facultyImport = new Faculty(
						csvRecord.get("salutation"), csvRecord.get("first_name"), csvRecord.get("last_name"),csvRecord.get("faculty_email"),
						passwordEncoder.encode(csvRecord.get("first_name") + "@" + csvRecord.get("mobile")),
						Long.parseLong(csvRecord.get("mobile")),	Integer.parseInt(csvRecord.get("college_id")),  101);

				facultyList.add(facultyImport);
			}

			return facultyList;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}

	}

	public static List<Moderator> csvToModerator(InputStream is) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Moderator> moderatorList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Moderator moderatorImport = new Moderator(
						csvRecord.get("salutation"), csvRecord.get("first_name"), csvRecord.get("last_name"),
						csvRecord.get("moderator_email"), passwordEncoder.encode(csvRecord.get("first_name") + "@" + csvRecord.get("mobile")),
						Long.parseLong(csvRecord.get("mobile")),Integer.parseInt(csvRecord.get("college_id")), 101);
				
				moderatorList.add(moderatorImport);
			}

			return moderatorList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}

	}

	public static ByteArrayInputStream studentToCSV(List<Student> studentList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (Student studentImport : studentList) {
				List<String> data = Arrays.asList(String.valueOf(studentImport.getStudent_id()),
						studentImport.getSalutation(), studentImport.getFirst_name(), studentImport.getLast_name(),
						String.valueOf(studentImport.getCollege_id()), String.valueOf(studentImport.getBranch_id()),
						studentImport.getStudent_email(), studentImport.getPassword(),
						String.valueOf(studentImport.getMobile()), String.valueOf(studentImport.getAcademic_year()));

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream facultyToCSV(List<Faculty> facultyList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (Faculty facultyImport : facultyList) {
				List<String> data = Arrays.asList(String.valueOf(facultyImport.getFaculty_id()),
						facultyImport.getSalutation(), facultyImport.getFirst_name(), facultyImport.getLast_name(),
						String.valueOf(facultyImport.getCollege_id()), facultyImport.getFaculty_email(),
						facultyImport.getPassword(), String.valueOf(facultyImport.getMobile())

				);

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream moderatorToCSV(List<Moderator> moderatorList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (Moderator moderatorImport : moderatorList) {
				List<? extends Object> data = Arrays.asList(
						String.valueOf(moderatorImport.getModerator_id()),
						moderatorImport.getSalutation(), 
						moderatorImport.getFirst_name(),
						moderatorImport.getLast_name(), 
						moderatorImport.getCollege_id(),
						moderatorImport.getModerator_email(), 
						moderatorImport.getPassword(),
						String.valueOf(moderatorImport.getMobile())
						);
				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}
