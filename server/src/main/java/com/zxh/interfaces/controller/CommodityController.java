package com.zxh.interfaces.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zxh.core.entity.ResponseObj;
import com.zxh.core.util.Constant;
import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.service.CategoryService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("commodity/")
public class CommodityController {
	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public String categoryList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String pageBegin = request.getParameter("pageBegin");
		String pageSize = request.getParameter("pageSize");
		Category category = new Category();
		category.setLevel(3);
		category.setName("asdsad");
		category.setParentId(1);
		category.setPicture("sdfsf");
		//int a  = categoryMapper.insert(category);
		//System.out.println("return :"+a);
		List<Category> selectPage = categoryService.selectPage(Integer.valueOf(pageBegin),Integer.valueOf(pageSize));
		model.addAttribute("list", selectPage);
		return "commodity/categoryList";
	}
	@RequestMapping(value = "/categoryPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categoryPage(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String pageBegin = request.getParameter("pageBegin");
		String pageSize = request.getParameter("pageSize");
		List<Category> selectPage = categoryService.selectPage(Integer.valueOf(pageBegin),Integer.valueOf(pageSize));
		model.addAttribute("list", selectPage);
		return new ResponseObj(JSONArray.fromObject(selectPage));
	}
	@RequestMapping(value = "/categoryForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categoryList(HttpServletRequest request,HttpServletResponse response,Category one){
		int flag = categoryService.save(one);
		System.out.println("return :"+one);
		if(flag>0){
			return new ResponseObj();
		}else{
			return new ResponseObj(Constant.StatusFailOperate);
		}
	}
	@RequestMapping(value = "/categoryForm", method = RequestMethod.GET)
	public String categoryForm(){
		return "commodity/categoryForm";
	}
	@Autowired private CategoryService categoryService ;
}
