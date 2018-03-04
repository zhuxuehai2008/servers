package com.zxh.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxh.interfaces.entity.Brand;

import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand>{
	public List<Brand> selectPage(@Param("pageBegin")Integer pageBegin,@Param("pageSize")Integer pageSize);
	public Integer countAll();
	public List<Brand> selectLikeName(@Param("key") String key);
}
