package com.zxh.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxh.interfaces.entity.Category;

import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category>{
	public List<Category> selectPage(@Param("pageBegin")Integer pageBegin,@Param("pageSize")Integer pageSize);
}
