package com.tdhz.service;

import java.util.List;

import com.tdhz.pojo.AlertInfo;
import com.tdhz.util.PageBean;

public interface AlertInfoService {

	public List<AlertInfo> findAlertByAss(String onday, PageBean pe, Integer userid);

	public AlertInfo findById(Integer alert_info_id);

	public void updateAler(AlertInfo alertInfo);

	public List<AlertInfo> findAlertByChief(String onday, PageBean pe);

	public List<Object[]> findAlertByAss(String onday, Integer userid);

	public List<Object[]> findAlertByAss2(String onday, Integer userid);

	public List<Object[]> findAlertByChief(String onday);
}
