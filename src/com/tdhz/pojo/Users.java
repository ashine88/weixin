package com.tdhz.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统管理员表
 * @author TD-PC
 *
 */
public class Users {
	
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getCon_phone_no() {
		return con_phone_no;
	}
	public void setCon_phone_no(String con_phone_no) {
		this.con_phone_no = con_phone_no;
	}
	public Set<Weixin> getWeixin() {
		return weixin;
	}
	public void setWeixin(Set<Weixin> weixin) {
		this.weixin = weixin;
	}
	private Integer user_id;//用户ID主键
	private String user_code; //用户账号
	private String user_name;//用户姓名
	private String user_password;//用户密码
	private String enable;//启用标识  Y/N
	private String con_phone_no;//电话
	
	
	private Set<Weixin> weixin = new HashSet<Weixin>(); //一个用户对应多个微信
	
	private String roleName;//角色名称
	private Integer roleId; //角色ID
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
	
	
}
