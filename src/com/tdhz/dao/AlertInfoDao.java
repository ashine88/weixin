package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.AlertInfo;
import com.tdhz.util.PageBean;

public interface AlertInfoDao {

	public List<AlertInfo> findAll(String onday, PageBean pe);
	
	public List<AlertInfo> findAll(String onday);
	
	public long findAlertCount(String onday);

	public AlertInfo findById(Integer alert_info_id);

	public void updateAlert(AlertInfo alertInfo);

	public List<Object[]> findAlertByAss(String sql);
}
