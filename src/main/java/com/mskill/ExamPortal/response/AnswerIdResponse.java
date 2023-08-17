package com.mskill.ExamPortal.response;

public abstract class AnswerIdResponse {
	private int answer_id;

	public AnswerIdResponse() {}
	public AnswerIdResponse(int answer_id) {
		this.answer_id = answer_id;
	}

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	
	
}
