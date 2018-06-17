package com.tdhz.service;

import java.util.List;

import com.tdhz.pojo.Users;

public interface UsersService {
	
	public Users  login(String operName, String operPass);

	public Users selectOpenid(String openid);

	public void updateUser(Users user);

	public void addUser(Users user);

	public List<Users> findAllUsers();

	public List<Object[]> findAllAss(); //查看所有的辅导员
	public List<Object[]> findAllAssByWx(); //查看所有的辅导员
	

}
