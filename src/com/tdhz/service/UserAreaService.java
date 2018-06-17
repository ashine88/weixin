package com.tdhz.service;

import java.util.List;

import com.tdhz.pojo.*;

public interface UserAreaService {
	//宿管员操作报表统计
	public void addUserOperaLog(UserOperaLog u);
	//查找所有公寓统计信息
	public List<Object[]> fingBuildById(Integer roomId);	
	//查找所有楼层统计
	public List<Object[]> findRoomById(Integer roomId);
	//根据楼层ID  查找未归寝人员信息
	public List<PerInfo> finNotBackByfloorId(Integer roomId);
	//根据房间ID查找人员
	public List<PerInfo> findPerByRoomId(Integer roomId);
	
	//根据公寓ID统计考勤信息
	public List<Tbcha_channeltg_14> findlastByRoomId(Integer proom);
	
	public List<User_Area> getUserArea(Integer userId); //根据人员ID查找负责的公寓id

	public Room getRoomById(Integer rid); //根据宿舍ID查找宿舍

	public List<Room> findAllfloorByProomId(Integer proom); //根据公寓ID查找楼层ID

	public List<PerInfo> findAllPerByroomId(Integer roomId);

	public List<Room> findAllroomByProomId(Integer proom); //根据楼层ID  查找房间ID
	

}
