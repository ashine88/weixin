package com.tdhz.dao;

import java.util.List;

import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.dto.KqMsgDTO;
import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;
import com.tdhz.util.Page;

public interface LeaveDao {

	//个人请假
	public void addPersonLeave(PersonLeave personLeave);
	
	//班级休假
	public void addClassLeave(ClassLeave classLeave);

	public List<User_Dept> seletByUserId(User_Dept user_dept);
	
	//
	public Dept fingById(Integer dept_id);


	public List<PersonLeave> getPersonLeaveByAss(Integer userId, Integer deptId, String startTime, String endTime, List<String> leaveTypes);
	public List<PersonLeave> getPersonLeaveBySg(Integer userId, Integer roomId, String startTime, String endTime, List<String> leaveTypes);
	public int getQJBySg(Integer userId, Integer roomId, String startTime, String endTime);
	public int getQJByAss(Integer userId, Integer deptId, String startTime, String endTime);

	public int getSXBySg(Integer userId, Integer roomId, String startTime, String endTime);
	public int getSXByAss(Integer userId, Integer deptId, String startTime, String endTime);


	Page<KqDetailItemDTO> getPersonLeaveDetailByAss(KqDetailReqDTO reqDTO, Page page, List<String> leaveTypes);


	Page<KqDetailItemDTO> getPersonLeaveDetailBySg(KqDetailReqDTO reqDTO, Page page, List<String> leaveTypes);



}
