package com.tdhz.dao;

import java.util.List;

import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.dto.KqDetailRespDTO;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PerInfo;
import com.tdhz.util.Page;

public interface PerInfoDao {
	//按照id查询
	public PerInfo findById(Integer piid);
	//按照姓名查找
	public List<PerInfo> findOnePerInfo(String piName);
	//更新人员信息
	public void updatePer(PerInfo perInfo);
	//按照ID查询部门
	public Dept findpDept(Integer pdept);
	//按照部门名称查找
	public List<Dept> findOneDept(String deptName);

	List<PerInfo> getByAss(Integer userId, Integer deptId);


	int getCountByAss(Integer userId, Integer deptId);
	int getCountBySg(Integer userId, Integer roomId);
	List<PerInfo> getBySg(Integer userId, Integer roomId);

	/**
	 * 后勤后获取总人数
	 * @param reqDTO
	 * @param page
	 * @return
	 */
	Page<KqDetailItemDTO> getPersonLeaveDetailBySg(KqDetailReqDTO reqDTO, Page page);

	/**
	 * 行政口获取总人数
	 * @param reqDTO
	 * @param page
	 * @return
	 */
	Page<KqDetailItemDTO> getPersonLeaveDetailByAss(KqDetailReqDTO reqDTO, Page page);

}
