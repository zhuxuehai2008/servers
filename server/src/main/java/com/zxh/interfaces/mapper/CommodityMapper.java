package com.zxh.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxh.interfaces.entity.Category;
import com.zxh.interfaces.entity.Commodity;

import tk.mybatis.mapper.common.Mapper;

public interface CommodityMapper extends Mapper<Commodity>{
	public List<Commodity> selectPage(@Param("pageBegin")Integer pageBegin,@Param("pageSize")Integer pageSize);
	public List<Commodity> selectPageSearch(@Param("pageBegin")Integer pageBegin,@Param("pageSize")Integer pageSize,@Param("key")String key);
	public Integer countAll();
	public Integer countSearch(@Param("key") String key);
	public List<Commodity> selectLikeName(@Param("key") String key);
}
