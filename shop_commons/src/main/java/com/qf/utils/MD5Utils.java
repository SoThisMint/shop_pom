package com.qf.utils;

import com.qf.aop.IsLogin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/17 19:24
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class MD5Utils {

    /**
     * Encodes a string 2 MD5
     *
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String toMD5(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String s = toMD5("1");
        System.out.println(s);
    }


}
