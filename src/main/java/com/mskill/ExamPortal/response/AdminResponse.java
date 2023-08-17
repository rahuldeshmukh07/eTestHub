/**
 * 
 */
package com.mskill.ExamPortal.response;

import com.mskill.ExamPortal.model.Admin;

/**
 * @author 1838177
 *
 */
public class AdminResponse {

	/**
	 * 
	 */
	private long admin_id;
	private String salutation;
	private String first_name;
    private String last_name;
    private String admin_email;
    private String college_name;
    private Boolean active;
	private int created_on;
	private long mobile;
    
    public AdminResponse() {
		// TODO Auto-generated constructor stub
	}
    
    
	public AdminResponse(long admin_id, String salutation, String first_name, String last_name, String admin_email,
			String college_name, long mobile) {

		this.admin_id = admin_id;
		this.salutation = salutation;
		this.first_name = first_name;
		this.last_name = last_name;
		this.admin_email = admin_email;
		this.college_name = college_name;
		this.mobile = mobile;

	}

	public long getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}


	public String getSalutation() {
		return salutation;
	}


	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getAdmin_email() {
		return admin_email;
	}


	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}


	public String getCollege_name() {
		return college_name;
	}


	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public int getCreated_on() {
		return created_on;
	}


	public void setCreated_on(int created_on) {
		this.created_on = created_on;
	}


	public static AdminResponse generateView(Admin obj) {
		AdminResponse adminView = new AdminResponse();
		adminView.setFirst_name(obj.getFirst_name());
		adminView.setLast_name(obj.getLast_name());
		adminView.setSalutation(obj.getSalutation());
		adminView.setAdmin_email(obj.getAdmin_email());
		adminView.setAdmin_id(obj.getAdmin_id());
		 return adminView;
		
	}

}
