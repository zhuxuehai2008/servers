package com.zxh.interfaces.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.mapper.CategoryMapper;

@Service
@Transactional
public class CategoryService extends BaseService<Category,Integer>{
	
	public int save(Category one){
		int result = 0;
		if(null==one.getId()){
			result = categoryMapper.insert(one);
		}else{
			result = categoryMapper.updateByPrimaryKey(one);
		}
		return result;
	}
	public List<Category> selectPage(Integer pageBegin, Integer pageSize){
		return categoryMapper.selectPage(pageBegin, pageSize);
	}
	
	public List<HashMap<String, String>> categoryChildren(Integer id){
		return categoryMapper.categoryChildren(id);
	}
	@Autowired CategoryMapper categoryMapper;
}
