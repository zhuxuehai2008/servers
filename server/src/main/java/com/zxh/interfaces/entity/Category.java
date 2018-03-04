package com.zxh.interfaces.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "category")
public class Category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String name;
	@Column(name = "parentId")
	public Integer parentId;
	@Column(name = "pic")
	public String pic;
	public Integer level;
	@Column(name = "isEnd")
	public Integer isEnd;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}
	public Boolean check(){
		if(null==this.id||null==this.parentId||null==this.pic||null==isEnd||this.pic.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	public Boolean checkNoId(){
		if(null==this.parentId||null==this.pic||null==isEnd||this.pic.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parentId=" + parentId + ", pic=" + pic + ", level="
				+ level + "]";
	}
	
}
