package com.tdhz.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件
 * @author TD-PC
 *
 */
public class Cfg {
//	 private static Properties properties_dev = new Properties();
	    
	    private static Properties properties = new Properties();
	    
	    static {
	        try {
	        	properties.load(Cfg.class.getClassLoader().getResourceAsStream(
	                    "servers.properties"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static String getConfig(String key) {
	        return (String) properties.get(key);
	    }
	    public static void main(String[] args) {
			String domainname = Cfg.getConfig("domainname");
			System.out.println(domainname);
		}
}