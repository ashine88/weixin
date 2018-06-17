package com.tdhz.pojo;
/**
 * 辅导员和负责班级关系表
 * @author TD-PC
 *
 */
public class User_Dept {
	

	public Dept getDept_id() {
		return dept_id;
	}
	public void setDept_id(Dept dept_id) {
		this.dept_id = dept_id;
	}
	private Integer id;
	private Integer user_id;
	
	
	private Dept  dept_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	

}
