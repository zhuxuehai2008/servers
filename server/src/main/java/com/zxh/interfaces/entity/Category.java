package com.zxh.interfaces.entity;

import java.io.Serializable;

import com.zxh.core.annotation.PrimaryKey;
import com.zxh.core.annotation.Table;
import com.zxh.core.annotation.TableColumn;
@Table("category")
public class Category implements Serializable{
	@PrimaryKey("id")
	public Integer id;
	public String name;
	public Integer parentId;
	@TableColumn("pic")
	public String picture;
	public Integer level;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parentId=" + parentId + ", picture=" + picture + ", level="
				+ level + "]";
	}
	
}
