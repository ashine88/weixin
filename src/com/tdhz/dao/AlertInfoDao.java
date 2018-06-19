package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.AlertInfo;
import com.tdhz.util.PageBean;

public interface AlertInfoDao {

	public List<AlertInfo> findAll(String onday, PageBean pe);
	
	public List<AlertInfo> findAll(String onday);
	
	public long findAlertCount(String onday);

	public AlertInfo findById(Integer alert_info_id);

	public void updateAlert(AlertInfo alertInfo);

	public List<Object[]> findAlertByAss(String sql);

	/**
	 * 后勤口
	 * @param userId
	 * @param roomId
	 * @param startTime
	 * @param endTime
	 * @param alertType
	 * @return
	 */
	List<AlertInfo> findAlertBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer alertType);

	long findAlertCountBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer alertType);


	/**
	 * 行政口
	 * @param userId
	 * @param deptId
	 * @param startTime
	 * @param endTime
	 * @param alertType
	 * @return
	 */
	List<AlertInfo> findAlertByAss(Integer userId, Integer deptId, String startTime, String endTime, Integer alertType);

	int findAlertCountByAss(Integer userId, Integer deptId, String startTime, String endTime, Integer alertType);


}
