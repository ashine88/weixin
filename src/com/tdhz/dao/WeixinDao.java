package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.Users;
import com.tdhz.pojo.Weixin;

public interface WeixinDao {

	public List<Weixin> selectOpenid(String openid);
	
	public void updateWeixin(Weixin weixin);
	
	public void addWeixin(Weixin weixin);
	
	public List<Weixin> selectByUser(Integer userid);
	
	public List<Users> getUserByRoleNo(Integer role);
	
	public void deleteWeixin(String openid);
}
