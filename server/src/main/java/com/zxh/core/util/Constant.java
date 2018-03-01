package com.zxh.core.util;

import java.util.HashMap;

public class Constant {
	public static final Integer StatusSuccess = 200;
	public static final Integer StatusErrorParam = 409;
	public static final Integer StatusCommon = 410;
	public static final Integer StatusErrorSystem = 500;
	public static final Integer StatusFailOperate = 408;
	private static HashMap<Integer,String> status = new HashMap<Integer,String>();
	static {
		status.put(StatusErrorParam, "传入参数错误");
		status.put(StatusErrorSystem, "系统错误");
		status.put(StatusSuccess, "成功");
		status.put(StatusFailOperate, "成功");
	}
	public static String getStatusMsg(Integer status){
		return Constant.status.get(status);
	}
}
