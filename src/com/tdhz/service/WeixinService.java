package com.tdhz.service;

import com.tdhz.pojo.Weixin;

public interface WeixinService {
	public Weixin selectOpenid(String openid);
	
	public void updateWeixin(Weixin weixin);
	
	public void addWeixin(Weixin weixin);

	public void deleteweixin(String openid);
}
