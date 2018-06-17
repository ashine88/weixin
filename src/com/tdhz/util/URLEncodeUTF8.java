package com.tdhz.util;

import java.io.UnsupportedEncodingException;

public class URLEncodeUTF8 {
	/**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String getUrlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
public static void main(String[] args) {
	String url = "http://"+Cfg.getConfig("domainname")+"/weixin1/oauthServlet";
	System.out.println(getUrlEncodeUTF8(url));
}
}
