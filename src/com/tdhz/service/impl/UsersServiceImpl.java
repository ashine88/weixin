package com.tdhz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tdhz.dao.UsersDao;
import com.tdhz.pojo.Users;
import com.tdhz.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Resource
	private UsersDao operDao;
	
	
	@Override
	public Users login(String operName,String operPass) {
		
		List<Users> operlist = operDao.selectOper(operName, operPass);
		Users oper = new Users();
		if(operlist.size()<1){
			oper =null;
		}
		for(int i = 0;i<operlist.size();i++){
			 oper = operlist.get(i);
		}
		return oper;
		
	}


	@Override
	public Users selectOpenid(String openid) {
		List<Users> operlist = operDao.selectOpenid(openid);
		Users oper = new Users();
		for(int i = 0;i<operlist.size();i++){
			 oper = operlist.get(i);
		}
		return oper;
	}


	@Override
	public void updateUser(Users user) {
		operDao.updateUser(user);
		
	}


	@Override
	public void addUser(Users user) {
		operDao.addUser(user);
		
	}


	@Override
	public List<Users> findAllUsers() {
		
		return operDao.findAllUsers();
	}


	@Override
	public List<Object[]> findAllAss() {
		StringBuilder sb = new StringBuilder();
		sb.append("select  u.user_id, u.user_name,r.role_name,d.DEPTNAME,d.FULLDEPTNAME ,u.con_phone_no from sys_user u left join sys_user_role ur on u.user_id = ur.user_id left join Sys_role r on ur.role_id = r.role_id left join sys_user_dept ud on u.user_id = ud.user_id left join TBCHA_DEPT d on ud.dept_id = d.DEPTID ");
		sb.append(" where r.role_id = 4 and  len(d.FULLDEPTNAME)-len(REPLACE(d.FULLDEPTNAME,'_',''))=3");
		String sql = sb.toString();
		return operDao.findAllAss(sql);
	}
	
	@Override
	public List<Object[]> findAllAssByWx() {
		StringBuilder sb = new StringBuilder();
		sb.append("select  u.user_id, u.user_name,r.role_name,u.con_phone_no from sys_user u left join sys_user_role ur on u.user_id = ur.user_id left join Sys_role r on ur.role_id = r.role_id ");
		sb.append(" where r.role_id = 4 ");
		String sql = sb.toString();
		return operDao.findAllAssByWX(sql);
	}

}
