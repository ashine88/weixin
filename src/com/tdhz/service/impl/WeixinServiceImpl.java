package com.tdhz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tdhz.dao.WeixinDao;
import com.tdhz.pojo.Weixin;
import com.tdhz.service.WeixinService;

@Service
public class WeixinServiceImpl implements WeixinService {
	@Resource
	private WeixinDao weixinDao;
	@Override
	public Weixin selectOpenid(String openid) {
		Weixin weixin = new Weixin();
		List<Weixin> weixinlist = weixinDao.selectOpenid(openid);
		if(weixinlist.size()<0){
			weixin =null;
		}else{
			for(int i = 0;i<weixinlist.size();i++){
				weixin = weixinlist.get(i);
			}
		}
		
		
		return weixin;
	}

	@Override
	public void updateWeixin(Weixin weixin) {
		// TODO Auto-generated method stub
		weixinDao.updateWeixin(weixin);
	}

	@Override
	public void addWeixin(Weixin weixin) {
		// TODO Auto-generated method stub
		weixinDao.addWeixin(weixin);
	}

	@Override
	public void deleteweixin(String openid) {
		weixinDao.deleteWeixin(openid);
		
	}

}
