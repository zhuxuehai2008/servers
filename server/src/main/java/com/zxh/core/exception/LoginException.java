package com.zxh.core.exception;

import java.util.HashMap;

public class LoginException extends Exception{
	public LoginException() {
		super();
	}

	public LoginException(String message) {
		super(message);
	}
	
	public LoginException(Integer status ,String message) {
		super(message);
	}
	
}
