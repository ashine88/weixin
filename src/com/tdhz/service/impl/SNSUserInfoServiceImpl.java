package com.tdhz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdhz.dao.SNSUserInfoDao;
import com.tdhz.pojo.SNSUserInfo;
import com.tdhz.service.SNSUserInfoService;

@Service
public class SNSUserInfoServiceImpl implements SNSUserInfoService{

	@Autowired
	private SNSUserInfoDao snsUserInfoDao;
	
	@Override
	public void insertSNSUserInfo(SNSUserInfo sns) {
		// TODO Auto-generated method stub
		snsUserInfoDao.insertSNSUserInfo(sns);
	}

	@Override
	public void updateSNSUserInfo(SNSUserInfo sns) {
		// TODO Auto-generated method stub
		snsUserInfoDao.updateSNSUserInfo(sns);
	}

	@Override
	public SNSUserInfo findByOpenid(String openId) {
		// TODO Auto-generated method stub
		SNSUserInfo sns = new SNSUserInfo();
		List<SNSUserInfo> slist =  snsUserInfoDao.findByOpenid(openId);
		if(slist.size()>0){
			for(int i = 0;i<slist.size();i++){
				sns=slist.get(i);
			}			
		}else{
			sns = null;
		}
		return sns;
	}

	@Override
	public SNSUserInfo findById(Integer siid) {
		// TODO Auto-generated method stub
		return snsUserInfoDao.findById(siid);
	}

}
