/**
 * 
 */
package com.mskill.ExamPortal.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.service.LoginService;


/**
 * @author 1838177
 *
 */


@RestController
@RequestMapping("/api/v1")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	LoginService loginService;

	public LoginController() {
	}

	@PostMapping("/login")
	public ResponseEntity<String> validateUser( @RequestBody String credentials, HttpServletRequest request, HttpServletResponse response)  throws ResourceNotFoundException, JSONException{
		JSONObject json = new JSONObject(credentials);  
		String username = json.getString("username");  
		String password = json.getString("password");
		HttpSession session = request.getSession();
		if (username.length()==7) {
			if(loginService.validateStudent(username, password)){
				session.setAttribute("username", username);
/*				System.out.println(session);
				System.out.println(session.getAttribute("username"));
*/				return ResponseEntity.ok().body("Student");
			}
			else
				return ResponseEntity.status(404).body("login");
		}
		else if (username.length()==5) {
			System.out.println(username);
			if(loginService.validateModerator(username, password)){
				session.setAttribute("username", username);
				return ResponseEntity.ok().body("Moderator");
			}
			else
				return ResponseEntity.status(404).body("login");		
		}
		else if (username.length()==4) {
			if(loginService.validateFaculty(username, password)){
				session.setAttribute("username", username);
				return ResponseEntity.ok().body("Faculty");
			}
			else
				return ResponseEntity.status(404).body("login");			
		}
		else if (username.length()==3) {
			 if(loginService.validateAdmin(username, password)){
				 session.setAttribute("username", username);
				 return ResponseEntity.ok().body("Admin");
			 }
			 else
				 return ResponseEntity.status(404).body("login");			
		 }
		else {
			 return ResponseEntity.status(404).body("login");
		 }
	}
}