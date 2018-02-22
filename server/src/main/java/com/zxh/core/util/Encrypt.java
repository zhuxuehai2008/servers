package com.zxh.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encrypt {
	/**
	 * salt使用后不要更改,否则密码不能匹配以保存的密码
	 */
	private final static byte[] salt = "helloZXH".getBytes();
	public static void pwdToMd5(String pwd) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		md.update(Encrypt.salt);
		md.update(pwd.getBytes());
		String digest = byteToHexString(md.digest());
		System.out.println(digest);
	}
    public static String byteToHexString(byte[] b) {   
        StringBuffer hexString = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
            String hex = Integer.toHexString(b[i] & 0xFF);   
            if (hex.length() == 1) {   
                hex = '0' + hex;   
            }   
            hexString.append(hex.toUpperCase());   
        }   
        return hexString.toString();   
    }  
	public static void main(String[] args) throws NoSuchAlgorithmException{
		Encrypt.pwdToMd5("123456");
	}
}
