package com.tdhz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.util.CommonUtil;
import com.tdhz.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tdhz.dao.LeaveDao;
import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;
import com.tdhz.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {
	public static final Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);
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


	@Override
	public List<PersonLeave> getPersonLeaveByAss(Integer userId, Integer deptId, String startTime, String endTime, List<String> leaveTypes) {
		return leaveDao.getPersonLeaveByAss(userId, deptId, startTime, endTime, leaveTypes);
	}

	@Override
	public List<PersonLeave> getPersonLeaveBySg(Integer userId, Integer roomId, String startTime, String endTime, List<String> leaveTypes) {
		return leaveDao.getPersonLeaveBySg(userId, roomId, startTime, endTime, leaveTypes);
	}

	@Override
	public int getQJBySg(Integer userId, Integer roomId, String startTime, String endTime) {
		return leaveDao.getQJBySg(userId, roomId, startTime, endTime);
	}

	@Override
	public int getQJByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		return leaveDao.getQJByAss(userId, deptId, startTime, endTime);
	}

	@Override
	public int getSXBySg(Integer userId, Integer roomId, String startTime, String endTime) {
		return leaveDao.getSXBySg(userId, roomId, startTime, endTime);
	}

	@Override
	public int getSXByAss(Integer userId, Integer roomId, String startTime, String endTime) {
		return leaveDao.getSXByAss(userId, roomId, startTime, endTime);
	}


	@Override
	public Page<KqDetailItemDTO> getQJDetailByAss(KqDetailReqDTO reqDTO, Page page) {
		logger.info("【行政口】获取请假的明细");
		//TODO 确定实习的leavetype
		List<String> leaveTypes = CommonUtil.getSxLeaveTypes();
		page = leaveDao.getPersonLeaveDetailByAss(reqDTO, page, leaveTypes);
		return page;
	}

	@Override
	public Page<KqDetailItemDTO> getSXDetailByAss(KqDetailReqDTO reqDTO, Page page) {
		logger.info("【行政口】获取实习的明细");
		//TODO 确定实习的leavetype
		List<String> leaveTypes = CommonUtil.getSxLeaveTypes();
		page = leaveDao.getPersonLeaveDetailByAss(reqDTO, page, leaveTypes);
		return page;
	}

	@Override
	public Page<KqDetailItemDTO> getQJDetailBySg(KqDetailReqDTO reqDTO, Page page) {
		logger.info("【后勤口】获取请假的明细");
		//TODO 确定实习的leavetype
		List<String> leaveTypes = CommonUtil.getQjLeaveTypes();
		page = leaveDao.getPersonLeaveDetailBySg(reqDTO, page, leaveTypes);
		return page;
	}

	@Override
	public Page<KqDetailItemDTO> getSXDetailBySg(KqDetailReqDTO reqDTO, Page page) {
		logger.info("【行政口】获取实习的明细");
		//TODO 确定实习的leavetype
		List<String> leaveTypes = CommonUtil.getSxLeaveTypes();
		page = leaveDao.getPersonLeaveDetailBySg(reqDTO, page, leaveTypes);
		return page;
	}


}
