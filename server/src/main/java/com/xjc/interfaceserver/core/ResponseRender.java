package com.xjc.interfaceserver.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseRender {
	private HttpServletResponse response;
	private Object obj;
	public ResponseRender(HttpServletResponse resp,Object object){
		response = resp ;
		obj =object;
	}
	public static void send(HttpServletResponse response,Object obj){
		try {
			PrintWriter writer = response.getWriter();
			writer.write(obj.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(){
		try {
			PrintWriter writer = response.getWriter();
			writer.write(obj.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
