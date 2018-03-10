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
	@Column(name = "categoryId")
	public Integer categoryId;
	@Column(name = "brandId")
	public Integer brandId;
	public String code;
	public Integer stock;
	@Column(name = "showPrice")
	public Float showPrice;
	@Column(name = "realPrice")
	public Float realPrice;
	@Column(name = "thumbnailPic")
	public String thumbnailPic; 
	@Column(name = "banner1Pic")
	public String banner1Pic;
	@Column(name = "banner2Pic")
	public String banner2Pic;
	@Column(name = "banner3Pic")
	public String banner3Pic;
	@Column(name = "detailPic")
	public String detailPic;
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
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Float getShowPrice() {
		return showPrice;
	}
	public void setShowPrice(Float showPrice) {
		this.showPrice = showPrice;
	}
	public Float getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Float realPrice) {
		this.realPrice = realPrice;
	}
	public String getThumbnailPic() {
		return thumbnailPic;
	}
	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}
	public String getBanner1Pic() {
		return banner1Pic;
	}
	public void setBanner1Pic(String banner1Pic) {
		this.banner1Pic = banner1Pic;
	}
	public String getBanner2Pic() {
		return banner2Pic;
	}
	public void setBanner2Pic(String banner2Pic) {
		this.banner2Pic = banner2Pic;
	}
	public String getBanner3Pic() {
		return banner3Pic;
	}
	public void setBanner3Pic(String banner3Pic) {
		this.banner3Pic = banner3Pic;
	}
	public String getDetailPic() {
		return detailPic;
	}
	public void setDetailPic(String detailPic) {
		this.detailPic = detailPic;
	}
	public boolean check(){
		if(null==this.id||null==this.name||null==this.categoryId||null==this.brandId||null==this.code||null==this.stock||null==this.realPrice||null==this.showPrice||null==this.thumbnailPic||
				null==this.banner1Pic||null==this.banner2Pic||null==this.banner3Pic||null==this.detailPic||this.name.isEmpty()||this.stock<=0||this.realPrice<=0.0f||
				this.showPrice<=this.realPrice||this.thumbnailPic.isEmpty()||this.banner1Pic.isEmpty()||this.banner2Pic.isEmpty()||this.banner3Pic.isEmpty()||this.detailPic.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	public boolean checkNoId(){
		if(null==this.name||null==this.categoryId||null==this.brandId||null==this.code||null==this.stock||null==this.realPrice||null==this.showPrice||null==this.thumbnailPic||
				null==this.banner1Pic||null==this.banner2Pic||null==this.banner3Pic||null==this.detailPic||this.name.isEmpty()||this.stock<=0||this.realPrice<=0.0f||
				this.showPrice<=this.realPrice||this.thumbnailPic.isEmpty()||this.banner1Pic.isEmpty()||this.banner2Pic.isEmpty()||this.banner3Pic.isEmpty()||this.detailPic.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
}
