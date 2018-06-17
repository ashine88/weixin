package com.tdhz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tdhz.pojo.SNSUserInfo;
import com.tdhz.pojo.WeixinOauth2Token;
import com.tdhz.util.Cfg;
import com.tdhz.util.CommonUtil;


public class OAuthServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
 
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        
        // 用户同意授权
        if (!"authdeny".equals(code)) {
        	// 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = CommonUtil.getOauth2AccessToken(Cfg.getConfig("appid"), Cfg.getConfig("appsecret"), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = CommonUtil.getSNSUserInfo(accessToken, openId);
          
            // 设置要传递的参数
           HttpSession session = request.getSession();
            session.setAttribute("snsUserInfo", snsUserInfo);
            session.setAttribute("state", state);
           
        }
        
        // 重定向到UsersAction    
        response.sendRedirect("userAction_checkOpenid.action");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
