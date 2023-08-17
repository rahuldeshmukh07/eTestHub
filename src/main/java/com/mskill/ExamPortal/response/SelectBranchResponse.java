package com.mskill.ExamPortal.response;

public class SelectBranchResponse{
	private String branch_name;
	
	public SelectBranchResponse() {
		
	}

	public SelectBranchResponse(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
}
