package com.xjc.interfaceserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xjc.interfaceserver.core.ResponseObjV01;

@Controller
public class loginController {
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseObjV01 login(){
		return null;
	}
}
