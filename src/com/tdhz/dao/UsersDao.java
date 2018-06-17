package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.Users;

public interface UsersDao {
	
	public List<Users> selectOper(String user_code, String user_password);

	public List<Users> selectOpenid(String openid);

	public void updateUser(Users user);

	public void addUser(Users user);
	
	public List<Users> getUserByRoleNo(Integer role);
	
	public List<Users> findAllUsers();

	public List<Object[]> findAllAss(String sql);//查看所有辅导员

	public List<Object[]> findAllAssByWX(String sql);
}
