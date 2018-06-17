package com.tdhz.pojo;

public class RankInfo {
	
	private Integer userId;		//管理员ID
	private String username;	//管理员姓名
	private String rolename;	//管理员角色名称
	private String deptname;	//负责部门名称
	private String fulldeptname;	//负责部门全称
	private Integer personAllCount;	//所有预警人数
	private Integer personNotCount;	//以处理人数
	private String deal;	//处理率
	private Integer rank;	//排名
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getFulldeptname() {
		return fulldeptname;
	}
	public void setFulldeptname(String fulldeptname) {
		this.fulldeptname = fulldeptname;
	}
	public Integer getPersonAllCount() {
		return personAllCount;
	}
	public void setPersonAllCount(Integer personAllCount) {
		this.personAllCount = personAllCount;
	}
	public Integer getPersonNotCount() {
		return personNotCount;
	}
	public void setPersonNotCount(Integer personNotCount) {
		this.personNotCount = personNotCount;
	}
	public String getDeal() {
		return deal;
	}
	public void setDeal(String deal) {
		this.deal = deal;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	


}
