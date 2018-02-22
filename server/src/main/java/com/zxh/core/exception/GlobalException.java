package com.zxh.core.exception;

import java.util.HashMap;

public class GlobalException extends Exception{
	public Integer status;
	public String message;
	public GlobalException() {
		super();
	}

	public GlobalException(String message) {
		super(message);
	}
	
	public GlobalException(Integer status ,String message) {
		super(message);
	}
	
}
