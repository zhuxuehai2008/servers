package com.zxh.interfaces.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="brand")
public class Brand implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String name;
	@Column(name = "pic")
	public String pic;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}
	
}
