package com.zxh.interfaces.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxh.core.entity.ResponseObj;
import com.zxh.core.util.Constant;
import com.zxh.interfaces.entity.Brand;
import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.service.BrandService;
import com.zxh.interfaces.service.CategoryService;

/**
 * 
 * @author zxh
 * 2018年3月4日
 */
@Controller
@RequestMapping("commodity/")
public class CommodityController {
	/**
	 * 分类
	 */
	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public String categoryList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "commodity/categoryList";
	}
	@RequestMapping(value = "/categoryPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categoryPage(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue="0")String pageBegin,@RequestParam(defaultValue="10") String pageSize){
		List<Category> selectPage = categoryService.selectPage(Integer.valueOf(pageBegin),Integer.valueOf(pageSize));
		Integer countAll = categoryService.countAll();
		HashMap<String, Object> result = new HashMap<String,Object>();
		result.put("list",selectPage);
		result.put("totalCount",countAll);
		return new ResponseObj(result);
	}
	@RequestMapping(value = "/categoryChildren", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categoryChildren(HttpServletRequest request,HttpServletResponse response,ModelMap model,@RequestParam Integer id){
		List<HashMap<String,String>>  result= categoryService.categoryChildren(id);
		System.out.println(result);
		ResponseObj responseObj = new ResponseObj(result);
		return responseObj;
	}
	@RequestMapping(value = "/categorySearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categorySearch(HttpServletRequest request,HttpServletResponse response,ModelMap model,@RequestParam String key){
		List<Category>  result= categoryService.selectLike(key);
		ResponseObj responseObj = new ResponseObj(result);
		return responseObj;
	}
	@RequestMapping(value = "/categoryForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categoryForm(HttpServletRequest request,HttpServletResponse response,Category one) throws Exception{
		int flag = categoryService.save(one);
		if(flag>0){
			return new ResponseObj();
		}else{
			return new ResponseObj(Constant.StatusFailOperate);
		}
	}
	@RequestMapping(value = "/categoryDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj categoryDelete(HttpServletRequest request,HttpServletResponse response,@RequestParam String id) throws Exception{
		int flag = categoryService.delete(Integer.valueOf(id));
		if(flag>0){
			return new ResponseObj();
		}else{
			return new ResponseObj(Constant.StatusFailOperate);
		}
	}
	@RequestMapping(value = "/categoryForm", method = RequestMethod.GET)
	public String categoryForm(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		if(null!=id){
			Category one = categoryService.selectOne(Integer.valueOf(id));
			Category parent = categoryService.selectOne(one.parentId);
			model.addAttribute("one", one);
			model.addAttribute("parent", parent);
		}
		return "commodity/categoryForm";
	}
	/**
	 * 品牌
	 */
	@RequestMapping(value = "/brandList", method = RequestMethod.GET)
	public String brandList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "commodity/brandList";
	}
	@RequestMapping(value = "/brandPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj brandPage(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue="0")String pageBegin,@RequestParam(defaultValue="10") String pageSize){
		List<Brand> selectPage = brandService.selectPage(Integer.valueOf(pageBegin),Integer.valueOf(pageSize));
		Integer countAll = brandService.countAll();
		HashMap<String, Object> result = new HashMap<String,Object>();
		result.put("list",selectPage);
		result.put("totalCount",countAll);
		return new ResponseObj(result);
	}
	@RequestMapping(value = "/brandSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj brandSearch(HttpServletRequest request,HttpServletResponse response,ModelMap model,@RequestParam String key){
		List<Brand>  result= brandService.selectLike(key);
		ResponseObj responseObj = new ResponseObj(result);
		return responseObj;
	}
	@RequestMapping(value = "/brandDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj brandDelete(HttpServletRequest request,HttpServletResponse response,@RequestParam String id) throws Exception{
		int flag = brandService.delete(Integer.valueOf(id));
		if(flag>0){
			return new ResponseObj();
		}else{
			return new ResponseObj(Constant.StatusFailOperate);
		}
	}
	@RequestMapping(value = "/brandForm", method = RequestMethod.GET)
	public String brandForm(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		if(null!=id){
			Brand one = brandService.selectOne(Integer.valueOf(id));
			//Category parent = categoryService.selectOne(one.parentId);
			model.addAttribute("one", one);
			//model.addAttribute("parent", parent);
		}
		return "commodity/brandForm";
	}
	@RequestMapping(value = "/brandForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj brandForm(HttpServletRequest request,HttpServletResponse response,Brand one) throws Exception{
		int flag = brandService.save(one);
		if(flag>0){
			return new ResponseObj();
		}else{
			return new ResponseObj(Constant.StatusFailOperate);
		}
	}
	@Autowired private CategoryService categoryService ;
	@Autowired private BrandService brandService;
}
