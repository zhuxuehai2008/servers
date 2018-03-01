package com.zxh.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author zxh
 * 2018年2月27日
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T extends Serializable,PK extends Serializable> {

	public int insert(T t);
	
	public int update(T t);
	
	public int delete(PK id);
	
	public T findOneById(PK id);
	
	public List<T> findAll();	

	public int findAllCount();
}
