package com.tdhz.dao;

import com.tdhz.pojo.SendLog;

public interface SendLogDao {

	public void saveLog(SendLog log);
	
	public void update(SendLog log);
}
