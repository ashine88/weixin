package com.tdhz.service;

import com.tdhz.pojo.SNSUserInfo;

public interface SNSUserInfoService {

	//添加微信信息
	public void insertSNSUserInfo(SNSUserInfo sns);
	
	//更新微信信息
	public void updateSNSUserInfo(SNSUserInfo sns);
	
	//按照openId查询微信信息
	public SNSUserInfo findByOpenid(String openId);
	
	//按照ID查询微信信息
	public SNSUserInfo findById(Integer siid);
}
