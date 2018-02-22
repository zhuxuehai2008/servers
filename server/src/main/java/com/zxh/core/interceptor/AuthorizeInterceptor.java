package com.zxh.core.interceptor;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zxh.core.entity.Pair;
import com.zxh.core.entity.User;
import com.zxh.core.exception.GlobalException;

public class AuthorizeInterceptor extends HandlerInterceptorAdapter{

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
		ArrayList<Pair<String,String>> limitDefinitionList = getLimitDefinitionMap(contextPath);
		System.out.println(getLimitDefinition());
		System.out.println(requestURI);
		System.out.println("@!!!!!!!"+contextPath);
		System.out.println(limitDefinitionList);
		boolean isMatch = false;
		Pair<String, String> pair = null;
		for(int i=0;i<limitDefinitionList.size();i++){
			pair = limitDefinitionList.get(i);
			isMatch = Pattern.matches(pair.key, requestURI);
			if(isMatch){
				if(pair.value.equals("authc")){
					
					System.out.println("authc");
				}else if(pair.value.equals("user")){
					User attribute = (User)request.getSession().getAttribute("user");
					System.out.println("user");
					if(null!=attribute){
						return true;
					}else{
						Cookie cookie = new Cookie("backURL", requestURI);
						response.addCookie(cookie);
						throw new GlobalException("请登录");
					}
				}else if(pair.value.equals("anon")){
					System.out.println("anon");
					return true;
				}else{
					System.err.println("认证类型未知：");
				}
				break;
			}
		}
		//request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
		String parameter = request.getParameter("flag");
		if(null!=parameter){response.sendRedirect(request.getContextPath()+"");return false;}
		return true;//super.preHandle(request, response, handler);
	}
	private String limitDefinition;
	public String getLimitDefinition() {
		return limitDefinition;
	}
	public ArrayList<Pair<String,String>> getLimitDefinitionMap(String contextPath){
		String[] split = getLimitDefinition().split("\n");
		ArrayList<Pair<String,String>> result = new ArrayList<Pair<String,String>>();
		for(int i=0;i<split.length;i++){
			if(split[i].trim()==null||split[i].indexOf("=")<0||split[i].trim().equals("")){continue;}
			String[] arr = split[i].trim().split("=");
			Pair<String, String> pair = new Pair<String ,String>(contextPath+arr[0].trim(), arr[1].trim());
			result.add(pair);
		}
		return result;
	}
	public void setLimitDefinition(String limitDefinition) {
		this.limitDefinition = limitDefinition;
	}
	public static void  main(String[] args){
		String pattern = "^runoob.*";
		boolean isMatch = Pattern.matches(pattern, "aarunoobsdfsdf");
		System.out.println(isMatch);
	}      
	 
}
