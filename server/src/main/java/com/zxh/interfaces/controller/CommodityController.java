package com.zxh.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zxh.core.dao.BaseDaoImpl;
import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.mapper.CategoryMapper;
import com.zxh.interfaces.service.CategoryService;

@Controller
@RequestMapping("commodity/")
public class CommodityController {
	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public String categoryList(){
		Category category = new Category();
		category.setLevel(3);
		category.setName("asdsad");
		category.setParentId(1);
		category.setPicture("sdfsf");
		Category insert = categoryService.insert(category);
		System.out.println(insert);
		return "commodity/categoryList";
	}
	@RequestMapping(value = "/categoryForm", method = RequestMethod.GET)
	public String categoryForm(){
		return "commodity/categoryForm";
	}
	@Autowired private CategoryMapper categoryMapper;
	@Autowired private CategoryService categoryService ;
}
