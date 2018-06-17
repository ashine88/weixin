package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.SNSUserInfo;

public interface SNSUserInfoDao {

	//添加微信信息
	public void insertSNSUserInfo(SNSUserInfo sns);
	
	//更新微信信息
	public void updateSNSUserInfo(SNSUserInfo sns);
	
	//按照openId查询微信信息
	public List<SNSUserInfo> findByOpenid(String openId);
	
	//按照ID查询微信信息
	public SNSUserInfo findById(Integer siid);
	
	
	
}
