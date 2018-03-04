package com.zxh.interfaces.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxh.core.exception.GlobalException;
import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.mapper.CategoryMapper;

@Service
@Transactional
public class CategoryService extends BaseService<Category,Integer>{
	
	public int save(Category one) throws Exception{
		int result = 0;
		if(one.parentId!=0){
			Category parent = categoryMapper.selectByPrimaryKey(one.parentId);
			if(null == parent){ throw  new GlobalException("父节点不存在！");}
			if(parent.isEnd==1){ throw  new GlobalException("父分类是末端节点，修改添加失败！");}
		}
		if(null==one.getId()){
			if(!one.checkNoId()){ throw  new GlobalException("提交数据不完整！！");}
			result = categoryMapper.insert(one);
		}else{
			if(!one.check()){ throw  new GlobalException("提交数据不完整！！");}
			Category oldOne = categoryMapper.selectByPrimaryKey(one.id);
			if(null==oldOne){throw  new GlobalException("修改节点不存在！！");}
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
	public Integer countAll(){
		return categoryMapper.countAll();
	}
	public List<Category> selectLike(String key){
		return categoryMapper.selectLikeName(key);
	}
	public Category selectOne(Integer id){
		return categoryMapper.selectByPrimaryKey(id);
	}
	public int delete(Integer id) throws GlobalException{
		int result = 0;
		List<Category> list = categoryMapper.selectByParentId(id);
		if(list.size()>0){throw  new GlobalException("存在子节点，不能删除！！");}
		result = categoryMapper.deleteByPrimaryKey(id);
		return result;
	}
	@Autowired CategoryMapper categoryMapper;
}
