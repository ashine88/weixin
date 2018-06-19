package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.User_Dept;

public interface UsersDeptDao {
	
	public List<User_Dept> findByUserId(Integer userid);

	/**
	 * 获取所有的班级
	 * @param userId  用户id
	 * @return
	 */
	List<User_Dept> getBJ(Integer userId);
	

}
