package com.zxh.core.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxh.core.util.RandomCode;

//import org.apache.log4j.Logger;

/**
 * 验证码生成servlet.
 * 
 * @author 
 * @version v1.0 2012-8-17
 */
public class RandCodeServlet extends HttpServlet {
    /**
     * .
     */
    private static final long serialVersionUID = 1423591401028099267L;
    /**
     * 日 志对象.
     */
    /*private Logger logger = Logger.getLogger(this.getClass());*/

    /**
     * doGet.
     * 
     * @param request
     *            .
     * @param response
     *            .
     * 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);

    }

    /**
     * doPost.
     * 
     * @param request
     *            .
     * @param response
     *            .
     * 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //logger.info("开始生成图片");
        String str = "CP=CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR";
        response.addHeader("P3P", str);

        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        RandomCode rc = new RandomCode();

        try {
            rc.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
