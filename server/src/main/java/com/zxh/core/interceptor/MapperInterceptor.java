package com.zxh.core.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zxh.core.dao.BaseDao;

public class MapperInterceptor implements MethodInterceptor{
	private static final Log logger = LogFactory.getLog(MapperInterceptor.class);
	    public MapperInterceptor() {
	    }
	    @Resource
	    private BaseDao baseDao;
	    @Override
	    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
	    	Object result = null;
	        Method method = methodInvocation.getMethod();
	        String methodName = method.getName();
	        Class<?> cls = method.getDeclaringClass();
	        Object service = methodInvocation.getThis();
	        Object[] args = methodInvocation.getArguments();
	        try {    
	            result = methodInvocation.proceed();    
	         } catch (Exception e) {  
	        	 logger.error("mapperInterceptor\n"+e.getMessage(),e);  
	             throw e;  
	         }  
	         return result;
	    }
}
