package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.User_Dept;

public interface UsersDeptDao {
	
	public List<User_Dept> findByUserId(Integer userid);
	

}
