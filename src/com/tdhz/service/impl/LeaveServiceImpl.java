package com.tdhz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tdhz.dao.LeaveDao;
import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;
import com.tdhz.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {
	@Resource
	private LeaveDao leaveDao;

	@Override
	public void addPersonLeave(PersonLeave personLeave) {
		
		leaveDao.addPersonLeave(personLeave);
	}

	@Override
	public void addClassLeave(ClassLeave classLeave) {
		
		leaveDao.addClassLeave(classLeave);
	}

	@Override
	public List<User_Dept> selectAllClass(Integer user_id) {
		User_Dept user_dept = new User_Dept();
		user_dept.setUser_id(user_id);
		List<User_Dept> udlist = leaveDao.seletByUserId(user_dept);
		if(udlist.size()>=0){
			for(int i=0;i<udlist.size();i++){				
				User_Dept ud = udlist.get(i);
				Dept dept = leaveDao.fingById(ud.getDept_id().getDeptId());
				ud.setDept_id(dept);;
				udlist.set(i, ud);
			}			
		}
//		String a =   "dept_id.fullName".replace("", "")
		return udlist;
	}

}
