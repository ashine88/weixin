package com.tdhz.service;

import java.util.List;

import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.util.PageBean;

public interface AbnormityService {

	public List<Tbcha_channeltg_14> findLaterInfo(String onday, PageBean pe, Integer userid);

	public List<AbnormityInfo> findAbnInfoByAss(String onday, PageBean pe, Integer userid);

	public List<AbnormityInfo> findLaterInfo(String onday, PageBean pe);

	public List<Object[]> findLaterInfoByAss(String onday, Integer userid);

	public List<Object[]> findAbnInfoByAss(String onday, Integer userid);


	public List<Object> findLaterCountByAss(Integer userId, Integer deptId, String startTime, String endTime);



	int findAbnormityCountByAss(Integer assId, Integer deptId, String startTime, String endTime, Integer abnType);

	/**
	 * 迟归
	 * @param assId
	 * @param deptId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int findCgCountByAss(Integer assId, Integer deptId, String startTime, String endTime);

	/**
	 * 未归
	 * @param assId
	 * @param deptId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int findWgCountByAss(Integer assId, Integer deptId, String startTime, String endTime);

	List<AbnormityInfo> findAbnormityBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer abnType);

	int findAbnormityCountBySg(Integer userId, Integer roomId, String startTime, String endTime ,Integer abnType);
	int findCgCountBySg(Integer userId, Integer roomId, String startTime, String endTime );

	int findWgCountBySg(Integer userId, Integer roomId, String startTime, String endTime );

	List<AbnormityInfo> findAbnormityByAss(Integer userId, Integer deptId, String startTime, String endTime, Integer abnType);


}
