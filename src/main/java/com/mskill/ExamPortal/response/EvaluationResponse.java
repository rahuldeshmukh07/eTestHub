package com.mskill.ExamPortal.response;

public class EvaluationResponse {
	
	private String question;
	private String answer;
	private int answer_id;
	
	
	
	public EvaluationResponse() {
		// TODO Auto-generated constructor stub
	}


	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	


	public EvaluationResponse(String question, String answer, int answer_id) {
		this.question = question;
		this.answer = answer;
		this.answer_id = answer_id;
	}


	public int getAnswer_id() {
		return answer_id;
	}


	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
		

}
