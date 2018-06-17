package com.tdhz.service;


import java.util.List;

import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;

public interface LeaveService {

	//个人请假
	public void addPersonLeave(PersonLeave personLeave);
	
	//班级休假
	public void addClassLeave(ClassLeave classLeave);

	//查询辅导员负责的所有班级
	public List<User_Dept> selectAllClass(Integer user_id);
}
