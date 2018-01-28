package com.zxh.core.entity;

import com.zxh.core.util.Constant;

public class ResponseObj {
	private Object result ;
	private Integer status;
	private String msg;
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	/*
	 * 构造方法
	 */
	public ResponseObj(Object obj) {
		this.status = 200;
		this.msg = "成功";
		this.result= obj;
	}
	public ResponseObj(){
		this.status = 200;
		this.msg = "成功";
	}
	public ResponseObj(Integer staus,String msg,Object obj){
		this.status = staus;
		this.msg = msg;
		this.result = obj;
	}
	public ResponseObj(Integer staus,String msg){
		this.status = staus;
		this.msg = msg;
	}
	public ResponseObj(Integer status){
		this.status = status;
		this.msg = Constant.getStatusMsg(status);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
