package com.zxh.interfaces.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="commodity")
public class Commodity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String name;
	public Integer categoryId;
	public Integer brandId;
	public String code;
	public Integer stock;
	public Float showPrice;
	public Float realPrice;
	public String thumbnailPic; 
	public String listPic;
	public String detailPic;
}
