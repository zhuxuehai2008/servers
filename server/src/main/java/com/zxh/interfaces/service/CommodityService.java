package com.zxh.interfaces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxh.core.exception.GlobalException;
import com.zxh.interfaces.entity.Brand;
import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.entity.Commodity;
import com.zxh.interfaces.mapper.CommodityMapper;

@Service
@Transactional
public class CommodityService {
	public Commodity selectOne(Integer id){
		return commodityMapper.selectByPrimaryKey(id);
	}
	public List<Commodity> selectAll(){
		return commodityMapper.selectAll();
	}
	public List<Commodity> selectPage(Integer pageBegin, Integer pageSize){
		return commodityMapper.selectPage(pageBegin, pageSize);
	}
	public List<Commodity> selectPageSearch(Integer pageBegin, Integer pageSize,String key){
		return commodityMapper.selectPageSearch(pageBegin, pageSize, key);
	}
	public Integer countAll(){
		return commodityMapper.countAll();
	}
	public Integer countSearch(String key){
		return commodityMapper.countSearch(key);
	}
	public int save(Commodity one) throws GlobalException{
		int result =0;
		if(null==one.getId()){
			if(!one.checkNoId()){ throw  new GlobalException("提交数据不完整或错误！！");}
			result = commodityMapper.insert(one);
		}else{
			if(!one.check()){ throw  new GlobalException("提交数据不完整或错误！！");}
			result = commodityMapper.updateByPrimaryKey(one);
		}
		return result;
	}
	public List<Commodity> selectLike(String key){
		return commodityMapper.selectLikeName(key);
	}
	@Autowired private CommodityMapper commodityMapper;
}
