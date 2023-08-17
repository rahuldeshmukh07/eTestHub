package com.mskill.ExamPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mskill.ExamPortal.model.Student;
//import com.mskill.ExamPortal.util.StudentSecurityConfiguration;
 
@Controller
public class WebController {
	
	
	

	@RequestMapping(value="/",method = RequestMethod.GET)
  public String homepage(){
      return "index";
  }

	@RequestMapping(value="/index_temp",method = RequestMethod.GET)
	  public String index_temp(){
	      return "index_temp";
	  }

	
  @RequestMapping(value="/login",method = RequestMethod.GET)
  public String loginpage(){
      return "login";
  }

/*
  @RequestMapping(value="/login/login",method = RequestMethod.POST)
  public String studentdashboardpage( @RequestBody Student student){
	  //StudentSecurityConfiguration.configure(null)
      return "dashboard";
  }*/

  @RequestMapping(value="/accessdenied",method = RequestMethod.GET)
  public String accessdeniedpage(){
      return "accessdenied";
  }
/*	@RequestMapping(value="/moderator/dashboard",method = RequestMethod.GET)
	  public String moderatordashboard(){
	      return "dashboardmoderator";
	  }
*/
/*  @RequestMapping(value="/faculty/dashboard",method = RequestMethod.GET)
	  public String facultydashboard(){
	      return "dashboardfaculty";
	  }
*/  
/*	@RequestMapping(value="/admin/dashboard",method = RequestMethod.GET)
	  public String admindashboard(){
	      return "dashboardadmin";
	  }
*/	
	
	
//	***********************************STUDENT************************************************
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	  public String studentdashboard(){
	      return "dashboardstudent";
	  }
	
	@RequestMapping(value="/profile",method = RequestMethod.GET)
	  public String studentprofile(){
	      return "profilestudent";
	  }
	
	@RequestMapping(value="/exam",method = RequestMethod.GET)
	  public String studentexam(){
	      return "examstudent";
	  }
	
	
//	***********************************STUDENT END************************************************
	
	
	
//	***********************************FACULTY************************************************
	
	@RequestMapping(value="/faculty/dashboard",method = RequestMethod.GET)
	  public String facultydashboard(){
	      return "facultydashboard";
	  }
	
	@RequestMapping(value="/faculty/contact",method = RequestMethod.GET)
	  public String facultycontact(){
	      return "contact";
	  }
	
	@RequestMapping(value="/faculty/worklist",method = RequestMethod.GET)
	  public String worklist(){
	      return "taskbar";
	  }
	
	@RequestMapping(value="/faculty/eval",method = RequestMethod.GET)
	  public String facultyeval(){
	      return "evaluateanswer";
	  }
	@RequestMapping(value="/faculty/setqb",method = RequestMethod.GET)
	  public String setqb(){
	      return "qb";
	  }
	@RequestMapping(value="/faculty/profile",method = RequestMethod.GET)
	  public String facultyprofile(){
	      return "facultyprofile";
	  }

	
	
//	***********************************FACULTY END************************************************
	

	
	
//	***********************************moderator************************************************
	
	@RequestMapping(value="/moderator/dashboard",method = RequestMethod.GET)
	  public String dashboardmod(){
	      return "moderatordashboard";
	  }
	
	@RequestMapping(value="/moderator/setpapermoderator",method = RequestMethod.GET)
	  public String setpapermod(){
	      return "setppr";
	  }
	
	@RequestMapping(value="/moderator/generatedmoderator",method = RequestMethod.GET)
	  public String generatemod(){
	      return "generatedpaper";
	  }
	
	@RequestMapping(value="/moderator/distribution",method = RequestMethod.GET)
	  public String distribution(){
	      return "distribution";
	  }
	
	@RequestMapping(value="/moderator/viewexaminermod",method = RequestMethod.GET)
	  public String viewexaminermod(){
	      return "viewexaminer";
	  }
	
	@RequestMapping(value="/moderator/profilemoderator",method = RequestMethod.GET)
	  public String profilemod(){
	      return "moderatorprofile";
	  }
	
	
//	***********************************moderator END************************************************

//	***********************************Admin start************************************************

	
	@RequestMapping(value="/admin/dashboard",method = RequestMethod.GET)
	  public String admindashboard(){
	      return "adminindex";
	  }
	
	@RequestMapping(value="/admin/student",method = RequestMethod.GET)
	  public String adminstudent(){
	      return "adminstudents";
	  }
	
	@RequestMapping(value="/admin/faculty",method = RequestMethod.GET)
	  public String adminfaculty(){
	      return "adminfaculty";
	  }
	
	@RequestMapping(value="/admin/moderator",method = RequestMethod.GET)
	  public String adminmoderator(){
	      return "adminmoderator";
	  }
/*
	@RequestMapping(value="/admin/result",method = RequestMethod.GET)
	  public String adminresult(){
	      return "adminresult";
	  }*/
	/*------------------------------------------*/
	@RequestMapping(value="/admin/faculty/import",method = RequestMethod.GET)
	  public String facultyimport(){
	      return "faculty-import";
	  }
	
	@RequestMapping(value="/admin/students/import",method = RequestMethod.GET)
	  public String studentsimport(){
	      return "students-import";
	  }
	
	@RequestMapping(value="/admin/moderator/import",method = RequestMethod.GET)
	  public String moderatorimport(){
	      return "moderator-import";
	  }
	
	@RequestMapping(value="/admin/profile",method = RequestMethod.GET)
	  public String adminprofile(){
	      return "adminprofile";
	  }
/*	@RequestMapping(value="/admin/logout",method = RequestMethod.GET)
	  public String adminlogout(){
	      return "adminlogout";
	  }*/
	@RequestMapping(value="/result/check",method = RequestMethod.GET)
	  public String resultcheck(){
	      return "result-check";
	  }

	@RequestMapping(value="/result/view",method = RequestMethod.GET)
	  public String resultview(){
	      return "result-view";
	  }
	
	
//	***********************************Admin END************************************************



}