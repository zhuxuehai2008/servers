package com.xjc.interfaceserver.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

@Component
public class ExceptionController implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception e) {
		System.out.println("a");
		ModelAndView modelAndView = new ModelAndView();
		HashedMap map = new HashedMap();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("aa", "22");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(jsonObject.toString());
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
		return modelAndView;
	}
	
}
