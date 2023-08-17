package com.mskill.ExamPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="branch")

public class Branch {
	private long branch_id;
	private String branch_name;
	private int created_by;
	
	public Branch() {
		
	}
	
	@Id
	@GeneratedValue(generator = "branch_sequence")
	@SequenceGenerator(schema = "public", name = "branch_seqeunce", sequenceName = "branch_sequence", allocationSize = 1)
    
	@Column(name = "branch_id", nullable = false)
	public long getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(long branch_id) {
		this.branch_id = branch_id;
	}
	@Column(name = "branch_name", nullable = false)
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	@Column(name = "created_by", nullable = false)
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Branch(long branch_id, String branch_name, int created_by) {
		this.branch_id = branch_id;
		this.branch_name = branch_name;
		this.created_by = created_by;
	}
	@Override
	public String toString() {
		return "Branch [branch_id=" + branch_id + ", branch_name=" + branch_name + ", created_by=" + created_by + "]";
	}
	

}
