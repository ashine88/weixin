package com.tdhz.pojo;
/**
 * 管理员和负责房间关系表
 * @author TD-PC
 *
 */
public class User_Area {
	
	public Room getArea_id() {
		return area_id;
	}

	public void setArea_id(Room area_id) {
		this.area_id = area_id;
	}

	public Integer getUser_area_id() {
		return user_area_id;
	}

	public void setUser_area_id(Integer user_area_id) {
		this.user_area_id = user_area_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	

	private Integer user_area_id; //ID
	
	private Integer user_id; //宿管人员ID
	
	private Room area_id; //负责的房间ID
	
	

}
