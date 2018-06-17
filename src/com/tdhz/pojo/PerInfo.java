package com.tdhz.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 人员信息表
 * @author TD-PC
 *
 */
public class PerInfo {
	
	
	@Override
	public String toString() {
		return "PerInfo [piid=" + piid + ", piName=" + piName
				+ ", lastRoomTGDT=" + lastRoomTGDT + ", lastRoomTGINOUT="
				+ lastRoomTGINOUT + ", deptName=" + deptName + ", roomName="
				+ roomName + "]";
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Date getLastRoomTGDT() {
		return lastRoomTGDT;
	}
	public void setLastRoomTGDT(Date lastRoomTGDT) {
		this.lastRoomTGDT = lastRoomTGDT;
	}
	public String getLastRoomTGINOUT() {
		return lastRoomTGINOUT;
	}
	public void setLastRoomTGINOUT(String lastRoomTGINOUT) {
		this.lastRoomTGINOUT = lastRoomTGINOUT;
	}
	public Set<Tbcha_channeltg_14> getTbcha_channeltgs() {
		return tbcha_channeltgs;
	}
	public void setTbcha_channeltgs(Set<Tbcha_channeltg_14> tbcha_channeltgs) {
		this.tbcha_channeltgs = tbcha_channeltgs;
	}
	public Set<AbnormityInfo> getAbnormityInfos() {
		return abnormityInfos;
	}
	public void setAbnormityInfos(Set<AbnormityInfo> abnormityInfos) {
		this.abnormityInfos = abnormityInfos;
	}
	public Set<AlertInfo> getAlertInfos() {
		return alertInfos;
	}
	public void setAlertInfos(Set<AlertInfo> alertInfos) {
		this.alertInfos = alertInfos;
	}
	public String getPdeptName() {
		return pdeptName;
	}
	public void setPdeptName(String pdeptName) {
		this.pdeptName = pdeptName;
	}
	private Integer piid ;	//主键 
	private String piName;	//人员姓名
	
	private String credNo1; //证件号
	private Dept dept;  //部门
	private Room room;	//房间
	
	private Date lastRoomTGDT; //最后通过宿舍楼时间
	
	private String lastRoomTGINOUT;//最后通过宿舍的进出方向
	private String deptName;
	private String roomName;
	
	private String pdeptName;//部门名称
	
	private Set<AlertInfo> alertInfos = new HashSet<AlertInfo>();
	
	private Set<AbnormityInfo> abnormityInfos = new HashSet<AbnormityInfo>();
	
	private Set<Tbcha_channeltg_14> tbcha_channeltgs = new HashSet<Tbcha_channeltg_14>();
	
	
	public Integer getPiid() {
		return piid;
	}
	public void setPiid(Integer piid) {
		this.piid = piid;
	}
	public String getPiName() {
		return piName;
	}
	public void setPiName(String piName) {
		this.piName = piName;
	}
	
	public String getCredNo1() {
		return credNo1;
	}
	public void setCredNo1(String credNo1) {
		this.credNo1 = credNo1;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
