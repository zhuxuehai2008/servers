package com.zxh.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 随机码生成.
 * 
 * @author zhanghai
 * @version v1.0 2012-8-20
 */
public class RandomCode {

    /**
     * //随机码关键字.
     */
    public static final String RANDOM_CODE_KEY = "RANDOM_CODE_KEY";

    /**
     * 随机取得一个字体.
     * 
     * @param random
     *            random 随机数
     * @return Font 返回一个新字体
     */
    private synchronized Font getsFont(Random random) {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 返回一个随机颜色.
     * 
     * @param fc
     *            fc 随机数
     * @param bc
     *            bc 随机数
     * @param random
     *            random 随机数
     * @return Color 返回一个新颜色
     */
    synchronized Color getRandColor(int fc, int bc, Random random) {
        int fcc = fc;
        if (fc > 255) {
            fcc = 255;
        }
        int bcc = bc;
        if (bc > 255) {
            bcc = 255;
        }
        int r = fcc + random.nextInt(bcc - fcc - 6);
        int g = fcc + random.nextInt(bcc - fcc - 4);
        int b = fcc + random.nextInt(bcc - fcc - 8);
        return new Color(r, g, b);
    }

    /**
     * 生成随机数图片.
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @param response
     *            {@link HttpServletResponse}
     * @throws Exception .
     */
    public synchronized void getRandcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.setProperty("java.awt.headless", "true");
        HttpSession session = request.getSession();
        // 设置图片大小
        int width = 54, height = 18;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        ServletOutputStream  os = response.getOutputStream();
        Random random = new Random();
        // 设定边框
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 16));
        g.setColor(getRandColor(111, 133, random));
        // 产生随机线
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(13);
            int yl = random.nextInt(15);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 产生随机点
        g.setColor(getRandColor(130, 150, random));
        // 产生5个随机数
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            g.setFont(getsFont(random));
            g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
            String rand = String.valueOf(getRandomString(random.nextInt(10)));
            sRand += rand;
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(rand, 12 * i, 12);
        }

        session.removeAttribute(RANDOM_CODE_KEY);
        session.setAttribute(RANDOM_CODE_KEY, sRand);
        g.dispose();
        ImageIO.write(image, "JPEG", os);
        os.flush();   
        os.close();
    }

    /**
     * .
     * 
     * @param num
     *            .
     * @return string
     */
    public synchronized String getRandomString(int num) {
        String randstring = "0123456789";
        //String randstring = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(randstring.charAt(num));
    }

}
