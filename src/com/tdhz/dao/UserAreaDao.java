package com.tdhz.dao;

import java.util.List;

import com.tdhz.pojo.PerInfo;
import com.tdhz.pojo.Room;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.pojo.User_Area;

public interface UserAreaDao {	
	
	public List<User_Area> getUserArea(Integer userId); //根据宿管员ID查找公寓

	public List<Room> findAllfloorByProom(Integer proom); //根据proom查找他的下级roomlist

	public List<PerInfo> findAllperByroomId(Integer roomId); //根据房间ID查找人员
	
	//根据人员ID查找最后一次通过记录
	public List<Tbcha_channeltg_14> findTgByPer(Integer perInfoId);
	
	//
	public List<Object[]> findByRoomId(String sql);

	public List<PerInfo> findNotBackPerByfloorId(String sql);

	/**
	 * 获取用户管理的所有寝室信息
	 * @param roomId
	 * @return
	 */
	List<Room> getUserRoom(Integer roomId);
	

}
