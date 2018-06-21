package com.tdhz.service;


import java.util.List;

import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;
import com.tdhz.util.Page;

public interface LeaveService {

	//个人请假
	public void addPersonLeave(PersonLeave personLeave);
	
	//班级休假
	public void addClassLeave(ClassLeave classLeave);

	//查询辅导员负责的所有班级
	public List<User_Dept> selectAllClass(Integer user_id);


	public List<PersonLeave> getPersonLeaveByAss(Integer userId, Integer deptId, String startTime, String endTime, List<String> leaveTypes);
	public List<PersonLeave> getPersonLeaveBySg(Integer userId, Integer roomId, String startTime, String endTime, List<String> leaveTypes);
	public int getQJBySg(Integer userId, Integer roomId, String startTime, String endTime);
	public int getQJByAss(Integer userId, Integer deptId, String startTime, String endTime);

	public int getSXBySg(Integer userId, Integer roomId, String startTime, String endTime);
	public int getSXByAss(Integer userId, Integer deptId, String startTime, String endTime);

	Page<KqDetailItemDTO> getQJDetailByAss(KqDetailReqDTO reqDTO, Page page);
	Page<KqDetailItemDTO> getSXDetailByAss(KqDetailReqDTO reqDTO, Page page);


	Page<KqDetailItemDTO> getQJDetailBySg(KqDetailReqDTO reqDTO, Page page);
	Page<KqDetailItemDTO> getSXDetailBySg(KqDetailReqDTO reqDTO, Page page);

}
