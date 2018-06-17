package com.tdhz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdhz.dao.UserRoleDao;
import com.tdhz.pojo.Users_Role;
import com.tdhz.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements  UserRoleService{
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public Users_Role getUserRole(Integer user_id) {
		Users_Role user_role = new Users_Role();
		List<Users_Role> ulist =userRoleDao.getUserRole(user_id);
		if(ulist.size()>0){			
			user_role = ulist.get(0);					
		}else{
			user_role = null;
		}
		return user_role; 
	}
	

}
