package com.tdhz.service;

import java.util.List;

import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.pojo.AlertInfo;
import com.tdhz.util.Page;
import com.tdhz.util.PageBean;

public interface AlertInfoService {

	public List<AlertInfo> findAlertByAss(String onday, PageBean pe, Integer userid);

	public AlertInfo findById(Integer alert_info_id);

	public void updateAler(AlertInfo alertInfo);

	public List<AlertInfo> findAlertByChief(String onday, PageBean pe);

	public List<Object[]> findAlertByAss(String onday, Integer userid);

	public List<Object[]> findAlertByAss2(String onday, Integer userid);

	public List<Object[]> findAlertByChief(String onday);


	/**
	 * 获取未出报警
	 * @param userId
	 * @param deptId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getWcByAss(Integer userId, Integer deptId, String startTime, String endTime);
	int getWcBySg(Integer userId, Integer roomId, String startTime, String endTime);


	Page<KqDetailItemDTO> getWcDetailByAss(KqDetailReqDTO reqDTO, Page page);
	Page<KqDetailItemDTO> getWcDetailBySg(KqDetailReqDTO reqDTO, Page page);

	/**
	 * 获取未归报警
	 * @param userId
	 * @param deptId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getWgByAss(Integer userId, Integer deptId, String startTime, String endTime);
	int getWgBySg(Integer userId, Integer roomId, String startTime, String endTime);

	Page<KqDetailItemDTO>  getWgDetailByAss(KqDetailReqDTO reqDTO, Page page);
	Page<KqDetailItemDTO>  getWgDetailBySg(KqDetailReqDTO reqDTO, Page page);
	/**
	 * 获取在校预警
	 * @param userId
	 * @param deptId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getZxByAss(Integer userId, Integer deptId, String startTime, String endTime);

	int getZxBySg(Integer userId, Integer roomId, String startTime, String endTime);
	Page<KqDetailItemDTO>  getZxDetailByAss(KqDetailReqDTO reqDTO, Page page);
	Page<KqDetailItemDTO>  getZxDetailBySg(KqDetailReqDTO reqDTO, Page page);

}
