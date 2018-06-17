package com.tdhz.pojo;
/**
 * 人员角色关系表
 * @author TD-PC
 *
 */
public class Users_Role {

	private Integer id;//id
	private Integer role_id;//角色ID
	private Integer user_id; //人员ID
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
	
}
