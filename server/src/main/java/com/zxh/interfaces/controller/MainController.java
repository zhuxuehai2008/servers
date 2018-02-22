package com.zxh.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zxh.core.entity.ResponseObj;

@Controller
public class MainController {
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(){
		return "error";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
}
