package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;

public interface LeaveDao {

	//个人请假
	public void addPersonLeave(PersonLeave personLeave);
	
	//班级休假
	public void addClassLeave(ClassLeave classLeave);

	public List<User_Dept> seletByUserId(User_Dept user_dept);
	
	//
	public Dept fingById(Integer dept_id);
}
