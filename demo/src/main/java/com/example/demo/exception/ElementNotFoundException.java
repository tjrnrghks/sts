package com.example.demo.exception;

//예외를 직접 구현할 때는 exception 을 상속 받는다
//만약 runtime exception 을 상속받으면 

public class ElementNotFoundException extends RuntimeException {

	public ElementNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ElementNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
