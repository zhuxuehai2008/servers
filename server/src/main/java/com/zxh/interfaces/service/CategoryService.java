package com.zxh.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxh.interfaces.entity.Category;

@Service
@Transactional
public class CategoryService extends BaseService<Category,Integer>{

}
