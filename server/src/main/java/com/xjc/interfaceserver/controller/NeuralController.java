package com.xjc.interfaceserver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("neural/")
public class NeuralController {
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		return "nerualAnalysis";
	}
	@RequestMapping("prepareData")
	@ResponseBody
	public String prepareData(HttpServletRequest request){
		return "nerualAnalysis";
	}
}
