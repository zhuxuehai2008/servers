package com.zxh.core.component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zxh.core.entity.ResponseObj;
import com.zxh.core.exception.GlobalException;
import com.zxh.core.exception.LoginException;
import com.zxh.core.util.Constant;

import net.sf.json.JSONObject;

@Component
public class ExceptionGlobalResovler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) {
		boolean ajax = isAjax(request);
		ModelAndView modelAndView = new ModelAndView();
		if(ajax){
			if(e instanceof GlobalException){
				processAjaxException(response,new ResponseObj(Constant.StatusCommon,e.getMessage()));
			}else if(e instanceof LoginException){
				
			}else{
				processAjaxException(response, new ResponseObj(Constant.StatusErrorSystem));
				e.printStackTrace();
			}
		}else{
			modelAndView = new ModelAndView("error");
			if(e instanceof GlobalException){
				modelAndView.addObject("msg", e.getMessage());
			}else if(e instanceof LoginException){
				modelAndView = new ModelAndView("login");
			}else{
				modelAndView.addObject("msg", Constant.getStatusMsg(Constant.StatusErrorSystem));
				e.printStackTrace();
			}
		}
		return modelAndView;
	}
	private boolean isAjax(HttpServletRequest request){
		String str = request.getHeader("X-Requested-With");
		if(str==null||str.equals("")){return false;}
		return true;
	}
	private void processAjaxException(HttpServletResponse response,ResponseObj responseObj){
		try {
			PrintWriter writer = response.getWriter();
			writer.print(JSONObject.fromObject(responseObj));
			writer.flush();
			writer.close();
		} catch (IOException e1) {e1.printStackTrace();}
	}
}
