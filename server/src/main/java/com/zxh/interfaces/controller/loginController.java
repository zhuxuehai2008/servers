package com.zxh.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zxh.core.entity.ResponseObj;

@Controller
public class loginController {
	@RequestMapping(value = "login/interface", method = RequestMethod.POST)
	public ResponseObj loginInterface(){
		return null;
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginSuccess(){
		return "login";
	}
}
