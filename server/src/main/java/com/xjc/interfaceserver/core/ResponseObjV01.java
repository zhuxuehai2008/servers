package com.xjc.interfaceserver.core;

import net.sf.json.JSONObject;

public class ResponseObjV01 {
	private JSONObject result ;
	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	/*
	 * 构造方法
	 */
	public ResponseObjV01(Object obj) {
		result = new JSONObject();
		result.put("status", 200);
		result.put("msg", "成功");
		result.put("result", obj);
	}

	public ResponseObjV01(Integer staus,String msg,Object obj){
		result = new JSONObject();
		result.put("status", staus);
		result.put("msg", msg);
		result.put("result", obj);
	}
	/*
	 * 构造方法结束
	 */
	public String toString(){
		return result.toString();
	}
}
