package com.mskill.ExamPortal.response;

import com.mskill.ExamPortal.model.Moderator;

public class ModeratorResponse {
	
	private int moderator_id;
	private String salutation;
	private String first_name;
	private String last_name;
	private String moderator_email;
	private long mobile;
	private int college_id;
	private int subject_id;
	private String subject_name;
/*	private String password;
	
	public ModeratorResponse(int moderator_id, String password) {
		this.moderator_id = moderator_id;
		this.password = password;
	}

*/	
	
	
	public ModeratorResponse() {}

	
	
	public ModeratorResponse(int moderator_id, String salutation, String first_name, String last_name, String moderator_email, long mobile,
			int college_id, int subject_id, String subject_name) {
		super();
		this.moderator_id = moderator_id;
		this.salutation = salutation;
		this.first_name = first_name;
		this.last_name = last_name;
		this.moderator_email = moderator_email;
		this.mobile = mobile;
		this.college_id = college_id;
		this.subject_id = subject_id;
		this.subject_name = subject_name;
	}

	public int getModerator_id() {
		return moderator_id;
	}
	
	public void setModerator_id(int moderator_id) {
		this.moderator_id = moderator_id;
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

	public String getModerator_email() {
		return moderator_email;
	}

	public void setModerator_email(String moderator_email) {
		this.moderator_email = moderator_email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}
	

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public static ModeratorResponse generateModeratorView(Moderator obj) {
		ModeratorResponse ModeratorView = new ModeratorResponse();
		ModeratorView.setModerator_id(obj.getModerator_id());
		ModeratorView.setSalutation(obj.getSalutation());
		ModeratorView.setFirst_name(obj.getFirst_name());
		ModeratorView.setLast_name(obj.getLast_name());
		ModeratorView.setModerator_email(obj.getModerator_email());
		ModeratorView.setMobile(obj.getMobile());
		ModeratorView.setCollege_id(obj.getCollege_id());
		/*ModeratorView.setSubject_id(obj.getSubject_id());
		ModeratorView.setSubject_name(obj.getSubject_name());*/
		 return ModeratorView;
	}	
	
}
