package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.Users_Role;

public interface UserRoleDao {
	
	//根据人员ID查找对应角色id
	public  List<Users_Role> getUserRole(Integer user_id);

}
