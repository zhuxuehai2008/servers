package com.zxh.core.exception;

import java.util.HashMap;

public class GlobalException extends Exception{
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
