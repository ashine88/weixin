package com.tdhz.pojo;

import java.util.Date;

/**
 * 宿舍管理员操作记录表
 * @author TD-PC
 *
 */
public class UserOperaLog {
	
	private Integer id;
	private String username;  //宿管员名称
	private Date operadate;  //操作时间
	private String data; //操作记录
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getOperadate() {
		return operadate;
	}
	public void setOperadate(Date operadate) {
		this.operadate = operadate;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
