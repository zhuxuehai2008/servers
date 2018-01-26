package com.xjc.interfaceserver.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjc.interfaceserver.core.ResponseObj;
import com.xjc.interfaceserver.core.ResponseObjV01;
import com.xjc.interfaceserver.entity.TestEntity;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("test")
public class TestController {
	@RequestMapping("a1")
	@ResponseBody
	public ResponseObj testController(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject = new JSONObject();
		HashedMap map = new HashedMap();
		map.put("name", "spring元");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("1235667");
		arrayList.add("zxxccvv");
		ResponseObj result = new ResponseObj(200,"aaa",null);//JSONObject.fromObject(map);
		System.out.println("aaaa");
		TestEntity testEntity = new TestEntity();
		testEntity.setName("llll111");
		//if(true){throw new Exception("asdsad");}
		return result;
	}
}
