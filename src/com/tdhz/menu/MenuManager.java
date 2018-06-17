package com.tdhz.menu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tdhz.pojo.AccessToken;
import com.tdhz.util.Cfg;
import com.tdhz.util.URLEncodeUTF8;
import com.tdhz.util.WeixinUtil;


/**
 * 菜单管理器类
 * @author TD-PC
 *
 */
public class MenuManager {
private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	public static Integer buildMenu() {
		Integer result = null ;
        // 第三方用户唯一凭证
        String appId = Cfg.getConfig("appid");
        // 第三方用户唯一凭证密钥
        String appSecret = Cfg.getConfig("appsecret");

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
            result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (0 == result)
                log.info("菜单创建成功！");
            else
                log.info("菜单创建失败，错误码：" + result);
        }else{
        	log.info("appid或者appsecret错误");
        	result = null;
        }
        return result;
    }
	
	/**
     * 组装菜单数据
     */
    private static Menu getMenu() {
    	Button btn31 = new Button();
        btn31.setName("本地域名");
        btn31.setType("view");
        btn31.setUrl("http://192.168.8.35/oy/login.jsp");

        Button btn32 = new Button();
        btn32.setName("管理入口");
        btn32.setType("view");
        btn32.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?"
        + "appid="+Cfg.getConfig("appid")
//        + "&redirect_uri=http%3A%2F%2F"+Cfg.getConfig("domainname")+"%2Fweixin1%2FoauthServlet"
        + "&redirect_uri="+URLEncodeUTF8.getUrlEncodeUTF8("http://"+Cfg.getConfig("domainname")+"/weixin1/oauthServlet")
        + "&response_type=code"
        + "&scope=snsapi_userinfo"
        + "&state=STATE#wechat_redirect"); 
//        btn32.setUrl("http://www.dgzwl.cn/eems/userAction_toLogin.action");
        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。%3A8091
         */
      
//        ComplexButton mainBtn3 = new ComplexButton();
//        mainBtn3.setName("高校管理");
//        //mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });
//        mainBtn3.setSub_button(new Button[]{btn32});
        
        /**
         * 封装整个菜单 
         */
        Menu menu = new Menu();
        // menu.setButton(new Button[] {mainBtn3 });
        menu.setButton(new Button[] { btn31, btn32 });
        return menu;
    }
    
    public static void main(String[] args) {
    	// 第三方用户唯一凭证
        String appId = Cfg.getConfig("appid");
        // 第三方用户唯一凭证密钥
        String appSecret = Cfg.getConfig("appsecret");

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
           int  result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (0 == result)
                log.info("菜单创建成功！");
            else
                log.info("菜单创建失败，错误码：" + result);
        }
	}
}
