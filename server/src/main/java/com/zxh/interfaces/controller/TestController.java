package com.zxh.interfaces.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxh.core.entity.ResponseObj;
import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.entity.TestEntity;
import com.zxh.interfaces.service.CategoryService;

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
	@RequestMapping("page")
	@ResponseBody
	public ResponseObj testPage(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue="0")String pageBegin,@RequestParam(defaultValue="10") String pageSize) throws Exception{
		List<Category> selectPageHelper = categoryService.selectPageHelper(Integer.valueOf(pageBegin), Integer.valueOf(pageSize));
		return new ResponseObj(selectPageHelper);
	}
	@Autowired private CategoryService categoryService;
}
