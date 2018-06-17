package com.tdhz.service;

import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PerInfo;

public interface PerInfoService {
	//按照ID查找人员
	public PerInfo findById(Integer piid);
	//按照姓名查询
	public PerInfo findOnePerInfo(String piName);
	//更新人员信息
	public void updatePer(PerInfo perInfo);
	//按照ID查询部门
	public Dept findpDept(Integer pdept);
	//按照部门名称查询
	public Dept findOneDept(String deptName);
}
