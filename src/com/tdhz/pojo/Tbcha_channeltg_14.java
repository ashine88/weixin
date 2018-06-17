package com.tdhz.pojo;

import java.util.Date;

public class Tbcha_channeltg_14 {

	

	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	public PerInfo getPerInfo() {
		return perInfo;
	}

	public void setPerInfo(PerInfo perInfo) {
		this.perInfo = perInfo;
	}

	public Integer getTgId() {
		return tgId;
	}

	public void setTgId(Integer tgId) {
		this.tgId = tgId;
	}

	public Date getTgTime() {
		return tgTime;
	}

	public void setTgTime(Date tgTime) {
		this.tgTime = tgTime;
	}



	private Integer tgId;
	
	private Date tgTime;	
	
	private String inout;
	
	private PerInfo perInfo;
	

}
