package com.zxh.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zxh.core.entity.ResponseObj;

@Controller
public class loginController {
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseObj login(){
		return null;
	}
}
