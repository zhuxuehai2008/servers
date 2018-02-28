package com.zxh.interfaces.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.zxh.core.dao.BaseDao;

public abstract class BaseService<M extends Serializable,K extends Serializable> {  
    @Autowired  
    protected BaseDao<M,K> baseDao;  
  
    public M insert(M m) {  
    	
    	return baseDao.insert(m);  
    }  
}  
