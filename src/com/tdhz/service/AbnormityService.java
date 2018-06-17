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
}
