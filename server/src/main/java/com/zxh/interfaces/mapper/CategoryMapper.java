package com.zxh.interfaces.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxh.interfaces.entity.Category;

import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category>{
	public List<Category> selectPage(@Param("pageBegin")Integer pageBegin,@Param("pageSize")Integer pageSize);
	public List<HashMap<String,String>> categoryChildren(@Param("id")Integer id);
	public Integer countAll();
	public List<Category> selectLikeName(@Param("key") String key);
	public List<Category> selectByParentId(@Param("parentId") Integer parentId);
}
