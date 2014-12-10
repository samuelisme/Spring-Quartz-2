package com.samuelisme.spring.task;

public class TestTask {
	private String message;
	

	public void printMessage() throws InterruptedException {
		System.out.println(message);
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
