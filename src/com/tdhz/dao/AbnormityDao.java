package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.util.PageBean;

public interface AbnormityDao {

	public List<AbnormityInfo> findLaterInfo(String onday, PageBean pe);
	
	public long findLaterCount(String onday);
	 
	public List<AbnormityInfo> findCarAbn(String onday, PageBean pe);
	
	public long findCarAbnCount(String onday);

	public List<Object[]> findCarAbn(String sql);

	public List<Tbcha_channeltg_14> findLaterInfo(String string, String onday,
                                                  PageBean pe);

	public List<Object[]> findLaterBackByAss(String sql);
}
