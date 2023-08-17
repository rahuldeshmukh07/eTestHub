/**
 * 
 */
package com.mskill.ExamPortal.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 1838177
 *
 */

@Controller
@RequestMapping("/api/v1")
public class LogoutController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  
	 */
	public LogoutController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/logout")
	public String validateUser( HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "index";
	}

}
