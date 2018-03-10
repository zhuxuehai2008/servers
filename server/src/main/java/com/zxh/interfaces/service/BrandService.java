package com.zxh.interfaces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxh.core.exception.GlobalException;
import com.zxh.interfaces.entity.Brand;
import com.zxh.interfaces.mapper.BrandMapper;

@Service
@Transactional
public class BrandService extends BaseService<Brand,Integer>{
	public Brand selectOne(Integer id){
		return brandMapper.selectByPrimaryKey(id);
	}
	public List<Brand> selectAll(){
		return brandMapper.selectAll();
	}
	public List<Brand> selectPage(Integer pageBegin, Integer pageSize){
		return brandMapper.selectPage(pageBegin, pageSize);
	}
	
	public Integer countAll(){
		return brandMapper.countAll();
	}
	public List<Brand> selectLike(String key){
		return brandMapper.selectLikeName(key);
	}
	public int save(Brand one) throws GlobalException{
		int result =0;
		if(null==one.getId()){
			if(!one.checkNoId()){ throw  new GlobalException("提交数据不完整！！");}
			result = brandMapper.insert(one);
		}else{
			if(!one.check()){ throw  new GlobalException("提交数据不完整！！");}
			result = brandMapper.updateByPrimaryKey(one);
		}
		return result;
	}
	public int delete(Integer id) throws GlobalException{
		int result = 0;
/*		List<Category> list = brandMapper.selectByParentId(id);
		if(list.size()>0){throw  new GlobalException("存在子节点，不能删除！！");}
		result = brandMapper.deleteByPrimaryKey(id);*/
		return result;
	}
	@Autowired  BrandMapper brandMapper;
}
