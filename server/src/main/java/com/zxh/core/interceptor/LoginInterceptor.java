package com.zxh.core.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("completion");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		session.setAttribute("backUrl", requestURI);
		System.out.println(getLimitDefinition());
		System.out.println(requestURI);
		System.out.println("@!!!!!!!"+contextPath);
		//request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
		String parameter = request.getParameter("flag");
		if(null!=parameter){response.sendRedirect(request.getContextPath()+"");return false;}
		//System.out.println("qqqqqqq");
		return true;//super.preHandle(request, response, handler);
	}
	private String limitDefinition;
	public String getLimitDefinition() {
		return limitDefinition;
	}
	public HashMap<String,String> getLimitDefinitionMap(String contextPath){
		return null;
	}
	public void setLimitDefinition(String limitDefinition) {
		this.limitDefinition = limitDefinition;
	}
}